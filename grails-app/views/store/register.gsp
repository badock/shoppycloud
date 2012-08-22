<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	
		<g:render template="category_list"/>
		
		<div class="span9">
			
				<h1>Create a customer account on ${store.name }</h1>
				<br/>
				<br/>
				<g:form class="form-horizontal" controller="store" action="doRegistration">
					<fieldset>
						<div class="control-group">
				            <label class="control-label" for="inputFirstName">First Name</label>
				            <div class="controls">
				              <input name="firstname" type="text" class="input-xlarge" id="inputFirstName" value="${firstname }"/>
				            </div>
				        </div>
						<div class="control-group">
				            <label class="control-label" for="inputLastName">Last Name</label>
				            <div class="controls">
				              <input name="lastname" type="text" class="input-xlarge" id="inputLastName" value="${lastname }"/>
				            </div>
				        </div>
						<div class="control-group">
				            <label class="control-label" for="inputEmail">Email</label>
				            <div class="controls">
				              <input name="email" type="text" class="input-xlarge" id="inputEmail" value="${email }"/>
				            </div>
				        </div>
						<div class="control-group">
				            <label class="control-label" for="inputAddress">Address</label>
				            <div class="controls">
				              <input name="address" type="text" class="input-xlarge" id="inputAddress" value="${address }"/>
				            </div>
				        </div>
						<div class="control-group">
				            <label class="control-label" for="inputCity">City</label>
				            <div class="controls">
				              <input name="city" type="text" class="input-xlarge" id="inputCity" value="${city }"/>
				            </div>
				        </div>
						<div class="control-group">
				            <label class="control-label" for="inputPostalCode">Postal Code</label>
				            <div class="controls">
				              <input name="postalcode" type="text" class="input-xlarge" id="inputPostalCode" value="${postalcode }"/>
				            </div>
				        </div>
						<div class="control-group">
				            <label class="control-label" for="inputCountry">Country</label>
				            <div class="controls">
				              <input name="country" type="text" class="input-xlarge" id="inputCountry" value="${country }"/>
				            </div>
				        </div>
						
						<div class="form-actions">
				            <g:submitButton class="btn btn-primary" name="Register"/>
				        </div>
						
						
					</fieldset>
			</g:form>
						
		</div>
	</body>
</html>

