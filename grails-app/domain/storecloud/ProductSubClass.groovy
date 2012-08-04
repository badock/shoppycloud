package storecloud

import grails.plugin.multitenant.core.annotation.MultiTenant;

import java.util.List;

@MultiTenant
class ProductSubClass extends ProductClass{
	
	static belongsTo = [parentClass:ProductMainClass]
	
    static constraints = {
    }
}
