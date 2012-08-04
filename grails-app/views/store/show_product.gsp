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
					<li class="nav-header">parent category</li>
					
					<g:if test="${ parentProductClass.id == category.id}">
						<li class="active">
						<g:link class="list" action="show_category" params="[id:parentProductClass.id]">
							<i class="icon-list"></i>
							${parentProductClass.name}
						</g:link>
						</li>
					</g:if>
					<g:else>
						<li>
						<g:link class="list" action="show_category" params="[id:parentProductClass.id]">
							<i class="icon-list"></i>
							${parentProductClass.name}
						</g:link>
						</li>
					</g:else>
					
					<li class="nav-header">sub categories</li>
					<g:each var="category_" in="${subs}">
						
						<g:if test="${ category_.id == category.id}">
							<li class="active">
							<g:link class="list" action="show_category" params="[id:category_.id]">
								<i class="icon-list"></i>
								${category_.name}
							</g:link>
							</li>
						</g:if>
						<g:else>
							<li>
							<g:link class="list" action="show_category" params="[id:category_.id]">
								<i class="icon-list"></i>
								${category_.name}
							</g:link>
							</li>
						</g:else>
						
					</g:each>
				</ul>
			</div>
		</div>
		
		<div class="span9">
		
			<div class="page-header">
		    	<h1>${product.name } <small>${product.technicalSpecs }</small></h1>
		  	</div>	
			
			<div class="product-description-preview">
			
				<div class="span2">
					<figure class="gallery-icon">
						<img class="product-photo" src="${product.pictureUrl }" alt="${product.name }" title="${product.name }">
					</figure>
				</div>
				<div class="">
					<span class="price"><g:formatNumber number="${product.price }" format="#####0.00€" /></span>
					<br/>
					<br/>
					<br/>
					<g:form controller="store" action="add_to_cart" id="add-cart-form">
						<g:hiddenField name="productid" value="${product.id }"/>
						<button class="add-to-cart-button btn btn-success" onclick="function(){$(\"#add-cart-form\")[0].submit()}">
							<span><i class="icon-shopping-cart icon-white"></i><b> Add to Cart</b></span>
						</button> 
						
						<br/> 
						<br/> 
						<br/>
						<br/>
						<div id="container">				    
						    <a href="#" class="s3d twitter addthis_button_preferred_2 addthis_button_twitter at300b">
						        Twitter
						        <span class="icons twitter"></span>
						    </a>
						    
						    <a href="#" class="s3d facebook addthis_button_preferred_1 addthis_button_facebook at300b">
						        Facebook
						        <span class="icons facebook"></span>
						    </a>
						</div>
					</g:form>
					<!-- AddThis Button BEGIN -->
	<%--				<div class="addthis_toolbox addthis_default_style ">--%>
	<%--				<a href="http://www.addthis.com/bookmark.php?v=250&amp;pubid=xa-4f7a329c299cccdb" class="addthis_button_compact">Share</a>--%>
	<%--				<span class="addthis_separator">|</span>--%>
	<%--				<a class="addthis_button_preferred_1"></a>--%>
	<%--				<a class="addthis_button_preferred_2"></a>--%>
	<%--				<a class="addthis_button_preferred_3"></a>--%>
	<%--				<a class="addthis_button_preferred_4"></a>--%>
	<%--				</div>--%>
					<script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=xa-4f7a329c299cccdb"></script>
					<!-- AddThis Button END -->
						
				</div>
			</div>
						
			<div class="container-fluid">
				<br/>
				<br/>
				<hr>
				<span id="description" class="description">${product.markdownDescription }</span>
			</div>
		</div>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'showdown.js')}"></script>
		<script type="text/javascript">	
			function convert() {
				
				var converter = new Showdown.converter();
			  
			    var txt = $("#description").html();
			    var html = converter.makeHtml(txt);
			    $("#description").html(html)
			}
			
			$(function() {			  
			  convert();
			});
		</script>
	</body>
</html>