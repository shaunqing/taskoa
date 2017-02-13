function getCreatedTasks(page) {
	$("#table_created tr").remove();
	$.ajax({
		url : "task/getCreatedTasks/" + page,
		type : 'POST',
		success : function(pageModel) {
			// 总页数
			var totalPages = pageModel.totalPages;
			var datas = pageModel.pageList;
			$.each(datas, function(i, n) {
				var trHTML = "<tr>";
				trHTML += "<td>" + ((page - 1) * pageModel.pageSize + i + 1)
						+ "</td>";
				trHTML += "<td>" + n.taskno + "</td>";
				trHTML += "<td>" + n.taskname + "</td>";
				trHTML += "<td>" + n.operator.username + "</td>";
				trHTML += "<td>" + n.time_start + "</td>";
				trHTML += "<td>" + n.time_end + "</td>";
				trHTML += "<td>" + n.time_created + "</td>";
				trHTML += "<td>" + n.taskstat + "</td>";
				trHTML += setOperate(n.taskstat, n.taskid);
				$("#table_created").append(trHTML);
			});
			$('#page_created').jqPaginator('option', {
				totalPages : totalPages,
				currentPage : page,
				onPageChange : function(num, type) {
					if (type === "change") {
						getCreatedTasks(num);
					}
				}
			});
		},
		error : function(result) {
			layer.alert(result.responseText, {
				icon : 2,
				closeBtn : 0,
				anim : 5
			});
		}
	});
}

function setOperate(status, taskid) {
	if (status == "待完成") {
		return "<td><a href=\"task/showTaskUpdate/"
				+ taskid
				+ "\"><i class=\"fa fa-pencil fa-fw\"></i>修改</a>&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=\"deleteTask("
				+ taskid
				+ ")\"><i class=\"fa fa-trash-o fa-fw\"></i>删除</a></td>";
	} else {
		return "<td><a href=\"task/showTask/"
				+ taskid
				+ "\"><i class=\"fa fa-envelope fa-fw\"></i>查看</a>&nbsp;&nbsp;<a onclick=\"downCreatedTaskFile("
				+ taskid
				+ ")\" href=\"javascript:void(0)\"><i class=\"fa fa-cloud-download fa-fw\"></i>结果下载</a></td>";
	}
}

function deleteTask(ctx, taskid) {
	var s = ctx;
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
				tipSuccess(data);
			},
			errot : function(error) {
				showError(error);
			}
		});
	});
}
