package storecloud

import org.springframework.transaction.annotation.Transactional;

@Transactional
class SubscriptionService {

	def grailsApplication
	
	def accountService
	def shopService

	def createRegistration(String email, String domain, String password) {


		ShoppyAccount.withTransaction {
			statusAccount -> accountService.createAccount(email,password)

		}

		Shop.withTransaction {
			statusShop -> shopService.createShop(domain, ShoppyAccount.findByEmail(email), password)
		}
		
		
		def url_server = grailsApplication.config.grails.domainURL
		def url = "http://"+domain+"."+url_server
		def message = "<h1>Your shop is ready!</h1><br/>visit this link to access it: <a href=\"${url}\">${url}</a><br/>username:${email}<br/>password:${password}"
		
		try {
			sendMail {
				to      "${email}"
				from 	"registration@shoppycloud.com"
				subject "Your e-shop is available"
				html    message
			}
		} catch(Exception e) {
			log.error "Problem sending email $e.message", e
		}
	}
}
