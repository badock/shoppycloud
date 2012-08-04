package storecloud

import grails.plugin.multitenant.core.annotation.MultiTenant


@MultiTenant
class Product {
	
	String name
	Double price
	
	String pictureUrl
	String technicalSpecs
	String description
	String markdownDescription
	
	static belongsTo = [category:ProductClass]
	
    static constraints = {
    }
	
	
	static mapping = {
		description type: 'text'
		markdownDescription type: 'text'
	}
	
	String toString() {
		return name
	}
}
