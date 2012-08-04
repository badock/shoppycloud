package storecloud.security

import grails.plugin.multitenant.core.annotation.MultiTenant;

@MultiTenant
class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
