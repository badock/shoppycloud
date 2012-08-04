package storecloud

import grails.plugin.multitenant.core.Tenant

/**
 * TODO: Implement me!
 *
 * @see http://multi-tenant.github.com/grails-multi-tenant-single-db/
 */
class Shop implements Tenant {

    String name
	String slogan
	String shortDescription
	
	String domain
	static belongsTo = [account:ShoppyAccount]
	
	Integer tenantId() {
		return this.id
	}
	
	String theme="united"
    
	static constraints = {
		domain (unique: true, disabled: true)
		theme (inList: ["amelia", "clean", "cerulean", "green", "united", "test"])
    }
}
