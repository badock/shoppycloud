package storecloud

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;


class PaymentService {

	
	def grailsApplication
	
	class CustomizedHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}


	static transactional = true

	def getTransactionTokenPaypal(CommercialOrder order, urlOk, urlKo) {



		BigDecimal amountBD = 0

		for (po in order.orders) {

			amountBD += po.quantity*po.price
		}

		BigDecimal netAmountBD = new BigDecimal(amountBD*0.804);
		BigDecimal taxAmountBD = amountBD.subtract(netAmountBD);


		String netAmount = netAmountBD.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
		String totalAmount = amountBD.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
		String taxAmount = taxAmountBD.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();

		def user = order.customer

		def urlPaypal = sprintf("USER=%s" +
				"&PWD=%s" +
				"&SIGNATURE=%s" +
				"&VERSION=%s" +
				"&METHOD=%s" +
				"&PAYMENTREQUEST_0_PAYMENTACTION=%s" +
				"&LANDINGPAGE=%s" +
				"&SOLUTIONTYPE=%s" +
				"&EMAIL=%s" +
				"&FIRSTNAME=%s" +
				"&LASTNAME=%s" +
				"&STREET=%s" +
				"&CITY=%s" +
				"&ZIP=%s" +
				"&STATE=%s" +
				"&PAYMENTREQUEST_0_CURRENCYCODE=%s" +
				"&MAXAMT=%s" +
				"&PAYMENTREQUEST_0_AMT=%s" +
				"&PAYMENTREQUEST_0_ITEMAMT=%s" +
				"&PAYMENTREQUEST_0_TAXAMT=%s" +
				"&PAYMENTREQUEST_0_DESC=%s" +
				"&L_PAYMENTREQUEST_0_NAME0=%s" +
				"&L_PAYMENTREQUEST_0_NUMER0=%s" +
				"&L_PAYMENTREQUEST_0_DESC0=%s" +
				"&L_PAYMENTREQUEST_0_AMT0=%s" +
				"&L_PAYMENTREQUEST_0_QTY0=%s" +

				"&NOSHIPPING=%s" +
				"&LOCALECODE=%s" +
				"&RETURNURL=%s" +
				"&CANCELURL=%s" +
				"&ADDROVERRIDE=%s" +
				"&BRANDNAME=%s"
				,
				//-------
				"badock_1345224114_biz_api1.gmail.com",
				"1345224140",
				"AsdaTfwqV5zJFyVHzJvIOfBBxDpmAnZVTLkV6jWsJs88az7mS0CdVW3Y",
				"69.0", // API Version
				"SetExpressCheckout", //Method
				"Sale",
				"Billing",
				"Sole",
				user.email,
				"" + URLEncoder.encode(user.firstName),
				"" + URLEncoder.encode(user.lastName),
				"" + URLEncoder.encode(user.address),
				"" + URLEncoder.encode(user.city),
				"" + URLEncoder.encode(user.postalCode),
				"" + URLEncoder.encode(user.country),
				"EUR",
				totalAmount, //MaxAMT
				totalAmount, //amount.toString()
				netAmount, //PAYMENTREQUEST_n_ITEMAMT
				taxAmount,
				URLEncoder.encode("Payment with Paypal"),
				//---------
				URLEncoder.encode("Item name"),
				"1",
				URLEncoder.encode("Payment with Paypal"),
				netAmount,
				"1",
				//---------
				"1",
				"FR",
				urlOk, // success URL
				urlKo, // failure URL
				"1", //ADDROVERRIDE
				"ShoppyCloud.com"
				)

		def url = new URL("https"+"://"+"api-3t.sandbox.paypal.com"+"/nvp?"+urlPaypal)

		def connection = url.openConnection()

		BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()))
		String inputLine;

		def token = null
		def result = ""
		
		while ((inputLine = input.readLine()) != null)
			result = URLDecoder.decode(inputLine);
			
		for(String s : result.split("&")) {
			def tab = s.split("=")
			if(tab[0]=="TOKEN")
				token = tab[1]
		}
		input.close();
		//		throw new RuntimeException("Payment web service not yet implemented!")

//		println("<"+token+">")
		return token
		//		throw new RuntimeException("Testing transaction rollback!")
	}

	def makePaymentPaypal(CommercialOrder order) {

		order.save(flush:true, failOnError:true)
		for (po in order.orders) {

			po.save(flush:true, failOnError:true)
		}

		def urlOk = "http://"+Shop.findById(order.tenantId).domain+"."+grailsApplication.config.grails.domainURL+"/payment/transaction_ok"
		def urlKo = "http://"+Shop.findById(order.tenantId).domain+"."+grailsApplication.config.grails.domainURL+"/payment/transaction_ko"
		
		def token = getTransactionTokenPaypal(order, urlOk, urlKo)
		
		if(token == null)
			throw new RuntimeException("Paypal error: could not get a token!")
		
		return token
	}
}
