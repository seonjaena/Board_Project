<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Board_Project</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<!-- 게시글 리스트 -->
<div class="container" style="margin-top:100px">
	<div class="card shadow">
		<div class="card-body">
			<h4 class="card-title">${requestScope.board_type_name }</h4>
			<table class="table table-hover" id='board_list'>
				<thead>
					<tr>
						<th class="text-center d-none d-md-table-cell">글번호</th>
						<th class="w-50">제목</th>
						<th class="text-center d-none d-md-table-cell">작성자</th>
						<th class="text-center d-none d-md-table-cell">작성날짜</th>
						<th class="text-center d-none d-md-table-cell">조회수</th>
						<th class="text-center d-none d-md-table-cell">추천수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var = "boardList" items = "${requestScope.boardList }">
						<tr>
							<td class="text-center d-none d-md-table-cell">${boardList.board_idx }</td>
							<td><a href='${root }board/read?board_idx=${boardList.board_idx }&board_type_idx=${requestScope.board_type_idx }'>${boardList.board_title }</a></td>
							<td class="text-center d-none d-md-table-cell">${boardList.user_nickname }</td>
							<td class="text-center d-none d-md-table-cell">${boardList.board_date }</td>
							<td class="text-center d-none d-md-table-cell">${boardList.board_views }</td>
							<td class="text-center d-none d-md-table-cell">${boardList.board_recommendation }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="d-none d-md-block">
				<ul class="pagination justify-content-center">
					<c:choose>
						<c:when test = "${requestScope.pageBean.currentPage == 1 }">
							<li class="page-item disabled">
								<a href="#" class="page-link">이전</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item">
								<a href="${root }board/main?board_type_idx=${requestScope.board_type_idx }&page=${requestScope.pageBean.prevPage }" class="page-link">이전</a>
							</li>
						</c:otherwise>
					</c:choose>
					<c:forEach var = "idx" begin = "${requestScope.pageBean.min }" end = "${requestScope.pageBean.max }">
						<c:choose>
							<c:when test = "${requestScope.pageBean.currentPage == idx }">
								<li class="page-item active">
									<a href="${root }board/main?board_type_idx=${requestScope.board_type_idx }&page=${idx }" class="page-link">${idx }</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a href="${root }board/main?board_type_idx=${requestScope.board_type_idx }&page=${idx }" class="page-link">${idx }</a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test = "${requestScope.pageBean.currentPage == requestScope.pageBean.pageCnt }">
							<li class="page-item disabled">
								<a href="#" class="page-link">다음</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item">
								<a href="${root }board/main?board_type_idx=${requestScope.board_type_idx }&page=${requestScope.pageBean.nextPage }" class="page-link">다음</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			
			<div class="text-right">
				<a href="${root }board/write?board_type_idx=${requestScope.board_type_idx }" class="btn btn-primary">글쓰기</a>
			</div>
		</div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>

</body>
</html>