<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>	
<%@ include file="../inc/header.jsp" %>

<div id="content" class="user">
<header role="user">
	<h1>${currentUser.name}님 환영합니다.</h1>
</header>

<section id="user">
	<ul id="userinfo">
		<li class="edit"><a href="./user/edit/${currentUser.id}">+</a></li>
		<li class="delete"><a href="#" onclick="deleteCon();">&times;</a></li>
	</ul>
<fieldset>
<table>
	<tr>
		<th>이메일 :</th>
		<td>${currentUser.email}</td>
	</tr>
	<tr>
		<th>작성글 수</th>
		<td>(구현예정)</td>
	</tr>
	<tr>
		<th>로그인횟수</th>
		<td>(구현예정)</td>
	</tr>
	<tr>
		<th>카테고리삭제</th>
		<td>(구현예정)</td>
	</tr>
</table>
</fieldset>
</section>

</div>
<script>
 function deleteCon() {
	 if(confirm('정말 탈퇴하시겠습니까?'))
		 location.href='./user/delete/${currentUser.id}';
	 else
		 return false;
	 
 }
</script>
<%@ include file="../inc/footer.jsp" %>