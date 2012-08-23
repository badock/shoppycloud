package storecloud

import grails.plugin.multitenant.core.resolve.TenantResolver
import grails.plugin.multitenant.core.exception.TenantResolveException;
import javax.servlet.http.HttpServletRequest;

/**
 * TODO: Implement me!
 *
 * @see http://multi-tenant.github.com/grails-multi-tenant-single-db/
 */
class CustomTenantResolver implements TenantResolver {

    Integer resolve(HttpServletRequest request) throws TenantResolveException {
		
		if(request.serverName.count(".") != 2) {
			
			request.store = -1
			request.domain = "StoreApp"
			return 0
			
		}
		else {
			
			def storeName = request.serverName.substring(0, request.serverName.indexOf("."))
			
			Shop s = Shop.find("from Shop as e where e.domain = '"+storeName+"'")
			request.store = s.id
			request.domain = storeName
			return s.id
		}
    }

}