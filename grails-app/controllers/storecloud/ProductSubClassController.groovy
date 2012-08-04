package storecloud

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN'])
class ProductSubClassController {	

    static scaffold = true
}
