package storecloud

import grails.converters.JSON;

class StoreController {
	
	def customerService
		
	def index() {
		render(view:"index", model: [controllerIndex:0, store:Shop.findById(request.store), products:Product.list(), categories: ProductMainClass.list()])
	}
	
	def show() {
		render(view:"show", model: [controllerIndex:1, store:Shop.findById(request.store), products:Product.list(), categories: ProductMainClass.list()])
	}
	
	def show_category(String id) {
		
		def category = ProductClass.findById(id)
		
		if(category != null) {
			
			def products = category.getProducts()
	
			def parentProductClass = category
			def subs = new ArrayList()
			
			if(category instanceof ProductMainClass) {
				subs = category.subclasses
			}
			else
			if(category instanceof ProductSubClass) {
				parentProductClass = category.parentClass
				subs = parentProductClass.subclasses
			}
		
			render(view:"show_category", model: [controllerIndex:1, store:Shop.findById(request.store), categories: ProductMainClass.list(), category:category, parentProductClass:parentProductClass, products: products, subs:subs])
		}
		else {
			render(view:"bad_request", model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])			
		}
	}
	
	def show_product(String id) {
		
		def product = Product.findById(Long.parseLong(id))
		
		if(product != null) {
			
			def category = product.category
			def parentProductClass = category
			def subs = new ArrayList()
			
			if(category instanceof ProductMainClass) {
				subs = category.subclasses
			}
			else
			if(category instanceof ProductSubClass) {
				parentProductClass = category.parentClass
				subs = parentProductClass.subclasses
			}
			
			render(view:"show_product", model: [controllerIndex:1, store:Shop.findById(request.store), categories: ProductMainClass.list(), category:category, parentProductClass:parentProductClass, subs:subs, product: product])
		}
		else {
			render(view:"bad_request", model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
		}
			
	}
	
	def add_to_cart() {
		
		if(session["cart"] == null)
			session["cart"] = new HashMap<Long, Double>()
		
		if(session["productindex"] == null)
			session["productindex"] = new HashMap<Long, Product>()
			
		Product product = Product.findById(Long.parseLong(params.productid))	
		
		if( product != null) {
			
			HashMap<Long, Double> map = session["cart"]
			HashMap<Long, Double> mapindex = session["productindex"]
			
			if(map.get(product.id)==null)
				map.put(product.id,0)
			
			if(mapindex.get(product.id)==null)
				mapindex.put(product.id,product)				
				
			map.put(product.id, map.get(product.id)+1)
		}
		
		show_cart()
	}
	
	def show_cart() {
		if(session["cart"] == null)
			session["cart"] = new HashMap<Long, Double>()
		
		if(session["productindex"] == null)
			session["productindex"] = new HashMap<Long, Product>()
		
		updateCartSize()
		render(view:"show_cart", model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
	}
	
	def update_cart() {
		
		if(session["cart"] == null)
			session["cart"] = new HashMap<Long, Double>()
		
		if(session["productindex"] == null)
			session["productindex"] = new HashMap<Long, Product>()
		
		for (p in params) {
			if(p.key.toString().startsWith("product-")) {
				Long product_id = Long.parseLong(p.key.toString().split("-")[1])
				Long value = Long.parseLong(p.value.toString())
				
				HashMap<Long, Double> map = session["cart"]
				
				map.put(product_id, value)
			}
		}
		updateCartSize()
		render(view:"show_cart", model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
	}
	
	def delete_product_from_chart() {
		
		if(session["cart"] == null)
			session["cart"] = new HashMap<Long, Double>()
		
		if(session["productindex"] == null)
			session["productindex"] = new HashMap<Long, Product>()
			
		Long product_id = Long.parseLong(params["delete-id"])
		HashMap<Long, Double> map = session["cart"]
		
		map.remove(product_id)
		updateCartSize()
		render(view:"show_cart", model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
	}
	
	def bad_request() {
		render(view:"bad_request",  model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
	}
	
	def checkcustomeremail() {
		def obj
		if(!customerService.exist(params.id))
			obj = [result:true] as JSON
		else
			obj = [result:false] as JSON
			
		render obj
	}
	
	def doRegistration() {
		
		def customer = null
		
		try {
			customer = customerService.createCustomer(params.email, params.password, params.firstname, params.lastname, params.address, params.city, params.postalcode, params.country, Shop.findById(request.store))
		}
		catch (Exception e) {
			log.println(e.toString())
		}
		
		if(customer==null) {
			
			def email = params.email
			def password = params.password
			def firstname = params.firstname
			def lastname = params.lastname
			def address = params.address
			def city = params.city
			def postalcode = params.postalcode
			def country = params.country
			
			render(view:"register",  model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list(), email:"", firstname:firstname, lastname:lastname, address:address, city:city, postalcode:postalcode, country:country])
		}
		else {			
			render(view:"registered",  model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
		}
	}
	
	def register() {
		
		render(view:"register",  model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
	}
	
	private void updateCartSize() {
		
		def size = 0
		
		if(session["cart"] != null)
			size = session["cart"].size()
			
		session["cartSize"] = size
	}
}
