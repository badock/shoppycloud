<div class="span3">
	<div class="well sidebar-nav">
		<ul class="nav nav-list">
			<li class="nav-header">categories</li>
			<g:each var="category" in="${categories}">
				
				<g:if test="${parentProductClass!=null && category.id==parentProductClass.id }"><li class="active"></g:if><g:else><li></g:else>
					<g:link class="list" action="show_category"
						params="[id:category.id]">
						<i class="icon-list"></i>
						${category.name}
					</g:link></li>
			</g:each>
		</ul>
	</div>
</div>