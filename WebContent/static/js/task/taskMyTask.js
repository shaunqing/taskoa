$("#taskFile").fileinput({
	// 设置语言
	language : 'zh',
	uploadUrl : "task/updateTaskFile",
	maxFileCount : 3,
	uploadExtraData : {
		taskid : $("#taskid").val()
	}
}).on('filebatchuploadsuccess', function(event, data, previewId, index) {
	var info = data.response.message;
	if (info == "success") {
		layer.alert("附件上传成功", {
			icon : 1,
			closeBtn : 0,
			anim : 5
		}, function() {
			init();
		});
	} else {
		if (info == null) {
			info = "程序异常！";
		}
		layer.alert(info, {
			icon : 2,
			closeBtn : 0,
			anim : 5
		});
	}
});

// 重新加载页面
function init() {
	window.location.href = "task/mytask/" + $("#taskid").val();
}

// 删除附件
function deleteTaskFile(ctx, taskfileid) {
	layer.confirm('删除后将不可恢复，确认删除吗?', {
		icon : 0,
		closeBtn : 0,
		title : '提示'
	}, function(index) {
		$.ajax({
			url : ctx + '/task/mydeletefile0/' + taskfileid,
			type : 'POST',
			success : function(data) {
				if (data == "success") {
					layer.alert("附件删除成功", {
						icon : 1,
						closeBtn : 0,
						anim : 5
					}, function() {
						location = ctx + "/task/mytask/" + $("#taskid").val();
					});
				} else {
					layer.alert(data, {
						icon : 2,
						closeBtn : 0,
						anim : 5
					});
				}
			}
		});
	});
}
