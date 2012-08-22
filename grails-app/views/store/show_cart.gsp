<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		
		
      	<g:render template="category_list"/>
      		
		<div class="span9">
			
				
				<div class="page-header">
		    	<h1>Your cart <small>List of selected products</small></h1>
		  		</div>
		  		
				<g:if test="${ session["cart"] != null && !session["cart"].isEmpty()}">
				
					<g:form controller="store">
						<table class="table table-bordered table-striped">
						  <thead>
						      <tr>
						        <th>Description</th>
						        <th>Price</th>
						        <th>Quantity</th>
						        <th>Delete</th>
						        <th>Total</th>
						      </tr>
						    </thead>
						    <tbody>
						     <g:each var="cartItem" in="${session["cart"]}">
							
								<tr>
									<td>${session["productindex"].get(cartItem.key).name}</td>
									<td>${session["productindex"].get(cartItem.key).price}€</td>
									<td><input name="product-${cartItem.key}" type="text" value="${cartItem.value}"></td>
									<td>
										<g:form>
											<g:hiddenField name="delete-id" value="${cartItem.key}"/>
											<g:actionSubmit action="delete_product_from_chart" class="btn btn-danger" href="#" value="Delete"/>
										</g:form>
									</td>
									<td>
									
									<% Double prix = session["productindex"].get(cartItem.key).price*cartItem.value %>
									<g:formatNumber number="${prix}" format="###,##0.00€" />
									</td>
								</tr>	
							</g:each> 
						    </tbody>
						  </table>
						
						<div class="pull-right" style="margin-right: 15px;">
						<% Double total=0; session["cart"].each{c -> total+= session["productindex"].get(c.key).price*c.value}; %>
						<b>total: <g:formatNumber number="${total}" format="###,##0.00€" /></b>
						</div>
						<br/>
							
						<div class="form-actions">
							<g:actionSubmit action="update_cart" class="btn btn-primary" value="Update cart"/>
							<g:link controller="payment" action="checkout_cart" class="btn btn-success" >Check-out</g:link>
<%--							<g:actionSubmit />--%>
						</div>
					</g:form>
				</g:if>
				<g:else>
					No item in cart.
				</g:else>
						
		</div>
	</body>
</html>

