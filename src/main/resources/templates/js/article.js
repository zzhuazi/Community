$(function() {
	//1.点击发表主评论按钮
	$("a.btn-comment").click(function() {
		var articleId = request['articleId'];
		var content = UE.getEditor('editor').getContent();
		$.ajax({
			type : "post",
			url : "/comment",
			async : true,
			data : {
				articleId : articleId,
				content : content
			},
			success : function(data) {
				if (data['status'] == 'success') {
					//发表主评论成功
					window.location = "/article?articleId=" + articleId;
				} else {
					//失败
					alert("评论失败");
				}
			}
		});
	});
	//2.更多主评论按钮单击事件
	$("a.more-comments").click(function() {
		var page = $("#page").val();
		page++;
		console.log(page);
		if (page == 1) {
			$(this).find("span").text("暂无更多评论了");
			return;
		}
		$.ajax({
			type : "get",
			url : "/comments",
			async : true,
			data : {
				currentpage : page,
				articleId : request['articleId']
			},
			success : function(data) {
				console.log(data);
				if (data['status'] == 'success') {
					var comments = data['comments'];
					var html = $("#comment").render({"comments": comments, "online": online});
					$("div.aw-feed-list").append(html);
					$("#page").val(data["page"]);
				} else {
					alert("请求失败");
				}
			}
		});
	});
});
//3.主评论的次评论数按钮点击事件
$(document).on('click', "a.aw-add-comment", function(){
	var commentA = $(this);
	//展开或者收起该主评论下的次评论
	$(this).parent().parent().next().toggle();
	//查看次评论page 默认1
	var page = $(this).parent().parent().next().find("input.reply-page").val();
	var commentId = $(this).parent().parent().next().find("input.comment-id").val();
	if(page == 1) {
		//首次展开
		$.ajax({
			type : "get",
			url : "/replies",
			async : true,
			data : {
				currentpage : page,
				commentId: commentId
			},
			success : function(data) {
				console.log(data);
				if (data['status'] == 'success') {
					var html = $("#reply").render({"replies": data['replies'], "online" : online});
					var ul = commentA.parent().parent().next().find("ul");
					ul.append(html);
					commentA.parent().parent().next().find("input.reply-page").val(data['page']);
					if(data['page'] == 0) {
						commentA.parent().parent().next().find("a.more-replies").find("span").text("暂无更多回复哦");
					}
				} else {
					alert("请求失败");
				}
			}
			
		});
	}
})
//4.更多次评论按钮单击事件
$(document).on('click', "a.more-replies", function(){
	console.log("更多次评论按钮单击事件");
	var page = $(this).find("input.reply-page").val();
	var commentId = $(this).find("input.comment-id").val();
	var that = $(this);
	var ul = $(this).parent().find("ul");
	if (page > 0) {
		$.ajax({
			type : "get",
			url : "/replies",
			async : true,
			data : {
				currentpage : page,
				commentId: commentId
			},
			success : function(data) {
				if (data['status'] == 'success') {
					var replies = new Array();
					replies[0] = data['replies'];
					var html = $("#reply").render({"replies": replies, "online" : online});
					ul.append(html);
					that.find("input.reply-page").val(data['page']);
					if(data['page'] == 0) {
						that.find("span").text("暂无更多回复哦");
					}
				} else {
					alert("请求失败");
				}
			}
		});
	}
})
//5.发表次评论按钮单击事件
$(document).on('click', "a.reply", function(){
	var that = $(this);
	var commentId = that.parent().parent().parent().parent().parent().find("input.comment-id").val();
	var textarea = that.parent().parent().parent().find("textarea");
	var input = that.parent().parent().parent().find("input.reply-id");
	var repliedId = input.val();
	var content = textarea.val();
	console.log("repliedId : " + repliedId);
	$.ajax({
		type : "post",
		url : "/reply",
		async : true,
		data : {
			commentId : commentId,
			content: content,
			repliedId: repliedId
		},
		success : function(data) {
			console.log(data);
			console.log(online);
			if (data['status'] == 'success') {
				var replies = new Array();
				replies = data['replies'];
				var html = $("#reply").render({"replies": replies, "online" : online});
				console.log(html);
				that.parent().parent().parent().parent().find("ul").prepend(html);
				textarea.val("");
				textarea.attr('placeholder', '回复一下...');
				input.val('');
			} else {
				alert("回复失败！");
			}
		}
	});
})
//6.回复此评论按钮单击事件
$(document).on('click', "a.reply-to-reply", function(){
	//获取编辑器
	var that = $(this);
	var replyId = that.data("reply-id");
	var div = that.parent().parent().parent().parent().parent().parent().parent();
	var author = that.parent().parent().find("a.author").text();
	var box = div.find("div.aw-comment-box-main");
	box.find("input.reply-id").val(replyId);
	box.find("textarea").focus();
	if (typeof(author) != 'undefine' && author != '')
		box.find("textarea").attr('placeholder','回复  ' + author + '  ...');
})
//7.清空回复次评论按钮单击事件
$(document).on('click', "a.close-comment-box", function(){
	var that = $(this);
	var box = that.parent().parent().parent();
	box.find("textarea").attr('placeholder', '回复一下...');
	box.find("input.reply-id").val('');
})
