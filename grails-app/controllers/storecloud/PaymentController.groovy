package storecloud

import grails.plugins.springsecurity.Secured;
import org.springframework.security.core.Authentication;

@Secured(['ROLE_CUSTOMER'])
class PaymentController {

	def springSecurityService
	def paymentService
	
    def checkout_cart() {
		
		def noError = true
		
		try {
			
		
			def user = springSecurityService.getPrincipal()
			def customer = Customer.findById(user.id)
			
			CommercialOrder corder = new CommercialOrder()
			corder.customer = customer
			//corder.save(failOnError: true)
			
			HashMap<Long, Double> cart = session["cart"]
			HashMap<Long, Product> product_index = session["cart"]
			
			
			def orders = new ArrayList()
			
			for (p in cart) {
				
				Product product = Product.findById(p.key)
				Long quantity = p.value
				
				ProductOrder porder = new ProductOrder(product: product,quantity: quantity, price:product.price, order:corder)	
				//porder.save(failOnError: true)
				orders.add(porder)
			}
			
			corder.orders = orders
			//corder.save(failOnError: true)
			
			paymentService.makePaymentPaypal(corder)
			
			session["cart"] = null
			session["productindex"] = null
			
			//redirect(controller: "customerManagement", action: "listOrders")
		}
		catch (e) {
			
			log.println("error: transaction service throwed and exception:")
			e.printStackTrace()
			
			noError = false
		}
		
		if(noError) {
			
			render "transaction: [OK]"
		}
		else {
			
			render "transaction: [KO]"			
		}
		//render(view:"show_cart", model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
	}
}
