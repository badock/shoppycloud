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
			  
<%--			  <div id="myCarousel" class="carousel slide">--%>
<%--				  <!-- Carousel items -->--%>
<%--				  <div class="carousel-inner">--%>
<%--				    <div class="active item">…</div>--%>
<%--				    <div class="item">…</div>--%>
<%--				    <div class="item">…</div>--%>
<%--				  </div>--%>
<%--				  <!-- Carousel nav -->--%>
<%--				  <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>--%>
<%--				  <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>--%>
<%--				</div>--%>
			  
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
			
			<div class="page-header">
		    	<h1>Shop <small>Our selection</small></h1>
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
	<script type="text/javascript">
		$('.carousel').carousel({
		  interval: 2000
		})
	</script>
</html>          