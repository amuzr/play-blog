<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./inc/header.jsp" %>

<div id="content" class="login">
<header role="login">
	<h1>권한이 필요합니다.</h1>
</header>

<section id="login">
 로그인 해주세요 <a href="${pageContext.request.contextPath}/login">[로그인]</a>
</section>

</div>
<%@ include file="./inc/footer.jsp" %>