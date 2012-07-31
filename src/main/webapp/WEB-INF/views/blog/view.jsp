<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>	
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../inc/header.jsp" %>

<c:if test="${currentUser.id eq blog.user.id}" >
	<c:set value="true" var="auth" />
</c:if>

<div id="content" class="blog single">
<header role="blog">
	<p class="meta"><time datetime="${blog.created}" pubdate><spring:eval expression="blog.created" /></time></p>
	<h1>[${blog.category.name}] ${blog.subject}</h1>
</header>

<section id="main">

<article>
<c:if test="${auth}">
	<ul id="auth">
		<li class="edit"><a href="./edit/${blog.id}">+</a></li>
		<li class="delete"><a href="./delete/${blog.id}">&times;</a></li>
	</ul>
</c:if>
	<div id="wysiwyg" class="group">
		<p>${blog.content}</p>
	</div>
	<footer>
		<ul>
			<li class="author">Posted by ${blog.user.name}</li>
			<li class="comments"><a href="javascript:;"><fb:comments-count href=http://amuzr.cloudfoundry.com/blog/${blog.id} ></fb:comments-count> comments</a></li>
			<li class="tags">
			<c:forEach var="tag" items="${blog.tags}">
			<a href="javascript:;">${tag.name}</a>
			</c:forEach>
			</li>
		</ul>
	</footer>
</article>

<section id="comments">
	<fb:comments href="http://amuzr.cloudfoundry.com/blog/${blog.id}" num_posts="2"></fb:comments>
</section>

</section>

<aside role="sub">
	<h4>Categories</h4>
	<ul id="nav_categories">
	<c:forEach var="category" items="${categoryList}">
		<li><a href="${pageContext.request.contextPath}/blog/category/${category.name}">${category.name}</a></li>
	</c:forEach>
	</ul>
	<h4>Archives</h4>
	<ul id="nav_archives">
	<c:forEach var="archive" items="${archiveList}">
		<li><a href="${pageContext.request.contextPath}/blog/archive/${archive}">${archive}</a></li>
	</c:forEach>
	</ul>
</aside>

</div>

<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/ko_KR/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<%@ include file="../inc/footer.jsp" %>