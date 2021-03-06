<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Board_Project</title>
</head>
<body class="w3-content w3-light-grey" style="max-width: 1600px;">

    <!--Sidebar/menu-->
    <nav class="w3-sidebar w3-black w3-collapse w3-animate-top" style="z-index:3;width:300px;" id="mySidebar"><br>
        <div class="w3-container">
            <h3 class="w3-center"><b>my profile</b></h3><!--board로 바꾸고 main화면 가는게 나을까요?-->
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
            <a href="#message" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;쪽지함</a>
            <a href="#정보수정" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;정보수정</a>
            <a href="${root }user/logout" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;로그아웃</a>
            <a href="${root }main" onclick="w3_close()" class="w3-bar-item w3-button w3-padding-16">&nbsp;메인페이지</a>
        </div>
        
    </nav>

    <!--small screen-->
    <div class="w3-overlay w3-hide-large w3-animate-top" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

    <div class="w3-main" style="margin-left:300px;">

        <!--header-->
        <header id="head" style="right:10px;">
            <div class="w3-padding-32">
                <div class="w3-container">
                    <button class="w3-btn w3-circle w3-hide-large w3-grey w3-hover-black w3-right" onclick="w3_open()">+</button>
                    <h1><b>회원정보</b></h1>
                    <br>
                    <button class="w3-btn w3-hide-large w3-black w3-hover-white navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navMenu" aria-expanded="false">
                        menu
                    </button><!--menu글자 대신 선택한 목록 나오게 할 예정-->
                    <div class="navrbar-collapse collapse" id="navMenu">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a href="" class="nav-link">내가 쓴 게시글</a>
                            </li>
                            <li class="nav-item">
                                <a href="" class="nav-link">내가 쓴 댓글</a>
                            </li>
                            <li class="nav-item">
                                <a href="" class="nav-link">카페 활동 알림</a>
                            </li>
                        </ul>
                    </div>
                    <div class="w3-hide-small w3-section w3-padding-16">
                        <button class="w3-button w3-black">내가 쓴 게시글</button>
                        <button class="w3-button w3-white">내가 쓴 댓글</button>
                        <button class="w3-button w3-white">카페 활동 알림</button>
                    </div>
                </div> 
            </div>
            
        </header>

        <!-- Pagination -->
        <div class="w3-center w3-padding-32">
            <div class="w3-bar">
                <a href="#" class="w3-bar-item w3-button w3-hover-black">«</a>
                <a href="#" class="w3-bar-item w3-black w3-button">1</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">2</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">3</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">4</a>
                <a href="#" class="w3-bar-item w3-button w3-hover-black">»</a>
            </div>
        </div>      
    </div>

    <script>
    //open and close siderbar
    function w3_open(){
        document.getElementById("mySidebar").style.display = "block";
        document.getElementById("myOverlay").style.display = "block";        
    }
    function w3_close(){
        document.getElementById("mySidebar").style.display = "none";
        document.getElementById("myOverlay").style.display = "none";        
    }
    $(".open_profile_button").click(function(e){
    	e.preventDefault();
    	open("${root}user/open_upload_picture", "", "width=400, height=100, scrollbars=no, resizable=no, toolbars=no, menubar=no");
    });
    </script>

</body>
</html>