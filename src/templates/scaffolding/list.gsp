<% import grails.persistence.Event %>
<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<div class="span3">
			<div class="well sidebar-nav">
				<ul class="nav nav-list">
					<li class="nav-header">${domainClass.propertyName}</li>
					<li class="active">
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
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
					<g:if test="\${flash.message}">
					<div class="message" role="status">\${flash.message}</div>
					</g:if>
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
						<%  excludedProps = Event.allEvents.toList() << 'id' << 'version' << 'pictureUrl' << 'technicalSpecs' << 'description' << 'markdownDescription'
							allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
							props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) && it.type != null && !Collection.isAssignableFrom(it.type) }
							Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
							props.eachWithIndex { p, i ->
								if (i < 15) {
									if (p.isAssociation()) { %>
							<th><g:message code="${domainClass.propertyName}.${p.name}.label" default="${p.naturalName}" /></th>
						<%      } else if (!p.name.toLowerCase().equals("password") && !p.name.toLowerCase().contains("tenant")) { %>
							<g:sortableColumn property="${p.name}" title="\${message(code: '${domainClass.propertyName}.${p.name}.label', default: '${p.naturalName}')}" />
						<%  }   }   } %>
						</tr>
					</thead>
					<tbody>
					<g:each in="\${${propertyName}List}" status="i" var="${propertyName}">
						<tr class="\${(i % 2) == 0 ? 'even' : 'odd'}" style="text-overflow:ellipsis;">
						<%  props.eachWithIndex { p, i ->
								if (p.type == Boolean || p.type == boolean) { %>
							<td><g:formatBoolean boolean="\${${propertyName}.${p.name}}" /></td>
						<%          } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
							<td><g:formatDate date="\${${propertyName}.${p.name}}" /></td>
						<%          } else if (p.name.toLowerCase().contains("price")) { %>
							<td><g:formatNumber number="\${${propertyName}?.${p.name}}" format="#####0.00€" /></td>
						<%          } else if (p.name.toLowerCase().equals("password") || p.name.toLowerCase().contains("tenant")) { %>
						<%          } else { %>
							<td>\${fieldValue(bean: ${propertyName}, field: "${p.name}")}</td>
						<%  }   } %>
							<td><g:link action="edit" id="\${${propertyName}.id}">edit</g:link></td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="\${${propertyName}Total}" />
				</div>
			</div>
		</div>
	</body>
</html>
