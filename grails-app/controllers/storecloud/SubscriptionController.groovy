package storecloud

import grails.converters.JSON;

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

class SubscriptionController {
	
	static layout = 'subscription'
	def subscriptionService
	def shopService
	def accountService
	
	def index() {
	}
	
	def checkemail() {
		def obj
		if(!accountService.exist(params.id))
			obj = [result:true] as JSON
		else
			obj = [result:false] as JSON
			
		render obj
	}
	
	def checkdomain() {
		def obj
		if(!shopService.exist(params.id))
			obj = [result:true] as JSON 
		else
			obj = [result:false] as JSON
		
		render obj
	}
	
	def checkoverall() {
		
		def arrayOfParams = params.id.split("-");
		def email = arrayOfParams[0]
		def domain = arrayOfParams[1]
		
		def obj
		if(!accountService.exist(email) && !shopService.exist(domain)) {
			
			subscriptionService.createRegistration(email, domain)
			
			obj = [result:true] as JSON
		}			
		else {
			obj = [result:false] as JSON
		}
		render obj	
	}
	
	def doRegister() {
		subscriptionService.computeRegistration(params.email, params.domain)
		redirect(url:"http://"+params.domain+"."+CH.config.grails.domainURL)
	}
}
