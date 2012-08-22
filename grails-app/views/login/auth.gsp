<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="default.list.label" args="[entityName]" /></title>

		<!-- Le styles -->
		<style type="text/css">
		/* Override some defaults */
		html,body {
			background-color: #eee;
		}
		
		body {
			padding-top: 40px;
		}
		
		.container {
			margin-top: 50px;
			width: 300px;
		}
		
		/* The white background content wrapper */
		.container>.content {
			background-color: #fff;
			padding: 20px;
			margin: 0 -20px;
			-webkit-border-radius: 10px 10px 10px 10px;
			-moz-border-radius: 10px 10px 10px 10px;
			border-radius: 10px 10px 10px 10px;
			-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
			-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
			box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
		}
		
		.login-form {
			margin-left: 65px;
		}
		
		legend {
			margin-right: -50px;
			font-weight: bold;
			color: #404040;
		}
		</style>
	</head>
	<body>
		 <div class="container">
	     	<div class="content">
	        	<div class="row">
	            	<div class="login-form">
	            		
	            		<div class="fieldcontain"> 
		            		<ul class="nav nav-pills">
								<li class="active">
							  		<a href="#editor-tab" data-toggle="tab">I am registered</a>
							  	</li>
							  	<li>
							  		<a href="#preview-tab" data-toggle="tab">I want to register</a>
							  	</li>
							</ul>

							<div id="myTabContent" class="tab-content">
								<div class="tab-pane fade active in" id="editor-tab">
	
									<h2>Sign up</h2>
									<br />
									<g:if test='${flash.message}'>
										<div class='login_message'>
											${flash.message}
										</div>
									</g:if>
	
									<form action='${postUrl}' method='POST' id='loginForm'
										class='cssform' autocomplete='off'>
										<p>
											<label for='username'>Email:</label> <input type='text'
												class='text_' name='j_username' id='username' />
										</p>
	
										<p>
											<label for='password'><g:message
													code="springSecurity.login.password.label" />:</label> <input
												type='password' class='text_' name='j_password' id='password' />
										</p>
	
										<p id="remember_me_holder">
											<input type='checkbox' class='chk'
												name='${rememberMeParameter}' id='remember_me'
												<g:if test='${hasCookie}'>checked='checked'</g:if> />
											<g:message code="springSecurity.login.remember.me.label" />
										</p>
	
	
										<input type='hidden' name='spring-security-redirect'
											value='${params['spring-security-redirect']}' />
										<p>
											<input class="btn btn-primary btn-large" type='submit'
												id="submit"
												value='${message(code: "springSecurity.login.button")}' />
										</p>
	
									</form>
								</div>
								<div class="tab-pane fade" id="preview-tab">
									<h2>Register</h2>
									<br />
									<p>
										<g:link class="btn btn-primary btn-large" controller="store"
											action="register">Go to registration form</g:link>
									</p>
								</div>
							</div>

						</div>

					</div>	
						<script type='text/javascript'>
							<!--
							(function() {
								document.forms['loginForm'].elements['j_username'].focus();
							})();
							// -->
						</script>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
