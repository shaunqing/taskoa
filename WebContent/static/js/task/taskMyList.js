function deleteTask(ctx, taskid) {
	layer.confirm('确认删除吗?', {
		icon : 0,
		closeBtn : 0,
		title : '提示'
	}, function(index) {
		$.ajax({
			url : ctx + "/task/mydelete",
			data : {
				taskid : taskid
			},
			type : 'POST',
			success : function(data) {
				if(data == "success") {
					// 获取当前页码
					var pageing = $(".pagination .page.active a").html();
					// 提示删除，并刷新
					tipSuccessAndRefresh("删除成功！",ctx + "/task/mylist/" + pageing);
				} else {
					showError(data);
				}
			},
			errot : function(error) {
				showError(error);
			}
		});
	});
}
