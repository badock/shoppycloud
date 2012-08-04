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
			<div class="page-header">
		    	<h1>Please select a category <small>here is a list of products</small></h1>
		  	</div>
		  		<div class="product-views">
				<g:each var="product" in="${ products }">
					<div class="product-preview well">
				    	<h2>${product.name }</h2>
				    	<br/>
				    		<figure class="gallery-icon">
								<img class="product-preview-photo" src="${product.pictureUrl }" alt="${product.name }" title="${product.name }">
							</figure>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>
						<br/>						
						<br/>
						<br/>
						<p>
							<span class="product-preview-price"><g:formatNumber number="${product.price }" format="#####0.00€" /></span>
							<br/>
							<br/>
							<g:link class="btn" action="show_product" in="${product.id}" params="[id: product.id]">View product</g:link>
						</p>
				  	</div><!--/span-->
				</g:each>
				</div>
		</div>
	</body>
</html>   