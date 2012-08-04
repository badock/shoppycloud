package storecloud

class PaymentService {

	static transactional = true

	def makePaymentPaypal(CommercialOrder order) {

		order.save(flush:true, failOnError:true)
		for (po in order.orders) {

			po.save(flush:true, failOnError:true)
		}

		def url = sprintf("https://developer.paypal.com" +
				"&USER=%s" +
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
				"&STREET2=%s" +
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
				Play.configuration.get("paypal.api.username").toString(),
				Play.configuration.get("paypal.api.password").toString(),
				Play.configuration.get("paypal.api.signature").toString(),
				"69.0", // API Version
				"SetExpressCheckout", //Method
				"Sale",
				"Billing",
				"Sole",
				user.email,
				"" + user.fullname,
				"" + user.fullname,
				"" + user.postalAddress,
				"" + user.postalAddress2,
				"" + user.city,
				"" + user.zip,
				"None",
				"EUR",
				totalAmount, //MaxAMT
				totalAmount, //amount.toString()
				netAmount, //PAYMENTREQUEST_n_ITEMAMT
				taxAmount,
				subject,
				//---------
				"Item name",
				"1",
				subject,
				netAmount,
				"1",
				//---------
				"1",
				"FR",
				Play.configuration.get("paypal.returnUrl").toString(),
				Play.configuration.get("paypal.cancelPay").toString(),
				"1", //ADDROVERRIDE
				"eXpress-Board pour Innoteria"
				)

				throw new RuntimeException("Payment web service not yet implemented!")
		//		throw new RuntimeException("Testing transaction rollback!")
	}
}
