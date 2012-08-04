package storecloud

class StoreController {
		
	def index() {
		render(view:"index", model: [controllerIndex:0, store:Shop.findById(request.store), categories: ProductMainClass.list()])
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
	
	def subscribe() { 
		
	}
	
	private void updateCartSize() {
		
		def size = 0
		
		if(session["cart"] != null)
			size = session["cart"].size()
			
		session["cartSize"] = size
	}
}
