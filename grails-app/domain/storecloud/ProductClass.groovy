package storecloud

import java.util.List;
import grails.plugin.multitenant.core.annotation.MultiTenant


@MultiTenant
abstract class ProductClass {
	
	String name
	
	def List<Product> getProducts() {
		List<Product> result = Product.findAll("from Product as b where b.category.id = :id_cat", [id_cat: id])
		
		return result
	}
	
    static constraints = {
    }
	
	String toString() {
		return name
	}
}
