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
//        String host = request.getServerName() 
//        switch (host) { 
//            case "app1.storeapp.com": 
//                return 1
//            case "app2.storeapp.com": 
//                return 2
//            case "app3.storeapp.com":
//                return 3
//
//            default:
//                // WARNING: Returning null will disable the Hibernate filter
//                // don't do this unless you know what you're doing! 
//                return null  
//        }
		def storeName = request.serverName.substring(0, request.serverName.indexOf("."))
		
		if((storeName.equals("storeapp") || storeName.equals("localhost"))) {
			request.store = 0
			request.domain = "StoreApp"
			return 0
		}
		else {
			
			Shop s = Shop.find("from Shop as e where e.domain = '"+storeName+"'")
			request.store = s.id
			request.domain = storeName
			return s.id
		}
    }

}