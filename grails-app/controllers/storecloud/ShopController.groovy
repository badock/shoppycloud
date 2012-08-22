package storecloud

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ADMIN'])
class ShopController {

	def grailsApplication
	static allowedMethods = [save: "POST", update: "POST"]

	def getListWithTenant() {
		if("Shop".equals("Shop"))
			Shop.findAll("from Shop as e where e.id = "+request.store)
		else
			Shop.findAll("from Shop as e where e.store = "+request.store)
	}

	def getWithTenant(id) {
		if("Shop".equals("Shop"))
			Shop.find("from Shop as e where e.id ="+id+" and e.id = "+request.store)
		else
			Shop.find("from Shop as e where e.id ="+id+" and e.store = "+request.store)
	}

	def toUrlWithTenant(blob) {
		def url_server = grailsApplication.config.grails.domainURL
		String result = "http://"+request.serverName.substring(0, request.serverName.indexOf("."))+"."+url_server+"/Shop"+"/"
		if(blob.action)
			result += blob.action
		if(blob.id)
			result += "/"+blob.id
		result
	}

	def redirectToUrlWithTenant(blob) {
		redirect(url:toUrlWithTenant(blob))
	}

	def edit() {
		def shopInstance = Shop.get(request.store)
		if (!shopInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'shop.label', default: 'Shop'), params.id])
			//redirect(action: "list")
			redirectToUrlWithTenant([action : "list"])
			return
		}

		[shopInstance: shopInstance]
	}

	def update() {
		def shopInstance = Shop.get(request.store)
		if (!shopInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'shop.label', default: 'Shop'), params.id])
			//redirect(action: "list")
			redirectToUrlWithTenant([action : "list"])
			return
		}

		if (params.version) {
			def version = params.version.toLong()
			if (shopInstance.version > version) {
				shopInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[message(code: 'shop.label', default: 'Shop')] as Object[],
						"Another user has updated this Shop while you were editing")
				render(view: "edit", model: [shopInstance: shopInstance])
				return
			}
		}

		shopInstance.properties = params

		if (!shopInstance.save(flush: true)) {
			if(shopInstance.paypalAccount!="" && shopInstance.paypalPassword!="" && shopInstance.paypalApiKey!="") {

				shopInstance.paypalIsConfigured = true
				shopInstance.save()
			}
			else {
				shopInstance.paypalIsConfigured = false
				shopInstance.save()
			}

			render(view: "edit", model: [shopInstance: shopInstance])
			return
		}



		if(shopInstance.paypalAccount!="" && shopInstance.paypalPassword!="" && shopInstance.paypalApiKey!="") {

			shopInstance.paypalIsConfigured = true
			shopInstance.save()
		}
		else {
			shopInstance.paypalIsConfigured = false
			shopInstance.save()
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'shop.label', default: 'Shop'), shopInstance.id])

		//redirect(action: "show", id: shopInstance.id)
		redirectToUrlWithTenant([action: "edit", id: shopInstance.id])
	}
}
