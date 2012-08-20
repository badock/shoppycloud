package storecloud

import grails.plugin.multitenant.core.annotation.MultiTenant


@MultiTenant
class Product {
	
	String name
	Double price
	Double VAT = 0.196
	
	String pictureUrl
	String tags
	String markdownDescription
	
	static belongsTo = [category:ProductClass]
	
    static constraints = {
		name()
		category()
		price()
		VAT()
    }
	
	
	static mapping = {
		markdownDescription type: 'text'
	}
	
	String toString() {
		return name
	}
}
