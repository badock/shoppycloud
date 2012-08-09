package storecloud

import org.springframework.dao.DataIntegrityViolationException;

import storecloud.security.UserRole;

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN'])
class CustomerController {
	
	static scaffold = Customer
	
	def delete() {
		def customer = Customer.get(params.id)
		if (!customer) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
			redirectToUrlWithTenant([action: "list"])
			return
		}

		try {
			UserRole.findByUser(customer).delete(flush:true)
			customer.delete(flush: true)
			
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
			redirectToUrlWithTenant([action: "list"])
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'customer.label', default: 'Customer'), params.id])
			redirectToUrlWithTenant([action: "show", id: params.id])
		}
	}
}
