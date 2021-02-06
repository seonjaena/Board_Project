/**
 * 
 */
function save_comment(path, board_idx, user_nickname) {
	var comment_body = $(".comment_body").val();
	var data1 = comment_body + "," + board_idx;
	data = new FormData();
	data.append("data", data1);
	$.ajax({
		data : data, 
		url : path + "board/write_board_comment", 
		type : "POST", 
		contentType : false, 
		processData : false, 
		success : function(return_data) {
			var return_first_data = return_data.slice(0, return_data.lastIndexOf(","));
			var return_second_data = return_data.slice(return_data.lastIndexOf(",") + 1);
			$(".comments_div").append("<div class = 'comment_div form-group'>" + 
			"<b class = 'comment_writer'>" + user_nickname + "</b><br/>" + 
			"<div class = 'form-control'>" + comment_body + "</div><br/>" + 
			"<div align = 'right'>" + 
			"<button type = 'button' class = 'btn btn-warning' onclick = 'delete_board_comment($(this), " + path + ", " + return_first_data + ")' value = '" + return_second_data + "'>삭제</button>&nbsp;" + 
			"<button type = 'button' class = 'btn btn-info' onclick = 'modify_board_comment($(this), " + path + ", \"" + comment_body + "\")' value = '" + return_second_data + "'>수정</button>&nbsp;" + 
			"<button type = 'button' class = 'btn btn-primary' onclick = 'make_comment_comment(${root})'>답글달기</button>&nbsp;" + 
			"</div>" + 
			"<div class = 'form-group comment_comment_div' style = 'margin-left:5%'>" + 
			"<b>ㄴ</b>" + 
			"<b class = 'comment_writer'>홍길동</b><br/>" + 
			"<div class = 'form-control'>그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥그냥</div>" + 
			"<div align = 'right'>" + 
			"<button type = 'button' class = 'btn btn-primary' onclick = 'save_comment(${root})'>저장</button>&nbsp;" + 
			"</div></div></div><div class = 'empty_comment_div'></div>");
		}, 
		error : function() {
			alert("댓글 작성 실패");
		}, 
		complete : function() {
			$(".comment_body").val("");
		}
	})
}

function delete_board_comment(my_val, path, writer_idx) {
	if(confirm("삭제하시겠습니까?") == false) {
		return;
	}
	$.ajax({
		url : path + "board/delete_board_comment?comment_idx=" + my_val.val() + "&comment_writer_idx=" + writer_idx,
		type : "GET", 
		dataType : "text", 
		success : function() {
			my_val.parent().parent().remove();
		}
	})
}



function modify_board_comment(my_value, path, comment_text) {
	var return_second_data = my_value.val();
	my_value.parent().prev().prev().replaceWith("<textarea rows = '4' class = 'comment_body form-control' style = 'resize : none' maxlength = '116'>" + comment_text + "</textarea>");
	my_value.parent().replaceWith("<div align = 'right'>" + 
			"<button type = 'button' class = 'btn btn-primary'>확인</button>&nbsp;" + 
			"<button type = 'button' class = 'btn btn-danger' onclick = 'cancleCommentModification($(this),\"" + comment_text + "\", " + return_second_data + ", " + path + ")'>취소</button>&nbsp;" + 
			"</div>");
}

function modify_board_comment_confirm(my_val, writer_idx, path) {
	
	if(confirm("수정하시겠습니까?") == false) {
		return;
	}
	
	var comment_text = my_val.parent().prev().val();
	var data1 = comment_text + "," + writer_idx;
	data = new FormData();
	data.append("data", data1);
	
	$.ajax({
		data : data, 
		url : path + "board/modify_board_comment", 
		type : "POST", 
		contentType : false, 
		processData : false, 
		success : function() {
			alert("성공");
		}, 
		error : function() {
			alert("실패");
		}
	})
	
}

function cancleCommentModification(my_value, comment_body, return_second_data, path) {
	my_value.parent().prev().prev().replaceWith("<div class = 'form-control'>" + comment_body + "</div>");
	my_value.parent().replaceWith("<div align = 'right'>" + 
			"<button type = 'button' class = 'btn btn-warning' onclick = 'delete_board_comment($(this), " + path + ")' value = '" + return_second_data + "'>삭제</button>&nbsp;" + 
			"<button type = 'button' class = 'btn btn-info' onclick = 'modify_board_comment($(this), " + path + ", \"" + comment_body + "\")' value = '" + return_second_data + "'>수정</button>&nbsp;" + 
			"<button type = 'button' class = 'btn btn-primary' onclick = 'make_comment_comment(${root})'>답글달기</button>&nbsp;" + 
			"</div>")
}