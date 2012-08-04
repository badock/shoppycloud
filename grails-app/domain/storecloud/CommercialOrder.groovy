package storecloud

import grails.plugin.multitenant.core.annotation.MultiTenant;

@MultiTenant
class CommercialOrder {

	static belongsTo = [customer:Customer]
	static hasMany = [orders:ProductOrder]
	
    static constraints = {
    }
}
