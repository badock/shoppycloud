class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/subscription/checkemail/$email"
		"/subscription/checkdomain/$domain"
		
		"/j_spring_security_logout"
		
		"/"(controller:"store")
		"500"(view:'/error')
		
		"/login/$action?"(controller: "login")
		"/logout/$action?"(controller: "logout")
	}
}
