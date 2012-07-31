<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<!--[if IE 7 ]><html lang="ko" class="ie7"> <![endif]-->
<!--[if IE 8 ]><html lang="ko" class="ie8"> <![endif]-->
<!--[if IE 9 ]><html lang="ko" class="ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="ko"> <!--<![endif]-->
<head>
	<meta charset="utf-8">
	<title>No excuses, just play!</title>

	<meta name="author" content="Amuzr">
	<!-- for mobile >
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<link rel="shortcut icon" href="/favicon.ico">
	<link rel="apple-touch-icon" href="/apple-touch-icon.png">
	<link rel="apple-touch-icon" sizes="72x72" href="/apple-touch-icon-ipad.png">
	<link rel="apple-touch-icon" sizes="114x114" href="/apple-touch-icon-iphone4.png">
	<! -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" media="screen">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<script src="${pageContext.request.contextPath}/js/selectivizr-min.js"></script>
	<![endif]-->
	<!--[if lt IE 7]>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ie.css" media="all">
	<script src="${pageContext.request.contextPath}/js/DD_belatedPNG.js"></script>
	<script type="text/javascript">var _sf_startpt=(new Date()).getTime()</script>
	<script>
	 DD_belatedPNG.fix('img, .png_bg');
	</script>
	<![endif]-->
</head>
<body>
<div id="container">

<header role="g_head">
	<h1 id="branding"><a href="${pageContext.request.contextPath}/blog">Amuzr.play()</a></h1>
	<nav>
		<ul>
			<li><a href="${pageContext.request.contextPath}/blog">Blog</a></li>
			<li><a href="javascript:;">About</a></li>
			<li><a href="${pageContext.request.contextPath}/login">&raquo;</a></li>
		</ul>
	</nav>
</header>

<div id="content" class="errorPage">
<header role="errorPage">
	<h1>페이지를 찾을 수 없습니다.</h1>
</header>

<section id="errorPage">
<p>
글이 삭제 되었거나 페이지를 찾을 수 없습니다.<br/>
<a href="javascript:history.back();">뒤로가기</a>
</p>
</section>

</div>

<footer role="siteinfo">
	<p>Copyright &copy; 2012 Amuzr. </p>
</footer>
</div>

</body>
</html>