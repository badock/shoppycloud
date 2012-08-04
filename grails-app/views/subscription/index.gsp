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

		email = $("#input_email").val();
		var xdr = getXDomainRequest();
		xdr.onload = function() {
			var data = xdr.responseText;
			var jsonData = jQuery.parseJSON(data);
			
			if(jsonData.result) {
				$("#input_email").val(email);
				$("#span_email_check").text(email);
				impress().goto("choose-domain");
			}
			else {
				alert("The email is already used!");
			}
		}

		xdr.open("GET", "/subscription/checkemail/"+$("#input_email").val());
		xdr.send();
	}

	function doCheckDomain() {

		domain = $("#input_domain").val();
		var xdr = getXDomainRequest();
		xdr.onload = function() {
			var data = xdr.responseText;
			var jsonData = jQuery.parseJSON(data);

			if(jsonData.result) {
				$("#input_domain").val(domain);
				$("#span_domain_check").text(domain);
				impress().goto("last-checking");
			}
			else {
				alert("The domain name already exist!");
			}
		}

		xdr.open("GET", "/subscription/checkdomain/"+$("#input_domain").val());
		xdr.send();
		
	}

	function doCheckFinal() {

		domain = $("#input_domain").val();
		var xdr = getXDomainRequest();
		xdr.onload = function() {
			var data = xdr.responseText;
			var jsonData = jQuery.parseJSON(data);
			
			if(jsonData.result) {
				impress().goto("congratulations")
			}
			else {
				alert("An error occured during the creation of your account.");
			}
		}

		xdr.open("GET", "/subscription/checkoverall/"+$("#input_email").val()+"-"+$("#input_domain").val());
		xdr.send();
		
	}
	
</script>
</head>
<body>
	<div id="impress">
	
		<div id="welcome" class="step" data-x="-1500" data-y="2500">
			<div id="cloud"><span class="shadow"></span></div>
			<q><b>Try it now!</b></q>
			<br/>
			<input id="input_email" type="text" style="font-size:35px; width:550px;" placeholder="email"/>
			<a style="margin-left: 10px;" onclick="doCheckEmail();">go!</a>
		</div>
		
		<div id="choose-domain" class="step" data-x="0" data-y="0" data-scale="4">
			<span class="try"><b>now choose a subdomain</b></span>
			<h1>
				<input id="input_domain" type="text" style="font-size:35px;" placeholder="subdomain"/>.shoppycloud.com
			</h1>
			<a onclick="doCheckDomain();">validate!</a>
		</div>
		
		<div id="last-checking" class="step" data-x="1050" data-y="4500" data-rotate="90"
			data-scale="5">
			<span class="try"><b>Are those information correct?</b></span>
			<h1>
				your email is <span id="span_email_check"></span>
				and you want <span id="span_domain_check"></span>.shoppycloud.com
			</h1>
			<a onclick="doCheckFinal();">I confirm!</a>
		</div>
		
		<div id="congratulations" class="step" data-x="1500" data-y="15000" data-rotate="90"
			data-scale="5">
			<p style="text-align:center;">
				<b>Congratulations!</b>
				<br/>
				You will receive soon an email with a link to your shop!
				<br/>
			</p>
		</div>

	</div>
</body>
</html>
