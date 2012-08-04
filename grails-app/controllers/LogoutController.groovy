import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

import storecloud.security.Role;
import storecloud.Shop;
import storecloud.security.User;
import storecloud.security.UserRole;

class LogoutController {

	def storeService
	
	def isAdminController(String s) {
		return false
		//return s.contains("product/") || s.contains("productMainClass/") || s.contains("productSubClass/") ||s.contains("shop/")
	}
		
	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// TODO put any pre-logout code here
		session.invalidate()
		
		String url = "http://"+Shop.findById(request.store).domain+"."+grailsApplication.config.grails.domainURL+params.targetUri
		if(isAdminController(params.targetUri))
			url = "http://"+Shop.findById(request.store).domain+"."+grailsApplication.config.grails.domainURL
			
		redirect(url:url)
	}
}
