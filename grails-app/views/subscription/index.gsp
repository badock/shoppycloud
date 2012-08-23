<%=packageName%>
<!doctype html>
<html>
<head>
<meta name="layout" content="subscription">
<title><g:message code="default.list.label" args="[entityName]" /></title>
<script type="text/javascript">

	function getXDomainRequest() {
		var xdr = null;
		
		if (window.XDomainRequest) {
			xdr = new XDomainRequest(); 
		} else if (window.XMLHttpRequest) {
			xdr = new XMLHttpRequest(); 
		} else {
			alert("Your browser doesn't support XDomainRequest, please upgrade to a recent browser!");
		}
		
		return xdr;	
	}

	var email = "";
	var domain = "";
	
	function doCheckEmail() {

		email = $("#input-email").val();
		var xdr = getXDomainRequest();
		xdr.onload = function() {
			var data = xdr.responseText;
			var jsonData = jQuery.parseJSON(data);
			
			if(jsonData.result) {
				$("#control-group-email").removeClass("error")
				$("#control-group-email").addClass("success")
			}
			else {
				$("#control-group-email").removeClass("success")
				$("#control-group-email").addClass("error")
			}
		}

		xdr.open("GET", "/subscription/checkemail/"+$("#input-email").val());

		if(email.length>2) {
			xdr.send();
		}
	}

	function doCheckDomain() {

		domain = $("#input-domain").val();
		var xdr = getXDomainRequest();
		xdr.onload = function() {
			var data = xdr.responseText;
			var jsonData = jQuery.parseJSON(data);

			if(jsonData.result) {
				$("#control-group-domain").removeClass("error")
				$("#control-group-domain").addClass("success")
			}
			else {
				$("#control-group-domain").removeClass("success")
				$("#control-group-domain").addClass("error")
			}
		}

		xdr.open("GET", "/subscription/checkdomain/"+$("#input-domain").val());

		if(domain.length>2) {
			xdr.send();
		}		
	}

	function doCheckFinal() {

		domain = $("#input-domain").val();
		var xdr = getXDomainRequest();
		xdr.onload = function() {
			var data = xdr.responseText;
			var jsonData = jQuery.parseJSON(data);
			
			if(jsonData.result) {
			}
			else {
				alert("An error occured during the creation of your account.");
				doCheckEmail();
				doCheckDomain();
			}
		}

		xdr.open("GET", "/subscription/checkoverall/"+$("#input-email").val()+"-"+$("#input-domain").val()+"-"+$("#input-password").val());

		if(domain.length>2) {
			xdr.send();
		}		
	}
	
</script>
</head>
<body>

	<div class="main-content">
		<h1>Create your ecommerce website in less than 5 minutes
			<small>: Create an account and select an available name for your store... congratulations, you are ready for the ecommerce!</small>
		</h1>
		
		<div class="creation-form-div well">
			<div id="control-group-domain" class="control-group">
				<input id="input-domain" type="text" class="input-xlarge" placeholder="Your store name" onblur="doCheckDomain();"/>
			</div>
			<br/>
			<div id="control-group-email" class="control-group">
				<input id="input-email" type="text" class="input-xlarge" placeholder="Email" onblur="doCheckEmail();"/>
			</div>
			<br/>
			<div id="control-group-password" class="control-group">
				<input id="input-password" type="password" class="input-xlarge" placeholder="Password"/>
			</div>
			<br/>
			<a class="btn btn-success btn-large" onclick="doCheckFinal();">Create your store now!</a>
		</div>
	</div>

</body>
</html>
