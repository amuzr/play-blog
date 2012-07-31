<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../inc/header.jsp" %>

<div id="content" class="blog multiple">
<header role="page">
	<h1>Blog</h1>
	<p>블로그 테스트 중입니다~ 글남기는건 안되요~!</p>
</header>

<section id="main">

<c:forEach var="blog" items="${blogList}" >
<article>
	<header>
		<h1><a href="${pageContext.request.contextPath}/blog/${blog.id}">[${blog.category.name}] ${blog.subject}</a></h1>
	</header>
	<div class="group">
	<p>
		<c:choose>
			<c:when test="${fn:length(blog.content) > 100}">
				${fn:substring(blog.content,0,100)} <Br/>
				<a href="${pageContext.request.contextPath}/blog/${blog.id}">....more</a>
			</c:when>
			<c:otherwise>
				${blog.content}
			</c:otherwise> 
		</c:choose>
	</p>
	</div>
	<footer>
		<ul>
			<li class="date"><time datetime="${blog.created}" pubdate><spring:eval expression="blog.created" /></time></li>
			<li class="comments"><a href="javascript:;"><fb:comments-count href=http://amuzr.cloudfoundry.com/blog/${blog.id} ></fb:comments-count> comments</a></li>
			<li class="tags">
			<c:forEach var="tag" items="${blog.tags}">
					<a href="${pageContext.request.contextPath}/blog/tag/${tag.name}">${tag.name}</a>
			</c:forEach>
			</li>
		</ul>
	</footer>
</article>
</c:forEach>
<!--  pagination -->
<div class="pager">
<c:if test="${pager.prevPage != 0}"> 
	<a href="?p=${pager.prevPage}">Prev</a>
</c:if>
<span class="pagenum">
<c:forEach var="nowPage" begin="${pager.startPage}" end="${pager.maxPage}">
	<c:choose>
		<c:when test="${nowPage == pager.curPage }">
			<span class="current">${nowPage}</span>
		</c:when>
		<c:otherwise>
			<a href="?p=${nowPage}">${nowPage}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
</span>
<c:if test="${pager.nextPage != 0}"> 
	<a href="?p=${pager.nextPage}">Next</a>
</c:if>
</div>
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

<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/ko_KR/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));
</script>

<%@ include file="../inc/footer.jsp" %>
