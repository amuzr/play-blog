<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../inc/header.jsp" %>

<div id="content" class="user login">
<header role="login">
	<h1>로그인</h1>
</header>

<section id="login">
<form:form modelAttribute="login">
<fieldset>
<p class="errors">
	<form:errors cssClass="error" path="" />
	<form:errors cssClass="error" path="email" /><br />
	<form:errors cssClass="error" path="password" />
</p>
<table>
	<tr>
		<th><form:label path="email">E-Mail : </form:label></th>
		<td><form:input path="email" cssClass="input_medium" /></td>
	</tr>
	<tr>
		<th><form:label path="password">Pass : </form:label></th>
		<td><form:password path="password" cssClass="input_medium" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="로그인" />&nbsp;
		</td>
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