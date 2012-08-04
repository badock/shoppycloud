package storecloud

class ShopService {

    def exist(domain) {
		if(Shop.findByDomain(domain)==null)
			return false
		else
			return true
    }
	
	def createShop(domain, account) {
		
		if(!exist(domain)) {
			
			Shop store = new Shop(name:"Boutique de test1", account:account, domain:domain, slogan:"Welcome in our Shop(1)", shortDescription:"We sell a lot of different products. Please take a look at them and do not hesitate to contact us!").save(failOnError: true)
			return store
		}
		
		throw new Exception("Domain \"+"+domain+"\" already taken.")
	}
}
