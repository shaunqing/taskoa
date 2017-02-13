function tipSuccess(data) {
	layer.msg(data, {
		icon : 1
	});
}

function tipSuccessAndRefresh(data, url) {
	layer.msg(data, {
		icon : 1,
		time : 1000 // 2秒关闭（如果不配置，默认是3秒）
	}, function() {
		location = url;
	});
}

function showError(data) {
	layer.alert(data, {
		title : '错误提示',
		icon : 2,
		closeBtn : 0,
		anim : 5
	});
}