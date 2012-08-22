<%@ page import="storecloud.Shop" %>



<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'domain', 'error')} ">
	<label for="domain">
		<g:message code="shop.domain.label" default="Domain" />
		
	</label>
	<g:textField name="domain" value="${shopInstance?.domain}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="shop.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${shopInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'slogan', 'error')} ">
	<label for="slogan">
		<g:message code="shop.slogan.label" default="Slogan" />
		
	</label>
	<g:textField name="slogan" value="${shopInstance?.slogan}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'shortDescription', 'error')} ">
	<label for="shortDescription">
		<g:message code="shop.shortDescription.label" default="Short Description" />
		
	</label>
	<g:textField name="shortDescription" value="${shopInstance?.shortDescription}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'paypalAccount', 'error')} ">
	<label for="paypalAccount">
		<g:message code="shop.paypalAccount.label" default="Paypal Account" />
		
	</label>
	<g:textField name="paypalAccount" value="${shopInstance?.paypalAccount}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'paypalPassword', 'error')} ">
	<label for="paypalPassword">
		<g:message code="shop.paypalPassword.label" default="Paypal Password" />
		
	</label>
	<g:textField name="paypalPassword" value="${shopInstance?.paypalPassword}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'paypalApiKey', 'error')} ">
	<label for="paypalApiKey">
		<g:message code="shop.paypalApiKey.label" default="Paypal Api Key" />
		
	</label>
	<g:textField name="paypalApiKey" value="${shopInstance?.paypalApiKey}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'paypalProductionMode', 'error')} ">
	<label for="paypalProductionMode">
		<g:message code="shop.paypalProductionMode.label" default="Paypal Production Mode" />
		
	</label>
	<g:checkBox name="paypalProductionMode" value="${shopInstance?.paypalProductionMode}" />
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'paypalIsConfigured', 'error')} ">
	<label for="paypalIsConfigured">
		<g:message code="shop.paypalIsConfigured.label" default="Paypal Is Configured" />
		
	</label>
	<g:checkBox disabled="true" name="paypalIsConfigured" value="${shopInstance?.paypalIsConfigured}" />
</div>

<div class="fieldcontain ${hasErrors(bean: shopInstance, field: 'theme', 'error')} ">
	<label for="theme">
		<g:message code="shop.theme.label" default="Theme" />
		
	</label>
	<g:select name="theme" from="${shopInstance.constraints.theme.inList}" value="${shopInstance?.theme}" valueMessagePrefix="shop.theme" noSelection="['': '']"/>
</div>

