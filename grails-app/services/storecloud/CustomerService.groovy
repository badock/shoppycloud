package storecloud

import storecloud.security.Role;
import storecloud.security.UserRole

class CustomerService {

    def exist(email, store) {
		
		store.withThisTenant {
			if(Customer.findByEmail(email)==null)
				return false
			else
				return true
		}
    }
	
	def createCustomer(email,password,firstname,lastname,address,city,postalcode,country, store) {
		
		store.withThisTenant {
			if(!exist(email, store)) {
				
				def customerRole = Role.findByAuthority('ROLE_CUSTOMER') ?: new Role(authority: 'ROLE_CUSTOMER').save(failOnError: true)
				
				def user = new Customer(
					email: email,
					username: email,
					password: password,
					firstName: firstname,
					lastName: lastname,
					address: address,
					city: city,
					postalCode: postalcode,
					country: country,
					enabled: true).save(failOnError: true)
				
				new UserRole(user:user, role:customerRole).save(failOnError: true)
						
				return user
			}
			else {				
				throw new Exception("Adress \"+"+email+"\" already taken.")
			}
		}
		
	}
}
