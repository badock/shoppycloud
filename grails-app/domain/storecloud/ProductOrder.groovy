package storecloud

import grails.plugin.multitenant.core.annotation.MultiTenant;

@MultiTenant
class ProductOrder {

	
	static belongsTo = [order:CommercialOrder]
	
	Product product
	
	int quantity
	Double price
	
	
    static constraints = {
    }
}
