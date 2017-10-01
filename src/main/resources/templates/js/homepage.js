var page =1;
function sendMessage(userId) {
	$("#sendMessage").modal({
		backdrop : "static"
	});
}
	
$(function(){
	$("a#articles").click(function(){
		$("a#comments").removeClass("active");
		$("a#articles").addClass("active");
			
		$("div#articles").removeClass("active");
		$("div#comments").removeClass("active");
		$("div#articles").addClass("active");
	});
	$("a#comments").click(function(){
		$("a#articles").removeClass("active");
		$("a#comments").addClass("active");
			
		$("div#articles").removeClass("active");
		$("div#comments").removeClass("active");
		$("div#comments").addClass("active");
	});
	$.ajax({
		type : "post",
		url : "/commentsPage",
		data : {
			currentpage : page,
			userId : userVOId
		},
		success: function(data){
			console.log(data);
			if(data['stauts'] = 'success'){
				var comments = data['comments'];
				var html = $("#comment").render({"comments": comments, "online": online});
				$("div.comments-aw-profile-publish-list").append(html);
				$("#commentsLength").val(comments.length);
			}else{
				alert("主评论请求失败");
			}
		}
	});
});
//1.主评论删除按钮单击事件
$(document).on('click', "a.delete", function(){
	var commentId = $(this).data("id");
	var item = $(this).parent().parent().parent().parent();
	$.ajax({
		type : "post",
		url : "/comment",
		data : {
			_method: 'DELETE',
			commentId: commentId
		},
		success: function(data){
			if (data['status'] == 'success') {
				item.remove();
			} else {
				alert("删除失败");
			}
		}
	});
})
