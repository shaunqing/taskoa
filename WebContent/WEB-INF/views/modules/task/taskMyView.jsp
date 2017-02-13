<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>任务查看</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp"></jsp:include>

	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">
					任务单管理<small> / <a href="${ctx}/task/mylist/1">我创建的任务</a> / 任务查看
					</small>
				</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">任务详情修改</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-6">
								<div class="form-group">
									<label>任务名称</label>
									<input value='${mytask.taskname}' class="form-control" readonly="readonly" >
								</div>
								<div class="form-group">
									<label>任务编号</label>
									<input value='${mytask.taskno}' class="form-control" readonly="readonly" >
								</div>
								<div class="form-group">
									<label>执行人</label>
									<input value='${mytask.operator.username}' class="form-control" readonly="readonly" >
								</div>
								<div class="form-group">
									<label>创建时间</label>
									<input value='${mytask.time_created}' class="form-control" readonly="readonly" >
								</div>
								<div class="form-group">
									<label>任务状态</label>
									<input value='${mytask.taskstat}' class="form-control" readonly="readonly" >
								</div>
							</div>

							<div class="col-lg-6">
								<div class="form-group">
									<label>开始时间</label>
									<input value="${mytask.time_start}" class="form-control" readonly="readonly" >
								</div>

								<div class="form-group">
									<label>结束时间</label>
									<input value="${mytask.time_end}" class="form-control" readonly="readonly" >
								</div>
								<div class="form-group">
									<label>任务描述</label>
									<textarea class="form-control" rows="5" readonly="readonly">${mytask.taskdesc}</textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">任务附件文件</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th width="50%">附件名称</th>
											<th width="25%">上传时间</th>
											<th width="25%">操作</th>
										</tr>
									</thead>
									<tbody id="table_file0">
										<c:choose>
											<c:when test="${file0.size() == 0}">
												<tr>
													<td colspan=3>无附件文件</td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${file0}" var="file0">
													<tr>
														<td>${file0.taskfilename}</td>
														<td>${file0.operatime}</td>
														<td>
															<a href="${ctx}/task/mydown/${file0.taskfileid}">
																<i class="fa fa-cloud-download fa-fw"></i>下载
															</a>
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">任务结果</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<div class="form-group">
									<label>任务结果说明</label>
									<textarea class="form-control" rows="5" readonly="readonly">${mytask.resultdesc}</textarea>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th width="50%">附件名称</th>
											<th width="25%">上传时间</th>
											<th width="25%">操作</th>
										</tr>
									</thead>
									<tbody id="table_file1">
										<c:choose>
											<c:when test="${file1.size() == 0}">
												<tr>
													<td colspan=3 >无结果文件</td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${file1}" var="file1">
													<tr>
														<td>${file1.taskfilename}</td>
														<td>${file1.operatime}</td>
														<td>
															<a href="${ctx}/task/mydown/${file1.taskfileid}">
																<i class="fa fa-cloud-download fa-fw"></i>下载
															</a>
														</td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>