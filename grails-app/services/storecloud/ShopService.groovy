package storecloud

import storecloud.security.User
import storecloud.security.UserRole
import storecloud.security.Role

class ShopService {

    def exist(domain) {
		if(Shop.findByDomain(domain)==null)
			return false
		else
			return true
    }
	
	def createShop(domain, account, password) {
		
		if(!exist(domain)) {
			
			Shop store = new Shop(name:"My Online Shop", account:account, domain:domain, slogan:"Welcome in our Shop!", shortDescription:"We sell a lot of different products. Please take a look at them and do not hesitate to contact us!").save(failOnError: true, flush: true)			
					
			store.withThisTenant {
				
				// USER AUTHENTICATION
				def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnccouError: true)
				
				
				def adminUser = new User(
					username: account.email,
					password: password,
					enabled: true).save(failOnError: true)
				// </store1>
					
				new UserRole(user:adminUser, role:adminRole).save(failOnError: true)
			}
			return store
		}
		
		throw new Exception("Domain \"+"+domain+"\" already taken.")
	}
}
