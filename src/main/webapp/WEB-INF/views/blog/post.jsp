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
<input type="text" name="cateInput" id="cateInput" placeholder="카테고리를 입력" />
<form:form modelAttribute="blog">
<p>
	<form:select path="category" onchange="showInput()">
		<form:option label="분류 선택하기." value="-" />
		<form:option label="분류 추가하기." value="add" />
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
		
		$("#cateInput").keyup(function(evt){
			if (evt.keyCode == 13){
				addCategory();
			}
		});
	});
	
	function addTag() {
		tagName = $("#tagInput").val();
		
		if(tagName != "") {
			$.ajax({
				type : "post",
				url : "./post/tag",
				data : "tagName="+tagName,
				dataType : "html",
				success: function(data) {
					$("#tagList").append(data);
					$("#tagInput").val("");
				}
			})
		} else
			alert('태그이름을 입력해주세요!');
	}
	
	function removeTag(tagid) {
		var tagName = $(tagid).attr('id');
		$.ajax({
			type : "post",
			url : "./post/delete_tag",
			data : "tagName="+tagName,
			dataType : "text",
			success: function(data) {
				$(data).remove();
			}
		})
	}
	
	function showInput() {
		if($("#category").val() == "add")
			$("#cateInput").show();
		else
			$("#cateInput").hide();
			
	}
	
	function addCategory() {
		if(confirm("카테고리를 추가하시겠습니까?")){
			cateName = $("#cateInput").val();
			
			$.ajax({
				type : "post",
				url : "./post/category",
				data : "cateName="+cateName,
				dataType : "json",
				success: function(data) {
					var options = document.getElementById("category").options;
					options[options.length] = new Option(data.text, data.value, true, true);
				}
			})
		} else
			$("#category").val("");
		
		$("#cateInput").val("").hide();
	}
</script>
<%@ include file="../inc/footer.jsp" %>