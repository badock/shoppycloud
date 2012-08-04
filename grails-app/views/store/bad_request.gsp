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
		    	<h1>Oooops, your request could not be processed :(</h1>
		  		</div>
						
		</div>
	</body>
</html>

