<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Store project: normal page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link rel="stylesheet" href="${resource(dir: 'css/themes', file: store.theme+'.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'docs.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-responsive.css')}" type="text/css">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="images/favicon.ico">
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">
    <link rel="apple-touch-icon" sizes="72x72" href="images/apple-touch-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="114x114" href="images/apple-touch-icon-114x114.png">
  	
  	<script type="text/javascript" src="${resource(dir: 'js', file: 'showdown.js')}"></script>
  	
  	<g:layoutHead/>
  
  </head>

  <body>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <g:link class="brand" controller="store" action="index">${store.name }</g:link>
          <div class="nav-collapse">
            <ul class="nav">
            
            
              <g:if test="${controllerIndex==0 || controllerIndex==null }"><li class="active"></g:if><g:else><li></g:else><g:link controller="store" action="index">Home</g:link></li>
              <g:if test="${controllerIndex!=null && controllerIndex==1}"><li class="active"></g:if><g:else><li></g:else><g:link controller="store" action="show">Store</g:link></li>
              <g:if test="${controllerIndex!=null && controllerIndex==2}"><li class="active"></g:if><g:else><li></g:else><a href="#contact">Contact</a></li>
              <sec:ifAllGranted roles="ROLE_CUSTOMER">
              <li><g:link controller="customerManagement" action="index">My Account</g:link></li>
              </sec:ifAllGranted>
              <sec:ifAllGranted roles="ROLE_ADMIN">
              <g:if test="${controllerIndex!=null && controllerIndex==3}"><li class="active"></g:if><g:else><li></g:else><g:link controller="administration" action="index">Administration</g:link></li>
              </sec:ifAllGranted>
            </ul>
            <p class="navbar-text pull-right">
	            <sec:ifNotLoggedIn>
				  <g:link controller="login" action="auth" params="['spring-security-redirect': request.forwardURI - request.contextPath]">Login</g:link>
				</sec:ifNotLoggedIn>
				<sec:ifAllGranted roles="ROLE_ADMIN">
					Logged in as <g:link controller="administration"><sec:username /></g:link>, <g:link params="[targetUri: (request.forwardURI - request.contextPath)]" controller="logout">logout</g:link>
				</sec:ifAllGranted>
				<sec:ifAllGranted roles="ROLE_CUSTOMER">
					Logged in as <g:link controller="customerManagement"><sec:username /></g:link>, <g:link params="[targetUri: (request.forwardURI - request.contextPath)]" controller="logout">logout</g:link>
				</sec:ifAllGranted>
            </p>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
	
	
	
			
	<div class="subnav subnav-fixed">
    	<ul class="nav nav-pills">
    		<g:each var="category_item" in="${categories}">
				<g:if test="${parentProductClass!=null && category_item.id==parentProductClass.id }"><li class="active"></g:if><g:else><li></g:else>
					<g:link class="list" action="show_category" params="[id:category_item.id]">
						${category_item.name}
					</g:link>
				</li>
			</g:each>
			
			<g:if test="${session.getAttribute("cartSize") != null && session.getAttribute("cartSize") > 0}">
    		<li>
    			<g:link class="list" action="show_cart" controller="store"><i class="icon-shopping-cart"></i>Your cart</g:link>
    		<li>
    		</g:if>
    	
    	</ul> 	
			  	
    	
	</div>
	
	<br/>
	<br/>
	
    <div class="container-fluid">
      <div class="row-fluid">                 
          <g:layoutBody/>
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Company 2012</p>
      </footer>

    </div><!--/.fluid-container-->
    
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-transition.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-alert.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-modal.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-dropdown.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-tab.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-tooltip.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-popover.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-button.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-collapse.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-carousel.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'js', file: 'bootstrap-typeahead.js')}"></script>
	
	<r:layoutResources />
	
  </body>
</html>
