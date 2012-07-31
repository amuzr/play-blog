<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../inc/header.jsp" %>

<div id="content" class="user register">
<header role="register">
	<h1>가입하기</h1>
</header>

<section id="register">
<form:form modelAttribute="user">
<fieldset>
<p class="errors">
	<form:errors cssClass="error" path="" />
	<form:errors cssClass="error" path="email" /><br />
	<form:errors cssClass="error" path="name" /><br />
	<form:errors cssClass="error" path="password" />
</p>
<table>
	<tr>
		<th><form:label path="email">이메일 : </form:label></th>
		<td><form:input path="email" cssClass="input_medium" /></td>
	</tr>
	<tr>
		<th><form:label path="name">이름 : </form:label></th>
		<td><form:input path="name" cssClass="input_medium" /></td>
	</tr>
	<tr>
		<th><form:label path="password">비밀번호 : </form:label></th>
		<td><form:password path="password" cssClass="input_medium" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="가입하기" /></td>
	</tr>
</table>
</fieldset>
</form:form>
</section>

</div>

<script>
	$(".error").parent().show();
</script>
<%@ include file="../inc/footer.jsp" %>