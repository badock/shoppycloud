package storecloud

import grails.plugin.multitenant.core.annotation.MultiTenant;

import java.util.List;

@MultiTenant
class ProductMainClass extends ProductClass {
	
	static hasMany = [products:Product,subclasses:ProductSubClass]
	
	def List<Product> getProducts() {
		List<Product> result = super.getProducts()
		
		for(ProductSubClass subClass : subclasses) {
			result = result.plus(subClass.products)
		}
		
		return result 
	}
	
    static constraints = {
    }
}
