<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

	<div style = "text-align : center;">
		<h1>프로필 사진</h1>
		<form id = "ajaxForm">
			<input type = "file" id = "ajaxFile" name = "file" />
			<button type = "button" class = "btn btn-primary" id = "uploadBtn" onclick = "uploadFile()">업로드</button>
		</form>
	</div>

	<script>
	
		function uploadFile() {
			const sendingData = new FormData();
			sendingData.append("file", $("#ajaxFile")[0].files[0]);
			$.ajax({
				url : ${root} + "user/upload_profile", 
				type : "POST", 
				processData : false, 
				contentType : false, 
				data : sendingData, 
				success : function() {
					opener.location.reload();
					close();
				}, 
				error : function() {
					alert("에러");
				}
			})
		}
	</script>

</body>
</html>