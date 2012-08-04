import storecloud.CustomTenantResolver;

// Place your Spring DSL code here
beans = {
//	multiTenantFilter(grails.plugin.multitenant.core.MultiTenantFilter)
	tenantFilter(grails.plugin.multitenant.core.servlet.CurrentTenantServletFilter)
	tenantResolver(CustomTenantResolver)
}


// Activate these bean definitions
// Documentation http://grails.org/doc/latest/guide/single.html#14.2%20Configuring%20Additional%20Beans
// tenantResolver(storecloud.CustomTenantResolver)
// tenantRepository(storecloud.TenantRepository)


// Activate these bean definitions
// Documentation http://grails.org/doc/latest/guide/single.html#14.2%20Configuring%20Additional%20Beans
// tenantResolver(storecloud.CustomTenantResolver)
// tenantRepository(storecloud.TenantRepository)
