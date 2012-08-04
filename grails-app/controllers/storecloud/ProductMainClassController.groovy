package storecloud

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN'])
class ProductMainClassController {
	
    static scaffold = true
}
