<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../inc/header.jsp" %>

<div id="content" class="post">
<header role="page">
	<h1>글쓰기</h1>
</header>
<section id="post">
<form:form modelAttribute="blog">
<p>
	<form:select path="category">
		<form:options items="${categoryList}" itemLabel="name" itemValue="id" />
	</form:select>
	<form:input path="subject" />
</p>
<p>
	<form:textarea path="content" style="height:560px;"></form:textarea>
</p>
</form:form>
<p>
	<input type="text" name="tagInput" id="tagInput" placeholder="태그를 입력해주세요." />
	<span id="tagList">
	<c:forEach var="tag" items="${blog.tags}">
		<button id="${tag.name}" onclick="removeTag(this)">${tag.name} &times;</button>
	</c:forEach>
	</span>
</p>
<p>
	<input type="button" value="글쓰기" onclick="$('#blog').submit();" />
</p>
</section>

</div>
<script type="text/javascript">
		$(document).ready(function(){
			$('textarea#content').redactor({lang:'ko',imageUpload:'./post/file'});
			
			$("#tagInput").keyup(function(evt){
				if (evt.keyCode == 13){
					addTag();
				}
			});

		});
		
		function addTag() {
			tagName = $("#tagInput").val();
			
			$.ajax({
				type : "post",
				url : "./post/tag",
				data : "tagName="+tagName,
				dataType : "html",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				success: function(data) {
					$("#tagList").append(data);
					$("#tagInput").val("");
				}
			})
		}
		
		function removeTag(tagid) {
			var tagName = $(tagid).attr('id');
			$.ajax({
				type : "post",
				url : "./post/delete_tag",
				data : "tagName="+tagName,
				dataType : "text",
				contentType : "application/x-www.form-urlencoded; charset=UTF-8",
				success: function(data) {
					$(data).remove();
				}
			})
		}
</script>
<%@ include file="../inc/footer.jsp" %>
