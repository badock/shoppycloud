package storecloud

import grails.plugins.springsecurity.Secured;
import org.springframework.security.core.Authentication;

@Secured(['ROLE_CUSTOMER'])
class PaymentController {

	def springSecurityService
	def paymentService

	def checkout_cart() {

		def noError = true
		def token = "-1"
		try {


			def user = springSecurityService.getPrincipal()
			def customer = Customer.findById(user.id)

			CommercialOrder corder = new CommercialOrder()
			corder.customer = customer

			HashMap<Long, Double> cart = session["cart"]
			HashMap<Long, Product> product_index = session["cart"]


			def orders = new ArrayList()

			for (p in cart) {

				Product product = Product.findById(p.key)
				Long quantity = p.value

				ProductOrder porder = new ProductOrder(product: product,quantity: quantity, price:product.price, order:corder)
				
				orders.add(porder)
			}

			corder.orders = orders

			token = paymentService.makePaymentPaypal(corder)

			session["cart"] = null
			session["productindex"] = null
		}
		catch (e) {

			log.println("error: transaction service throwed and exception:"+e)
			noError = false
		}

		if(noError) {

			redirect(url : "https://www.sandbox.paypal.com/webscr&cmd=_express-checkout&token="+token)
		}
		else {

			render "transaction: [KO] (could not contact paypal)"
		}
	}

	def transaction_ok() {
		
		def token = params["token"]
		def payerId = params["PayerId"]
		
		render "[OK] (paypal: it is ok!)"
	}

	def transaction_ko() {
		render "[KO] (paypal: payment error)"
	}
}
