package storecloud



import grails.test.mixin.*
import org.junit.*
import storecloud.Shop
import storecloud.Customer
import storecloud.Product
import storecloud.ProductOrder
import storecloud.ProductMainClass
import storecloud.ProductSubClass

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PaymentService)
class PaymentServiceTests {

	def paymentService = new PaymentService()
	
	void testSomething() {

		
		ProductClass computerCategory = new ProductMainClass(name:"Computer")
//		computerCategory.save(failOnError: true)
		String descriptionProduct = "lolo"

		def prod1 = new Product(name:"iBook", price:1050.10, category:computerCategory, markdownDescription:descriptionProduct, pictureUrl:"http://pc.watch.impress.co.jp/docs/2004/0420/apple2.jpg", technicalSpecs:"Apple,Notebook, Macbook ", description:"Latius iam disseminata licentia onerosus bonis omnibus Caesar nullum post haec adhibens modum orientis latera cuncta vexabat nec honoratis parcens nec urbium primatibus nec plebeiis.")
//		prod1.save(failOnError: true)
		def prod2 = new Product(name:"PowerBook", price:1499.99, category:computerCategory, markdownDescription:descriptionProduct, pictureUrl:"http://pc.watch.impress.co.jp/docs/2004/0420/apple2.jpg", technicalSpecs:"Apple,Notebook, Macbook ", description:"Latius iam disseminata licentia onerosus bonis omnibus Caesar nullum post haec adhibens modum orientis latera cuncta vexabat nec honoratis parcens nec urbium primatibus nec plebeiis.")
//		prod2.save(failOnError: true)
		
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
				enabled: true)
//		customer1.save(true)
		// </store1>

		def order1 = new CommercialOrder(customer:customer1)
		order1.orders = new ArrayList<ProductOrder>()
//		order1.save(failOnError: true)
		customer1.orders = new ArrayList<CommercialOrder>()
		customer1.orders.add(order1)
//		customer1.save(failOnError: true)
		def po1 = new ProductOrder(product:prod1, quantity:2, price:1250.90, order:order1)
//		po1.save(failOnError: true)
		def po2 = new ProductOrder(product:prod2, quantity:1, price:1050.90, order:order1)
//		po2.save(failOnError: true)
		order1.orders += po1
		order1.orders += po2
		
		paymentService.getTransactionTokenPaypal(order1, "http://127.0.0.1/ok", "http://127.0.0.1/ko")
	}

}
