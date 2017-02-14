<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${ctxStatic}/js/task/taskMyList.js"></script>
<script type="text/javascript">
$(function() {
	initJqPaginator("mylistPage",'${pageModel.totalPages}','${pageModel.currentPage}','${ctx}/task/mylist/');
});
</script>
<title>我创建的任务</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp"></jsp:include>

	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">
					任务单管理<small> / 我创建的任务</small>
				</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
			<sys:message />
				<div class="panel panel-default">
					<div class="panel-heading">
						<b>已创建任务</b>
					</div>
					<div class="panel-body">
						<table
							class="table table-striped table-bordered table-hover tablesorter table-responsive">
							<thead>
								<tr>
									<th width="7%">序号 <i class="fa fa-sort"></i></th>
									<th width="10%">任务编号</th>
									<th width="20%">任务名称</th>
									<th width="8%">执行人</th>
									<th width="11%">开始时间</th>
									<th width="11%">截止时间</th>
									<th width="11%">创建时间</th>
									<th width="7%">状态</th>
									<th width="15%">操作</th>
								</tr>
							</thead>
							<tbody id="mylistTable">
								<c:set var="listNum" value="0" />
								<c:forEach items="${pageModel.pageList}" var="task">
									<tr>
										<td>${(pageModel.currentPage - 1) * pageModel.pageSize + listNum + 1}
										</td>
										<td>${task.taskno}</td>
										<td><c:choose>
											<c:when test="${fn:length(task.taskname) > '20'}">
												${fn:substring(task.taskname,0,20)}...
											</c:when>
											<c:otherwise>
												${task.taskname}
											</c:otherwise>
										</c:choose></td>
										<td>${task.operator.username}</td>
										<td>${fn:substring(task.time_start,0,19)}</td>
										<td>${fn:substring(task.time_end,0,19)}</td>
										<td>${fn:substring(task.time_created,0,19)}</td>
										<td>${task.taskstat}</td>
										<td><c:choose>
												<c:when test="${task.taskstat == '待完成'}">
													<a href="${ctx}/task/mytask/${task.taskid}">
														<i class="fa fa-pencil fa-fw"></i>修改
													</a>
													&nbsp;&nbsp;
													<a href="javascript:void(0);" onclick="deleteTask('${ctx}',${task.taskid})"> 
														<i class="fa fa-trash-o fa-fw"></i>删除
													</a>
												</c:when>
												<c:otherwise>
													<a href="${ctx}/task/myview/${task.taskid}">
														<i class="fa fa-envelope fa-fw"></i>查看
													</a>
													&nbsp;&nbsp;
													<a href="javascript:void(0);" onclick="">
														<i class="fa fa-cloud-download fa-fw"></i>结果下载
													</a>
												</c:otherwise>
										</c:choose></td>
									</tr>
									<c:set var="listNum" value="${listNum + 1}" />
								</c:forEach>
							</tbody>
						</table>

						<div id="mylistPage" style="text-align: center;"></div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>