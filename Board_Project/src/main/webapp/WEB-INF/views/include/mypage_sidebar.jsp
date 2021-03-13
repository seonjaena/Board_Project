<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<style>
	a:link { color: white; text-decoration: none;}
	a:visited { color: white; text-decoration: none;}
	a:hover { color: white; text-decoration: none;}
</style>
<nav class="w3-sidebar w3-black w3-collapse w3-animate-top" style="z-index:3;width:300px;" id="mySidebar"><br>
    <div class="w3-container">
        <h3 class = "w3-center"><b><a href = "${root }user/mypage" class = "my_page_link">mypage</a></b></h3>
        <br>
        <div class="w3-row w3-center">
	        <c:choose>
	            <c:when test = "${requestScope.user_picture != null }">
	            	<a href = "#" class = "open_profile_button"><img src = "${root }img/${requestScope.user_picture}" style="width:45%; border-radius: 70% ;" class=" w3-center w3-round"></a>
	            </c:when>
	        	<c:otherwise>
	        		<a href = "#" class = "open_profile_button"><img src="${root }img/sample_picture.jpg" style="width:45%; border-radius: 70% ;" class=" w3-center w3-round"></a>
	        	</c:otherwise>
	        </c:choose>
            <br><br>
            <b>${sessionScope.loginUserBean.user_nickname }</b>
        </div>
        <br>
    </div>
    <!--영어 뭐라고 써야할지 너무 어려워요-->
    <div class="w3-bar-block">
        <a href="${root }board/main" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;카페글쓰기</a>
        <a href="${root }user/message" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;쪽지함</a>
        <a href="#정보수정" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;정보수정</a>
        <a href="${root }user/logout" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;로그아웃</a>
    	<a href="${root }main" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;메인페이지</a>
	</div>
        
</nav>
<script>
	$(".open_profile_button").click(function(e){
		e.preventDefault();
		open("${root}user/open_upload_picture", "", "width=400, height=100, scrollbars=no, resizable=no, toolbars=no, menubar=no");
	});
</script>