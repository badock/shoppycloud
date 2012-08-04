<script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-collapse.css')}"></script>
<meta name="layout" content="customer">
<h1>Your orders</h1>
<hr/>
<g:each var="order" in="${ user.orders }">
	<div class="accordion-group">
		<div class="accordion-heading" id="accordion${order.id }">
			<table style="width: 100%;">
				<tr>
					<td>
						<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion${order.id }" href="#collapse${order.id }">
						  Order #${order.id }
						</a>
				  	</td>
				  	<td>
						<div class="pull-right" style="margin-right: 15px;">
						<% Double total=0; order.orders.each{o -> total+= o.quantity*o.price}; %>
						<b><g:formatNumber number="${total}" format="###,##0.00€" /></b>
						</div>
				  	</td>
				</tr>
		  	</table>
		</div>
		<div id="collapse${order.id }" class="accordion-body collapse" style="height: 0px; ">
		    <div class="accordion-inner">
		    	<ul>
				      <g:each var="productOrder" in="${ order.orders }">
				      	<li><g:link controller="store" action="show_product" id="${productOrder.product.id }">${ productOrder.product.name}</g:link> : <ul><li><i>quantity:</i> <b>${ productOrder.quantity}</b></li> <li><i>unit-price:</i> <b><g:formatNumber number="${productOrder.price}" format="###,##0.00€" /></b></li> <li><i>total:</i> <b><g:formatNumber number="${productOrder.price*productOrder.quantity}" format="###,##0.00€" /></b></li></ul></li> 	
				      </g:each>
				</ul>
		    </div>
		</div>
	</div>
</g:each>

<script type="text/javascript">

//$(".collapse").collapse()

</script>