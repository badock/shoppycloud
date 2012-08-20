package storecloud

import grails.plugin.multitenant.core.annotation.MultiTenant;
import storecloud.security.User;

@MultiTenant
class Customer extends User {

	static hasMany = [orders:CommercialOrder]
	
	String email
	
	String firstName
	String lastName
	String address
	String city
	String postalCode
	String country
	
    static constraints = {
		email()
		firstName()
		lastName()
		address()
		city()
		postalCode()
		country()
    }
}
