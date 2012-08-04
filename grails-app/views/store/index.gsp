<%@page import="storecloud.ProductMainClass"%>
<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	
	<body>
		<div class="span3">
			<div class="well sidebar-nav">
				<ul class="nav nav-list">
					<li class="nav-header">categories</li>
					<g:each var="category" in="${categories}">
						<li>
							<g:link class="list" action="show_category" params="[id:category.id]">
								<i class="icon-list"></i>
								${category.name}
							</g:link>
						</li>
					</g:each>
				</ul>
			</div>
		</div>
		
		<div class="span9">
			<div class="hero-unit">
			  <h1>${store.slogan }</h1>
			  <p>${store.shortDescription }</p>
			  <p><g:link class="btn btn-primary btn-large" controller="store" action="show">Visit the store</g:link></p>
			</div>
			<div class="row-fluid">
			  <div class="span4">
			    <h2>Secure payment</h2>
			    <p>We use Paypal, which is a very secured payment system. This system encrypts your confidential information in transit between our store an the Paypal site. It will then resides on a server that is heavily guarded.</p>
			    <p><a class="btn" href="#">View details &raquo;</a></p>
			  </div><!--/span-->
			  <div class="span4">
			    <h2>At your service!</h2>
			    <p>If you have any questions or suggestions about one product in our store, you can send us an email or directly contact one of our sellers with our chat application. We will be proud to give you an answer as soon as possible</p>
			    <p><a class="btn" href="#">View details &raquo;</a></p>
			  </div><!--/span-->
			  <div class="span4">
			    <h2>Fast delivery</h2>
			    <p>We you will validate your order, we will prepare it immediately. We work with many fast package delivery companies. You will receive your package as soon as possible! Please contact us to know is the fast shipping is available in your country.</p>
			    <p><a class="btn" href="#">View details &raquo;</a></p>
			  </div><!--/span-->
			</div><!--/row-->
		</div>
	</body>
</html>          