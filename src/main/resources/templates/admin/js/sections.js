$(function() {
	//1.修改按钮单击事件
	$("a.mod").click(function() {
		var sectionId = $(this).data("id");
		$.ajax({
			type : "GET",
			url : "/admin/section",
			data : {
				sectionId : sectionId
			},
			dataType : "json",
			success : function(data) {

				if (data['status'] == 'success') {
					$("#id").val(data['section'].id);
					$("#name").val(data['section'].name);
					$("#avatar").attr('src', '../' + data['section'].avatar);
					$("#introduce").val(data['section'].introduce);
				} else {
					alter("获取数据失败");
				}
			}
		})
	})
})

//
$(document).on('change', "input[type='file']", function() {
	var that = $(this);
	var img = that.parent().find("img");
	var input = that.parent().find("input[name='avatar']");
	var id = that.attr('id');
	$.ajaxFileUpload({
		url : '/avatar/upload',//处理上传用的后台程序,可以是PHP,也可以是ASP等
		secureuri : false,//异步
		fileElementId : id,//上传控件ID
		dataType : 'application/json',//返回的数据信息格式
		type : 'post',
		success : function(data, status) {
			data = $.parseJSON(data.replace(/<.*?>/ig, ""));
			if (data['status'] == 'success') {
				var path = data['list'][0];
				img.attr('src', '../' + path);
				input.val(path);
			} else {
				console.log("上传失败");
			}
		},
		error : function(data, status, e) {
			//服务器响应失败处理函数
			alert(e);
		}
	});
});