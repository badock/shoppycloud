package storecloud

class AccountService {

    def exist(email) {
		
		if(ShoppyAccount.findByEmail(email)==null)
			return false
		else
			return true
    }
	
	def createAccount(email) {
		
		if(!exist(email)) {
			
			ShoppyAccount account = new ShoppyAccount(email:email, firstName:"", lastName:"").save(failOnError: true)
			return account
		}
		
		throw new Exception("Adress \"+"+email+"\" already taken.")
	}
}
