//文件改变事件
$(document).on('change', "#file", function() {
	$.ajaxFileUpload({
		url : '/avatar/upload',//处理上传用的后台程序,可以是PHP,也可以是ASP等
		secureuri : false,//异步
		fileElementId : 'file',//上传控件ID
		dataType : 'application/json',//返回的数据信息格式
		type : 'post',
		success : function(data, status) {
			data = $.parseJSON(data.replace(/<.*?>/ig,""));
			if (status == 'success' && data['status']) {
				
				$("img.aw-border-radius-5").attr('src', data['list'][0]);
				$("input.avatar").val(data['list'][0]);
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