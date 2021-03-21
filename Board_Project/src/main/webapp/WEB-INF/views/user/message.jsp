<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board_Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${root }css/message.css">
</head>
<body class="w3-content w3-light-grey" style="max-width: 1600px;">

    <c:import url = "/WEB-INF/views/include/mypage_sidebar.jsp" />

    <!--small screen-->
    <div class="w3-overlay w3-hide-large w3-animate-top" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

    <div class="w3-main" style="margin-left:300px;">

        <!--header-->
        <header id="head" style="right:10px;">
            <div class="w3-padding-16">
                <div class="w3-container">
                    <button class="w3-btn w3-circle w3-hide-large w3-grey w3-hover-black w3-right" onclick="w3_open()">+</button>
                    <h1><b>쪽지함</b></h1>
                    <br>
                    <button class="w3-btn w3-hide-large w3-black w3-hover-white navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navMenu" aria-expanded="false">
                        menu
                    </button><!--menu글자 대신 선택한 목록 나오게 할 예정-->
                    <!--size=sm-->
                    <div class="navrbar-collapse collapse" id="navMenu">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a href="" class="nav-link">쪽지 보내기</a>
                            </li>
                            <li class="nav-item">
                                <a href="" class="nav-link">수신 쪽지함</a>
                            </li>
                            <li class="nav-item">
                                <a href="" class="nav-link">발신 쪽지함</a>
                            </li>
                        </ul>
                    </div>
                    <!--size=lg-->
                    <div class="w3-hide-small w3-section">
                        <button class="w3-button w3-black">쪽지 보내기</button>
                        <button class="w3-button w3-white">수신 쪽지함</button>        
                        <button class="w3-button w3-white">발신 쪽지함</button>                
                    </div>        
                </div> 
            </div>
        </header>
        <!--write message-->
        <div class="container">
            <div class="card shadow">
            <div id="write_msg">
                <ul>
                    <li>
                        <span class="col1">보내는 사람 </span>
                        <span class="col2">sky114z21@gmail.co.kr </span>
                    </li>
                    <li>
                        <span class="col1">받는 사람 </span>
                        <span class="col2"><input type="text" name="rv_id"> </span>
                    </li>
                    <li>
                        <span class="col1">제목 </span>
                        <span class="col2"><input type="text" name="subject"> </span>
                    </li>
                    <li>
                        <span class="col1">내용</span>
                        <span class="col2"><textarea name="content"></textarea></span>
                    </li>
                </ul>
                <!--전송하기 버튼-->
                <div class="w3-center">
                    <input type="submit" class="w3-hover-black" value="send">    
                </div>
            </div>
            </div>
        
        </div>
        <br>
        
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
    </script>

</body>
</html>