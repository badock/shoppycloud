<meta name="layout" content="customer">
<h1>Your personal informations</h1>
<hr/>
<g:form class="form-horizontal" controller="customerManagement" action="updateInformations">
	<fieldset>
		<div class="control-group">
            <label class="control-label" for="inputFirstName">First Name</label>
            <div class="controls">
              <input name="firstname" type="text" class="input-xlarge" id="inputFirstName" value="${user.firstName }"/>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label" for="inputLastName">Last Name</label>
            <div class="controls">
              <input name="lastname" type="text" class="input-xlarge" id="inputLastName" value="${user.lastName }"/>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label" for="inputEmail">Email</label>
            <div class="controls">
              <input name="email" type="text" class="input-xlarge" id="inputEmail" value="${user.email }"/>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label" for="inputAddress">Address</label>
            <div class="controls">
              <input name="address" type="text" class="input-xlarge" id="inputAddress" value="${user.address }"/>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label" for="inputCity">City</label>
            <div class="controls">
              <input name="city" type="text" class="input-xlarge" id="inputCity" value="${user.city }"/>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label" for="inputPostalCode">Postal Code</label>
            <div class="controls">
              <input name="postalcode" type="text" class="input-xlarge" id="inputPostalCode" value="${user.postalCode }"/>
            </div>
        </div>
		<div class="control-group">
            <label class="control-label" for="inputCountry">Country</label>
            <div class="controls">
              <input name="country" type="text" class="input-xlarge" id="inputCountry" value="${user.country }"/>
            </div>
        </div>
		
		<div class="form-actions">
            <g:submitButton class="btn btn-primary" name="Apply changes"/>
        </div>
		
		
	</fieldset>
</g:form>