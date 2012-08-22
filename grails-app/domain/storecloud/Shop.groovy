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
	
	String paypalAccount = ""
	String paypalPassword = ""
	String paypalApiKey = ""
	Boolean paypalProductionMode = false
	Boolean paypalIsConfigured = false
	
	Integer tenantId() {
		return this.id
	}
	
	String theme="united"
    
	static constraints = {
		domain (unique: true, disabled: true)
		name()
		account(display:false)
		slogan()
		shortDescription()
		paypalAccount()
		paypalPassword()
		paypalApiKey()
		paypalProductionMode()
		paypalIsConfigured(disabled: true)
		theme (inList: ["amelia", "azure", "basic", "clean", "cerulean", "green", "united", "test"])
    }
}
