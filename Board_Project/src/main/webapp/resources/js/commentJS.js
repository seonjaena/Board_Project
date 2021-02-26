/*
*/
function save_comment(path, board_idx) {
	var comment_text = $(".comment_body").val();
	var data1 = comment_text + "," + board_idx;
	data = new FormData();
	data.append("data", data1);
	$.ajax({
		data : data, 
		url : path + "board/write_board_comment", 
		type : "POST", 
		contentType : false, 
		processData : false, 
		success : function(return_data) {
			var user_nickname = return_data.slice(0, return_data.indexOf(","));
			var writer_idx = return_data.slice(return_data.indexOf(",") + 1, return_data.lastIndexOf(","));
			var comment_idx = return_data.slice(return_data.lastIndexOf(",") + 1);
			$(".comments_div").append("<div class = 'comment_div form-group'>" + 
			"<b class = 'comment_writer'>" + user_nickname + "</b><br/>" + 
			"<div class = 'form-control'>" + comment_text + "</div><br/>" + 
			"<div align = 'right'>" + 
			"<button type = 'button' class = 'btn btn-warning' onclick = 'delete_board_comment($(this), " + path + ", " + writer_idx + ")' value = '" + comment_idx + "'>삭제</button>&nbsp;" + 
			"<button type = 'button' class = 'btn btn-info' onclick = 'modify_board_comment($(this), " + path + ", \"" + comment_text + "\", " + writer_idx + ")' value = '" + comment_idx + "'>수정</button>&nbsp;" + 
			"</div>" + 
			"<div class = 'empty_comment_div'></div></div>");
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



function modify_board_comment(my_val, path, comment_text, writer_idx) {
	var comment_idx = my_val.val();
	my_val.parent().prev().prev().replaceWith("<textarea rows = '4' class = 'comment_body form-control' style = 'resize : none' maxlength = '116'>" + comment_text + "</textarea>");
	my_val.parent().replaceWith("<div align = 'right'>" + 
			"<button type = 'button' class = 'btn btn-primary' onclick = 'modify_board_comment_confirm($(this), \"" +  path + "\", " + comment_idx + ", " + writer_idx + ", \"" + my_val.parent().prev().prev().val() + "\")'>확인</button>&nbsp;" + 
			"<button type = 'button' class = 'btn btn-danger' onclick = 'cancleCommentModification($(this), " + writer_idx + ", \"" + comment_text + "\", " + comment_idx + ", " + path + ")'>취소</button>&nbsp;" + 
			"</div>");
}

function modify_board_comment_confirm(my_val, path, comment_idx, writer_idx, comment_text) {
	
	if(confirm("수정하시겠습니까?") == false) {
		return;
	}
	var data1 = my_val.parent().prev().prev().val() + "," + comment_idx;
	var comment_body = my_val.parent().prev().prev().val();
	data = new FormData();
	data.append("data", data1);
	
	$.ajax({
		data : data, 
		url : path + "board/modify_board_comment", 
		type : "POST", 
		contentType : false, 
		processData : false, 
		success : function() {
			my_val.parent().prev().prev().replaceWith("<div class = 'form-control'>" + my_val.parent().prev().prev().val() + "</div>");
			my_val.parent().replaceWith("<div align = 'right'>" + 
					"<button type = 'button' class = 'btn btn-warning' onclick = 'delete_board_comment($(this), " + path + ", " + writer_idx + ")' value = '" + comment_idx + "'>삭제</button>&nbsp;" + 
					"<button type = 'button' class = 'btn btn-info' onclick = 'modify_board_comment($(this), " + path + ", \"" + comment_body + "\", " + writer_idx + ")' value = '" + comment_idx + "'>수정</button>&nbsp;" + 
					/*"<button type = 'button' class = 'btn btn-primary' onclick = 'make_comment_comment(${root})'>답글달기</button>&nbsp;" + */
					"</div>")
		}, 
		error : function() {
			alert("실패");
		}
	})
	
}

function cancleCommentModification(my_val, writer_idx, comment_text, comment_idx, path) {
	my_val.parent().prev().prev().replaceWith("<div class = 'form-control'>" + comment_text + "</div>");
	my_val.parent().replaceWith("<div align = 'right'>" + 
				"<button type = 'button' class = 'btn btn-warning' onclick = 'delete_board_comment($(this), " + path + ", " + writer_idx + ")' value = '" + comment_idx + "'>삭제</button>&nbsp;" + 
				"<button type = 'button' class = 'btn btn-info' onclick = 'modify_board_comment($(this), " + path + ", \"" + comment_text + "\", " + writer_idx + ")' value = '" + comment_idx + "'>수정</button>&nbsp;" + 
				/*"<button type = 'button' class = 'btn btn-primary' onclick = 'make_comment_comment(${root})'>답글달기</button>&nbsp;" + */
				"</div>")
}

function comment_comment(my_val, comment_comment_writer, path, comment_idx, board_idx) {
	my_val.parent().next().replaceWith("<div class = 'comment_comment_div form-group' style = 'margin-left:5%'>" + 
			"<b>ㄴ</b><b class = 'comment_writer'>" + comment_comment_writer + "</b><br/>" + 
			"<textarea rows = '4' class = 'comment_comment_body form-control' style = 'resize : none' maxlength = '116'></textarea><br/>" + 
			"<div align = 'right'>" + 
			"<button type = 'button' class = 'btn btn-primary' onclick = 'save_comment_comment($(this), " + path + ", " + comment_idx + ", " + board_idx + ")'>확인</button>&nbsp;" + 
			"<button type = 'button' class = 'btn btn-danger' onclick = 'cancle_comment_comment($(this))'>취소</button>&nbsp;" + 
			"</div>" + 
			"</div>")
}

function save_comment_comment(my_val, path, comment_idx, board_idx) {
	var comment_comment_text = $(".comment_comment_body").val();
	var data1 = comment_comment_text + "," + comment_idx;
	data = new FormData();
	data.append("data", data1);
	data.append("board_idx", board_idx);
	$.ajax({
		data : data, 
		url : path + "board/write_comment_comment", 
		type : "POST", 
		contentType : false, 
		processData : false, 
		success : function(return_data) {
			var user_nickname = return_data.slice(0, return_data.indexOf(","));
			var writer_idx = return_data.slice(return_data.indexOf(",") + 1, return_data.lastIndexOf(","));
			var ccomment_idx = return_data.slice(return_data.lastIndexOf(",") + 1);
			my_val.parent().parent().replaceWith("<div class = 'comment_comment_division form-group'></div>");
			my_val.parent().parent().next().last().replaceWith("<b>이어붙이기</b>");
			$(".comment_comment_container" + comment_idx + "").children().last().append("<div class = 'form-group comment_comment_div' style = 'margin-left:5%'>" + 
					"<b>ㄴ</b><b class = 'comment_writer'>" + user_nickname + "</b><br/>" + 
					"<div class = 'form-control'>" + comment_comment_text + "</div>" + 
					"<div align = 'right'>" + 
					"<button type = 'button' class = 'btn btn-danger' onclick = 'delete_comment_comment($(this), " + path + ", " + ccomment_idx + ")'>삭제</button>" + 
					"&nbsp;</div>");
		}, 
		error : function() { 
			alert("실패");
		}, 
		complete : function() {
			my_val.parent().parent().replaceWith("<div class = 'comment_comment_division form-group'></div>");
		}
	})
}

function cancle_comment_comment(my_val) {
	my_val.parent().parent().replaceWith("<div class = 'comment_comment_division form-group'></div>");
}

function delete_comment_comment(my_val, path, ccomment_idx) {
	if(confirm("삭제하시겠습니까?") == false) {
		return;
	}
	
	$.ajax({
		url : path + "board/delete_comment_comment?ccomment_idx=" + ccomment_idx, 
		type : "GET", 
		dataType : "text", 
		success : function() {
			my_val.parent().parent().remove();
		}, 
		error : function() {
			alert("실패");
		}
	})
}