
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityFilterPosition;
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import storecloud.Customer
import storecloud.ProductClass
import storecloud.Product
import storecloud.ProductMainClass
import storecloud.ProductOrder;
import storecloud.CommercialOrder;
import storecloud.ProductSubClass
import storecloud.Shop
import storecloud.ShoppyAccount
import storecloud.security.Role;
import storecloud.security.User;
import storecloud.security.UserRole;

class BootStrap {

	static executed = false

	def init = { servletContext ->
		// SHOPPY ACCOUNT
		environments {
			development {
				if(!executed) {

					executed = true

					// TO MAKE MULTI TENANT FILTER EXECUTED BEFORE SPRING SECURITY FILTER!
					//SpringSecurityUtils.clientRegisterFilter('tenantFilter', SecurityFilterPosition.PRE_AUTH_FILTER.getOrder() - 1)
					//

					// CREATION OF MOCK DATA
					ShoppyAccount firstAccount = new ShoppyAccount(id:1, email:"jonathancmoa@gmail.com", firstName:"Jonathan", lastName:"Pastor").save(failOnError: true)
					ShoppyAccount secondAccount = new ShoppyAccount(id:2, email:"lukeskywalker@msn.com", firstName:"Luke", lastName:"Skywalker").save(failOnError: true)

					// STORE INFORMATION
					Shop thisStore = new Shop(id:1, name:"Boutique de test1", account:firstAccount, domain:"app1", slogan:"Welcome in our Shop(1)", shortDescription:"We sell a lot of different products. Please take a look at them and do not hesitate to contact us!").save(failOnError: true)



					// <store1>
					// PRODUCT LIST
					thisStore.withThisTenant {

						ProductClass computerCategory = new ProductMainClass(name:"Computer").save(failOnError: true)
						ProductClass notebookCategory = new ProductSubClass(name:"Notebook", parentClass:computerCategory).save(failOnError: true)
						new ProductMainClass(name:"Media").save(failOnError: true)
						new ProductMainClass(name:"Cars").save(failOnError: true)
						new ProductMainClass(name:"Food").save(failOnError: true)

						File productDescriptionFile = new File("product_description.md");
						String descriptionProduct = productDescriptionFile.getText();

						def prod1 = new Product(name:"iBook", price:1050.10, category:notebookCategory, markdownDescription:descriptionProduct, pictureUrl:"http://pc.watch.impress.co.jp/docs/2004/0420/apple2.jpg", technicalSpecs:"Abundans ignorantes et ignorantes possint frumenti capi multiplices sustentantur siquae", description:"Latius iam disseminata licentia onerosus bonis omnibus Caesar nullum post haec adhibens modum orientis latera cuncta vexabat nec honoratis parcens nec urbium primatibus nec plebeiis.").save(failOnError: true)
						def prod2 = new Product(name:"PowerBook", price:1499.99, category:notebookCategory, markdownDescription:descriptionProduct, pictureUrl:"http://pc.watch.impress.co.jp/docs/2004/0420/apple2.jpg", technicalSpecs:"Abundans ignorantes et ignorantes possint frumenti capi multiplices sustentantur siquae", description:"Latius iam disseminata licentia onerosus bonis omnibus Caesar nullum post haec adhibens modum orientis latera cuncta vexabat nec honoratis parcens nec urbium primatibus nec plebeiis.").save(failOnError: true)
						def prod3 = new Product(name:"BasicComputer", price:799.99, category:computerCategory, markdownDescription:descriptionProduct, pictureUrl:"http://pc.watch.impress.co.jp/docs/2004/0420/apple2.jpg", technicalSpecs:"Abundans ignorantes et ignorantes possint frumenti capi multiplices sustentantur siquae", description:"Latius iam disseminata licentia onerosus bonis omnibus Caesar nullum post haec adhibens modum orientis latera cuncta vexabat nec honoratis parcens nec urbium primatibus nec plebeiis.").save(failOnError: true)


						//					// USER AUTHENTICATION
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

						def customer1 = new Customer(
								email: "jonathancmoa@gmail.com",
								username: 'customer1',
								password: 'pass',
								firstName: "Dark",
								lastName: "Vador",
								address: "1st main street",
								city: "mos esley",
								postalCode: "44980",
								country: "tatooine",
								enabled: true).save(failOnError: true)
						// </store1>

						def order1 = new CommercialOrder(customer:customer1).save(failOnError: true)
						customer1.orders = new ArrayList<CommercialOrder>()
						customer1.orders.add(order1)
						customer1.save(failOnError: true)
						def po1 = new ProductOrder(product:prod1, quantity:2, price:1250.90, order:order1).save(failOnError: true)
						def po2 = new ProductOrder(product:prod2, quantity:1, price:1050.90, order:order1).save(failOnError: true)

						def order2 = new CommercialOrder(customer:customer1).save(failOnError: true)
						customer1.orders.add(order1)
						customer1.save(failOnError: true)
						def po12 = new ProductOrder(product:prod1, quantity:3, price:1250.90, order:order2).save(failOnError: true)
						def po22 = new ProductOrder(product:prod2, quantity:3, price:1050.90, order:order2).save(failOnError: true)


						new UserRole(user:customer1, role:customerRole).save(failOnError: true)

						def customer2 = new Customer(
								email: "jonathan.pastor@eleve.emn.fr",
								username: 'customer2',
								password: 'pass',
								firstName: "Luke",
								lastName: "Skywalker",
								address: "1st main street",
								city: "mos esley",
								postalCode: "44980",
								country: "tatooine",
								enabled: true).save(failOnError: true)
						// </store1>

						new UserRole(user:customer2, role:customerRole).save(failOnError: true)
					}

					Shop secondStore = new Shop(id:2, name:"Boutique de test2", account:secondAccount, domain:"app2", slogan:"Welcome in our Shop(2)", shortDescription:"We sell a lot of different products. Please take a look at them and do not hesitate to contact us!").save(failOnError: true)

					secondStore.withThisTenant {

						ProductClass computerCategory = new ProductMainClass(name:"Computer").save(failOnError: true)
						ProductClass notebookCategory = new ProductSubClass(name:"Notebook", parentClass:computerCategory).save(failOnError: true)
						new ProductMainClass(name:"Media").save(failOnError: true)
						new ProductMainClass(name:"Cars").save(failOnError: true)
						new ProductMainClass(name:"Food").save(failOnError: true)

						File productDescriptionFile = new File("product_description.md");
						String descriptionProduct = productDescriptionFile.getText();

						new Product(name:"iBook", price:1050.10, category:notebookCategory, markdownDescription:descriptionProduct, pictureUrl:"http://pc.watch.impress.co.jp/docs/2004/0420/apple2.jpg", technicalSpecs:"Abundans ignorantes et ignorantes possint frumenti capi multiplices sustentantur siquae", description:"Latius iam disseminata licentia onerosus bonis omnibus Caesar nullum post haec adhibens modum orientis latera cuncta vexabat nec honoratis parcens nec urbium primatibus nec plebeiis.").save(failOnError: true)
						new Product(name:"PowerBook", price:1499.99, category:notebookCategory, markdownDescription:descriptionProduct, pictureUrl:"http://pc.watch.impress.co.jp/docs/2004/0420/apple2.jpg", technicalSpecs:"Abundans ignorantes et ignorantes possint frumenti capi multiplices sustentantur siquae", description:"Latius iam disseminata licentia onerosus bonis omnibus Caesar nullum post haec adhibens modum orientis latera cuncta vexabat nec honoratis parcens nec urbium primatibus nec plebeiis.").save(failOnError: true)
						new Product(name:"BasicComputer", price:799.99, category:computerCategory, markdownDescription:descriptionProduct, pictureUrl:"http://pc.watch.impress.co.jp/docs/2004/0420/apple2.jpg", technicalSpecs:"Abundans ignorantes et ignorantes possint frumenti capi multiplices sustentantur siquae", description:"Latius iam disseminata licentia onerosus bonis omnibus Caesar nullum post haec adhibens modum orientis latera cuncta vexabat nec honoratis parcens nec urbium primatibus nec plebeiis.").save(failOnError: true)


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

						def customer1 = new Customer(
								email: "jonathancmoa@gmail.com",
								username: 'customer1',
								password: 'pass',
								firstName: "Dark",
								lastName: "Vador",
								address: "1st main street",
								city: "mos esley",
								postalCode: "44980",
								country: "tatooine",
								enabled: true).save(failOnError: true)
						// </store1>

						new UserRole(user:customer1, role:customerRole).save(failOnError: true)

					}
				}
			}
			// AUTHENTICATION: app1



			// AUTHENTICATION: app2
			//
			//		def userRole2 = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER', shop:secondStore).save(failOnError: true)
			//		def memberRole2 = Role.findByAuthority('ROLE_MEMBER') ?: new Role(authority: 'ROLE_MEMBER', shop:secondStore).save(failOnError: true)
			//		def adminRole2 = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN', shop:secondStore).save(failOnError: true)
			//
			//		def adminUser2 = User.findByUsername('lukeskywalker@msn.com') ?: new User(
			//			username: 'lukeskywalker@msn.com',
			//			password: 'admin',
			//			shop:secondStore,
			//			enabled: true).save(failOnError: true)
			// </store1>

			//		if (!adminUser2.authorities.contains(adminRole2)) {
			//			UserRole.create adminUser2, adminRole2, secondStore
			//		}
		}
	}
	def destroy = {
	}
}
