package storecloud
import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_CUSTOMER'])
class CustomerManagementController {

	String getName() {
		return "name";
	}
	def springSecurityService
	
    def index() {
		
	}
	
	def listInformations() {
		def user = Customer.get(springSecurityService.principal.id)
		render(view:"listInformations", model: [user: user])
	}
	
	def listOrders() {
		def user = Customer.get(springSecurityService.principal.id)
		render(view:"listOrders", model: [user: user])
	}
	
	def updateInformations() {
		
		def user = Customer.get(springSecurityService.principal.id)
		
		user.email = params.email
	
		user.firstName = params.firstname
		user.lastName = params.lastname
		user.address = params.address
		user.city = params.city
		user.postalCode = params.postalcode
		user.country = params.country
		
		user.save(failOnError: true)
		
		render(view:"listInformations", model: [user: user])
	}
}
