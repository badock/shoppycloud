<% import grails.persistence.Event %>
<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
		<style type="text/css">
			dd {
				text-overflow:ellipsis;
				white-space : normal;
				word-wrap:break-word;
			}
		</style>
		<script type="text/javascript">	
			function convert() {
				
				var converter = new Showdown.converter();
			  
			    var txt = ${'$'}("#markdown-description").html();
			    var html = converter.makeHtml(txt);
			    ${'$'}("#markdown-description").html(html)
			}

			function displayMarkDown() {
				var domModal = ${'$'}('#myModal').modal({
			        backdrop: true, //Show a grey back drop
			        //closeOnEscape: true, //Can close on escapes
			        modal: true, //display it as a modal
			    });
			}
			
			${'$'}(function() {			  
				convert();

				//displayMarkDown();
			});
		</script>
	</head>
	<body>
				
		<div class="span3">
			<div class="well sidebar-nav">
				<ul class="nav nav-list">
					<li class="nav-header">${domainClass.propertyName}</li>
					<li>
						<g:link class="list" action="list">
							<i class="icon-list"></i>
							<g:message code="default.list.label" args="[entityName]" />
						</g:link>
					</li>
					<li>
						<g:link class="create" action="create">
							<i class="icon-plus"></i>
							<g:message code="default.new.label" args="[entityName]" />
						</g:link>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="span9">
			<div id="show-${domainClass.propertyName}" class="content scaffold-show" role="main">
				<div class="page-header">	
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
					<g:if test="\${flash.message}">
					<div class="message" role="status">\${flash.message}</div>
					</g:if>
				</div>
				<dl>
				<%  excludedProps = Event.allEvents.toList() << 'id' << 'version'
					allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
					props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) }
					Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
					props.each { p -> %>
					<g:if test="\${${propertyName}?.${p.name}}">
						<dt><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></dt>
						<%  if (p.isEnum()) { %>
							<dd><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></dd>
						<%  } else if (p.oneToMany || p.manyToMany) { %>
							<g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
							<dd><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></dd>
							</g:each>
						<%  } else if (p.manyToOne || p.oneToOne) { %>
							<dd><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link></dd>
						<%  } else if (p.type == Boolean || p.type == boolean) { %>
							<dd><g:formatBoolean boolean="\${${propertyName}?.${p.name}}" /></dd>
						<%  } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
							<dd><g:formatDate date="\${${propertyName}?.${p.name}}" /></dd>
						<%  } else if(p.name.toLowerCase().contains("price")) { %>
							<dd><g:formatNumber number="\${${propertyName}?.${p.name}}" format="#####0.00€" /></dd>
						<%  } else if(p.name.startsWith("markdown")) { %>
							<dd>
							
							<a class="" href="#myModal" data-toggle="modal">click to preview "<g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" />"</a></dd>
							<div class="modal hide fade in" id="myModal">
					            <div class="modal-header">
					              <a data-dismiss="modal" class="close">×</a>
					              <h3><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></h3>
					            </div>
					            <div class="modal-body">
									<div id="markdown-description"><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></div>
					            </div>
					            <div class="modal-footer">
					              <a data-dismiss="modal" class="btn" href="#">Close</a>
					            </div>
					          </div>
						<%  } else if(p.name.startsWith("pictureUrl")) { %>
						  <dd>
						  
						  <a class="" href="#myPictureModal" data-toggle="modal">click to preview "<g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" />"</a></dd>
						  <div class="modal hide fade in" id="myPictureModal">
							  <div class="modal-header">
								<a data-dismiss="modal" class="close">×</a>
								<h3><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></h3>
							  </div>
							  <div class="modal-body">
							  	<figure class="gallery-icon">
								  <img class="product-photo" alt="\${${propertyName}?.${p.name}}" title="\${${propertyName}?.${p.name}}" src="\${${propertyName}?.${p.name}}"/></img>
								</figure>
							  </div>
							  <div class="modal-footer">
								<a data-dismiss="modal" class="btn" href="#">Close</a>
							  </div>
							</div>
						<%  } else if(!p.type.isArray()) { %>
							<dd><g:fieldValue bean="\${${propertyName}}" field="${p.name}"/></dd>
						<%  } %>
					</g:if>
				<%  } %>
				</dl>
				<g:form>
					<div class="form-actions">
						<g:hiddenField name="id" value="\${${propertyName}?.id}" />
						<g:link class="btn" action="edit" id="\${${propertyName}?.id}"><i class="icon-pencil"></i><g:message code="default.button.edit.label" default="Edit" /></g:link>
						<g:link class="btn btn-danger" action="delete" onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" id="\${${propertyName}?.id}"><i class="icon-trash icon-white"></i><g:message code="default.button.delete.label" default="Delete" /></g:link>						
					</div>
				</g:form>
			</div>
		</div>
		
	</body>
</html>
