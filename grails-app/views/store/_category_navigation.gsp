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