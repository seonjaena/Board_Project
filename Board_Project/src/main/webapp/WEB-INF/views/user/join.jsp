<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미니 프로젝트</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action = "${root }user/join_pro" method = "POST" autocomplete = "off" modelAttribute = "joinUserBean">
						<form:hidden path = "userIdExist" />
						<form:hidden path = "userNicknameExist" />
						<form:hidden path = "userEmailExist" />
						<div class="form-group">
							<form:label path = "user_name">이름</form:label>
							<form:input path = "user_name" class = "form-control" />
							<form:errors path = "user_name" style = "color:red" />
						</div>
						<div class="form-group">
							<form:label path = "user_id">아이디</form:label>
							<div class="input-group">
								<form:input path = "user_id" class = "form-control" onkeydown = "resetUserIdExist()" />
								<div class="input-group-append">
									<button type = "button" class = "btn btn-primary" onclick = "checkUserIdExist(${root })">중복확인</button>
								</div>
							</div>
							<form:errors path = "user_id" style = "color:red" />
						</div>
						<div class="form-group">
							<form:label path = "user_email">이메일</form:label>
							<div class="input-group">
								<form:input path = "user_email" class = "form-control" onkeydown = "resetUserEmailExist()" />
								<div class="input-group-append">
									<button type = "button" class = "send_validation_email btn btn-primary" onclick = "checkEmailExist(${root })">중복확인</button>
								</div>
							</div>
							<form:errors path = "user_email" style = "color:red" />
						</div>
						<div class="form-group">
							<form:label path = "user_pw">비밀번호</form:label>
							<form:password path = "user_pw" class = "form-control" />
							<form:errors path = "user_pw" style = "color:red" />
						</div>
						<div class="form-group">
							<form:label path = "user_pw2">비밀번호 확인</form:label>
							<form:password path = "user_pw2" class = "form-control" />
							<form:errors path = "user_pw2" style = "color:red" />
						</div>
						<div class="form-group">
							<form:label path = "user_nickname">닉네임</form:label>
							<div class="input-group">
								<form:input path = "user_nickname" class = "form-control" onkeydown = "resetUserNickExist()" />
								<div class="input-group-append">
									<button type = "button" class = "btn btn-primary" onclick = "checkUserNickExist(${root })">중복확인</button>
								</div>
							</div>
							<form:errors path = "user_nickname" style = "color:red" />
						</div><br/>
						
						<div class = "form-group">
							<div class = "input-group">
								<form:input path = "zip_code" class = "form-control" placeholder = "우편번호" readonly = "true" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<div class = "input-group-append">
									<button type = "button" class = "btn btn-warning" onclick = "getAddress()">우편번호 찾기</button><br/>
								</div>
							</div><br/>
							<div class = "input-group">
								<form:input path = "main_addr" class = "form-control" placeholder = "주소" readonly = "true" /><br/>
							</div><br/>
							<div class = "input-group">
								<form:input path = "reference_addr" class = "form-control" placeholder = "참고항목" readonly = "true" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<div class = "input-group-append">
									<form:input path = "detail_addr" class = "form-control" placeholder = "상세주소"/>
								</div>
							</div>
							
						</div>
						
						<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
							<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
						</div>
						
						<div class="form-group">
							<div class="text-right">
								<form:button class = "btn btn-primary">회원가입</form:button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>
<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src = "${root }js/joinJS.js"></script>

</body>
</html>