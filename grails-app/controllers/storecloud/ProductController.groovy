package storecloud

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN'])
class ProductController {
	
	static scaffold = true
}
