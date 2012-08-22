<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<g:render template="category_list_navigation"/>
		
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

