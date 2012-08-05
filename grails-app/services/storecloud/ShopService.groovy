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
	
	def createShop(domain, account) {
		
		if(!exist(domain)) {
			
			Shop store = new Shop(name:"My Online Shop", account:account, domain:domain, slogan:"Welcome in our Shop!", shortDescription:"We sell a lot of different products. Please take a look at them and do not hesitate to contact us!").save(failOnError: true, flush: true)			
					
			store.withThisTenant {
				
				// USER AUTHENTICATION
				def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
				def memberRole = Role.findByAuthority('ROLE_MEMBER') ?: new Role(authority: 'ROLE_MEMBER').save(failOnError: true)
				def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
				def customerRole = Role.findByAuthority('ROLE_CUSTOMER') ?: new Role(authority: 'ROLE_CUSTOMER').save(failOnError: true)
				
				
				def adminUser = new User(
					username: 'admin',
					password: 'admin',
					enabled: true).save(failOnError: true)
				// </store1>
					
				new UserRole(user:adminUser, role:adminRole).save(failOnError: true)
			}
			return store
		}
		
		throw new Exception("Domain \"+"+domain+"\" already taken.")
	}
}
