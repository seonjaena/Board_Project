<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var='root' value='${pageContext.request.contextPath }/'/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Board_Project</title>
<!-- Bootstrap CDN -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<style>
	.comment_comment_div {
		border : 1px solid grey;
		padding-top : 1rem;
		padding-bottom : 1rem;
		padding-left : 3%;
		padding-right : 3%;
		margin-top : 2rem;
		background-color : grey;
	}
</style>
</head>
<body>
	
<c:import url="/WEB-INF/views/include/top_menu.jsp"/>

<div class="container" style="margin-top:100px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<input type = "hidden" class = "board_idx" readonly = "readonly" value = "${requestScope.board_idx }" />
					<div class="form-group">
						<label for="board_writer_name">작성자</label>
						<input type="text" id="board_writer_name" name="board_writer_name" class="form-control" value="${requestScope.readBoardBean.user_nickname }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_date">작성날짜</label>						
						<input type="text" id="board_date" name="board_date" class="form-control" value="<fmt:formatDate value = '${requestScope.readBoardBean.board_date}' pattern = 'yyyy-MM-dd HH:mm' />" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_subject">제목</label>
						<input type="text" id="board_subject" name="board_subject" class="form-control" value="${requestScope.readBoardBean.board_title }" disabled="disabled"/>
					</div>
					<div class="form-group">
						<label for="board_content">내용</label>
						<div>${requestScope.readBoardBean.board_text }</div>
					</div>
					<div class="form-group">
						<div class="text-left">
							<c:choose>
								<c:when test = "${requestScope.is_recommended != null && requestScope.is_recommended.is_delete == 0 }">
									<button type = "button" class = "recommendation_button btn btn-default" onclick = "toggleRecommendation(${root}, 1, ${requestScope.board_idx })">
										<i style = "color:blue" class = "fa fa-thumbs-o-up"></i>&nbsp;${requestScope.readBoardBean.board_recommendation }<br/><b>추천</b>
									</button>
								</c:when>
								<c:otherwise>
									<button type = "button" class = "recommendation_button btn btn-default" onclick = "toggleRecommendation(${root}, 0, ${requestScope.board_idx })">
										<i class = "fa fa-thumbs-o-up"></i>&nbsp;${requestScope.readBoardBean.board_recommendation }<br/><b>추천</b>
									</button>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="text-right">
							<a href="${root }board/main?board_type_idx=${requestScope.board_type_idx }" class="btn btn-primary">목록보기</a>
							<c:if test = "${requestScope.readBoardBean.board_writer_idx == sessionScope.loginUserBean.user_idx }">
								<a href="${root }board/modify?board_type_idx=${requestScope.board_type_idx }&board_idx=${requestScope.board_idx }" class="btn btn-info">수정하기</a>
								<a href="${root }board/delete?board_type_idx=${requestScope.board_type_idx }&board_idx=${requestScope.board_idx }" class="btn btn-danger">삭제하기</a>
							</c:if>
						</div>
					</div>
				</div>
			</div><br/>
			<div class="card shadow">
				<div class = "comments_div card-body">
					<c:forEach var = "commentList" items = "${requestScope.commentList }" varStatus = "status">
						<div class = "comment_div form-group">
							<b class = "comment_writer">${commentList.user_nickname }</b><br/>
							<div class = "form-control">${commentList.comment_text }</div><br/>
							<div align = "right">
								<c:if test = "${sessionScope.loginUserBean.user_idx == commentList.comment_writer_idx }">
									<button type = "button" class = "delete_comment_btn btn btn-warning" onclick = "delete_board_comment($(this), '${root}', ${commentList.comment_writer_idx })" value = "${commentList.comment_idx }">삭제</button>&nbsp;
									<button type = "button" class = "modify_comment_btn btn btn-info" onclick = "modify_board_comment($(this), '${root}', '${commentList.comment_text }', ${commentList.comment_writer_idx })" value = "${commentList.comment_idx }">수정</button>&nbsp;
								</c:if>
								<c:if test = "${sessionScope.loginUserBean.user_idx != commentList.comment_writer_idx }">
									<button type = "button" class = "btn btn-danger">신고</button>
								</c:if>
								<button type = "button" class = "save_comment_comment_btn btn btn-primary" onclick = "comment_comment($(this), '${sessionScope.loginUserBean.user_nickname}', '${root }', '${commentList.comment_idx }', '${requestScope.board_idx }')">답글달기</button>
							</div>
							<div class = "comment_comment_division form-group"></div>
							<div class = "comment_comment_container${commentList.comment_idx }">
								<c:forEach var = "commentCommentList" items = "${requestScope.commentCommentList }">
									<c:if test = "${commentCommentList.comment_idx == commentList.comment_idx }">
										<div class = "form-group comment_comment_div" style = "margin-left:5%">
											<b>ㄴ</b>
											<b class = "comment_writer">${commentCommentList.user_nickname }</b><br/>
											<div class = "form-control">${commentCommentList.ccomment_text }</div>
											<div align = "right">
												<c:if test = "${sessionScope.loginUserBean.user_idx == commentCommentList.ccomment_writer_idx }">
													<button type = "button" class = "btn btn-danger" onclick = "delete_comment_comment($(this), '${root}', ${commentCommentList.ccomment_idx })">삭제</button>&nbsp;
												</c:if>
											</div>
										</div>
									</c:if>
									<div></div>
								</c:forEach>
								<div></div>
							</div>
						</div>
						<div class = "empty_comment_div"></div>
					</c:forEach>
				</div>
			</div><br/>
			<div class = "card shadow">
				<div class = "card-body">
					<h5>댓글 작성</h5><br/>
					<div class = "form-group">
						<b class = "comment_writer">${sessionScope.loginUserBean.user_nickname }</b><br/>
						<textarea rows = "4" class = "comment_body form-control" style = "resize : none" maxlength = "116"></textarea><br/>
						<div align = "right">
							<button type = "button" class = "btn btn-primary" onclick = "save_comment(${root}, ${requestScope.board_idx })">저장</button>&nbsp;
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>


<c:import url="/WEB-INF/views/include/bottom_info.jsp"/>
<script src = "${root }js/commentJS.js"></script>
<script>
	function toggleRecommendation(path, state, board_idx){
		$(".recommendation_button").prop("disability", true);
		$.ajax({
			url : path + "board/toggleRecommend?state=" + state + "&board_idx=" + board_idx, 
			dataType : "text", 
			type : "GET", 
			success : function(result) {
				if(result == "0") {
					$(".recommendation_button").replaceWith("<button type = 'button' class = 'recommendation_button btn btn-default' onclick = 'toggleRecommendation(" + path + ", " + result + ", " + board_idx + ")'></button>");
					$(".recommendation_button").append("<i class = 'fa fa-thumbs-o-up'></i><br/><b>추천</b>");
				}else if(result == "1") {
					$(".recommendation_button").replaceWith("<button type = 'button' class = 'recommendation_button btn btn-default' onclick = 'toggleRecommendation(" + path + ", " + result + ", " + board_idx + ")'></button>");
					$(".recommendation_button").append("<i style = 'color:blue' class = 'fa fa-thumbs-o-up'></i><br/><b>추천</b>");
				}
				
				$(".recommendation_button").prop("disability", false);
				
			}
		})
	}
</script>
</body>
</html>