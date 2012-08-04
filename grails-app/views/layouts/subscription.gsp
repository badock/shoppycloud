<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=1024" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<title></title>

<meta name="description"
	content="impress.js is a presentation tool based on the power of CSS3 transforms and transitions in modern browsers and inspired by the idea behind prezi.com." />
<meta name="author" content="Bartek Szopka" />

<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:regular,semibold,italic,italicsemibold|PT+Sans:400,700,400italic,700italic|PT+Serif:400,700,400italic,700italic"
	rel="stylesheet" />
<link rel="stylesheet"	href="${resource(dir: 'css', file: 'impress-demo.css')}"	type="text/css">
<link rel="stylesheet"	href="${resource(dir: 'css', file: 'subscription.css')}"	type="text/css">
<link rel="shortcut icon" href="favicon.png" />
<link rel="apple-touch-icon" href="apple-touch-icon.png" />
<script type="text/javascript" src="${resource(dir: 'js', file: 'jquery.js')}"></script>
<g:layoutHead/>
</head>
<body class="impress-not-supported">
	
	
	<div class="navbar-inner">
		<img class="brand" alt="logo" src="${resource(dir: 'images', file: 'logo.png')}">
	</div>
	
	
	<!--
    For example this fallback message is only visible when there is `impress-not-supported` class on body.
-->
	<div class="fallback-message">
		<p>
			Your browser <b>doesn't support the features required</b> by
			impress.js, so you are presented with a simplified version of this
			presentation.
		</p>
		<p>
			For the best experience please use the latest <b>Chrome</b>, <b>Safari</b>
			or <b>Firefox</b> browser.
		</p>
	</div>
	
	<g:layoutBody/>

	<script src="${resource(dir: 'js', file: 'impress.js')}"></script>
	<script>impress().init();</script>

</body>
</html>

