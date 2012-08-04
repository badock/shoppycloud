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
		    	<h1>${category.name } <small>List of products</small></h1>
		  		</div>
				
				<table class="table table-striped">
					<thead>
						<td>name</td>
						<td>price</td>
					</thead>
					<tbody>
					<%
						int i =0;
					%>
					<g:each var="product" in="${products}">
						<tr class="\${(i % 2) == 0 ? 'even' : 'odd'}">
							<td>${product.name }</td>
							<td><g:formatNumber number="${product.price }" format="#####0.00€" /></td>
							<td><g:link action="show_product" in="${product.id}" params="[id: product.id]">Show</g:link></td>
						</tr>
						<%
							i++;
						%>
					</g:each>
					</tbody>
				</table>
		
		</div>
	</body>
</html>

