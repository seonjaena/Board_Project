<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<c:set var = "root" value = "${pageContext.request.contextPath }/" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p style="text-align:center;">[주소로 위도, 경도 좌표값 얻기]</p>

<input type="text" id="address" size="70" readonly = "readonly">
<button type="button" onclick="addressChk()">주소 찾기</button>
<div id="map" style="width:100%;height:450px;"></div>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=33d4176fe196efcbf6fee83138e26e9b&libraries=services"></script>
<script>
var user_address =  "<c:out value='${main_addr}' />";
var address = document.getElementById("address");
var mapContainer = document.getElementById("map");
var mapOption;
var map;
var x, y = "";

if (address.value == "") {
	mapOption = {
		center : new daum.maps.LatLng(33.450701, 126.570667), // 임의의 지도 중심좌표 , 제주도 다음본사로 잡아봤다.
        level : 4            // 지도의 확대 레벨
	};
}

// 지도 생성
map = new daum.maps.Map(mapContainer, mapOption);
address.value = user_address;

function addressChk() {
	var gap = address.value; // 주소검색어
	if (gap == "") {
		alert("주소 검색어를 입력해 주십시오");
		address.focus();
		return;
	}
 
	// 주소-좌표 변환 객체를 생성
	var geocoder = new daum.maps.services.Geocoder();

	// 주소로 좌표를 검색
	geocoder.addressSearch(gap, function(result, status) {
  
		// 정상적으로 검색이 완료됐으면,
		if (status == daum.maps.services.Status.OK) {
	   
		var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
		y = result[0].x;
		x = result[0].y;
	
		// 지도 중심을 이동
		map.setCenter(coords);
		}
	});
}


</script>
</body>
</html>