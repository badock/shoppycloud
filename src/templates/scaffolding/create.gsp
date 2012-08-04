<%=packageName%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="admin">
		<g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
		<script type="text/javascript">	
			function convert() {
				
				var converter = new Showdown.converter();
			  
			    var txt = ${'$'}("#markdown-editor").val();
			    var html = converter.makeHtml(txt);
			    ${'$'}("#result").html(html)
			}
			
			${'$'}(function() {
			  var converter = new Showdown.converter();
			  ${'$'}("#markdown-editor").keyup(function(){
			    convert();
			  });
			  
			  convert();
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
					<h1><g:message code="default.new.label" args="[entityName]" /></h1>
					<g:if test="\${flash.message}">
					<div class="message" role="status">\${flash.message}</div>
					</g:if>
				</div>
				
				<g:hasErrors bean="\${${propertyName}}">
				<ul class="errors" role="alert">
					<g:eachError bean="\${${propertyName}}" var="error">
					<li <g:if test="\${error in org.springframework.validation.FieldError}">data-field-id="\${error.field}"</g:if>><g:message error="\${error}"/></li>
					</g:eachError>
				</ul>
				</g:hasErrors>
				<g:form action="save" <%= multiPart ? ' enctype="multipart/form-data"' : '' %>>
					<fieldset class="form">
						<g:render template="form"/>
					</fieldset>
					<fieldset class="buttons">
						<g:submitButton name="create" class="btn btn-primary" value="\${message(code: 'default.button.create.label', default: 'Create')}" />
					</fieldset>
				</g:form>
			</div>
		</div>
	</body>
</html>
