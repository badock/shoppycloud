<%=packageName ? "package ${packageName}\n\n" : ''%>import org.springframework.dao.DataIntegrityViolationException

class ${className}Controller {
	
	def grailsApplication
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def getListWithTenant() {
		if("${className}".equals("Shop"))
			${className}.findAll("from ${className} as e where e.id = "+request.store)
		else
			${className}.findAll("from ${className} as e where e.store = "+request.store)
	}
	
	def getWithTenant(id) {
		if("${className}".equals("Shop"))
			${className}.find("from ${className} as e where e.id ="+id+" and e.id = "+request.store)
		else
			${className}.find("from ${className} as e where e.id ="+id+" and e.store = "+request.store)
	}
	
	def toUrlWithTenant(blob) {
		def url_server = grailsApplication.config.grails.domainURL
		String result = "http://"+request.serverName.substring(0, request.serverName.indexOf("."))+"."+url_server+"/${className}"+"/"
		if(blob.action)
			result += blob.action
		if(blob.id)
			result += "/"+blob.id
		result
	}
	
	def redirectToUrlWithTenant(blob) {
		redirect(url:toUrlWithTenant(blob))
	}
	
	def index() {
		//redirect(action: "list", params: params)
		redirectToUrlWithTenant([action: "list", params: params])
	}
	
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[${propertyName}List: ${className}.list(params), ${propertyName}Total: ${className}.count()]
		//[${propertyName}List: getListWithTenant(), ${propertyName}Total: ${className}.count()]
    }

    def create() {
        [${propertyName}: new ${className}(params)]
    }

    def save() {
        def ${propertyName} = new ${className}(params)
        if (!${propertyName}.save(flush: true)) {
            render(view: "create", model: [${propertyName}: ${propertyName}])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
        //redirect(action: "show", id: ${propertyName}.id)
		redirectToUrlWithTenant([action: "show", id: ${propertyName}.id])
    }

    def show() {
        def ${propertyName} = ${className}.get(params.id)
        if (!${propertyName}) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            //redirect(action: "list")
			redirectToUrlWithTenant([action : "list"])
            return
        }

        [${propertyName}: ${propertyName}]
    }

    def edit() {
        def ${propertyName} = ${className}.get(params.id)
        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            //redirect(action: "list")
			redirectToUrlWithTenant([action : "list"])
            return
        }

        [${propertyName}: ${propertyName}]
    }

    def update() {
        def ${propertyName} = ${className}.get(Long.parseLong(params.id))
        if (!${propertyName}) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            //redirect(action: "list")
			redirectToUrlWithTenant([action : "list"])
			return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (${propertyName}.version > version) {<% def lowerCaseName = grails.util.GrailsNameUtils.getPropertyName(className) %>
                ${propertyName}.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: '${domainClass.propertyName}.label', default: '${className}')] as Object[],
                          "Another user has updated this ${className} while you were editing")
                render(view: "edit", model: [${propertyName}: ${propertyName}])
                return
            }
        }

        ${propertyName}.properties = params

        if (!${propertyName}.save(flush: true)) {
            render(view: "edit", model: [${propertyName}: ${propertyName}])
            return
        }
		
		flash.message = message(code: 'default.updated.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), ${propertyName}.id])
		
		//redirect(action: "show", id: ${propertyName}.id)
		redirectToUrlWithTenant([action: "edit", id: ${propertyName}.id])
    }

    def delete() {
        def ${propertyName} = ${className}.get(params.id)
        if (!${propertyName}) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            redirectToUrlWithTenant([action: "list"])
            return
        }

        try {
            ${propertyName}.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            redirectToUrlWithTenant([action: "list"])
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: '${domainClass.propertyName}.label', default: '${className}'), params.id])
            redirectToUrlWithTenant([action: "show", id: params.id])
        }
    }
}
