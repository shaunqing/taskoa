<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>任务修改</title>
<!-- 修正fileupload控件中触发的url -->
<base href="${ctx}" />
<!-- 上传文件控件 -->
<link href="${ctxStatic}/library/fileinput/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<script src="${ctxStatic}/library/fileinput/fileinput.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/library/fileinput/fileinput_locale_zh.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp"></jsp:include>

	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h2 class="page-header">
					任务单管理<small> / <a href="${ctx}/task/mylist/1">我创建的任务</a> / 任务详情修改
					</small>
				</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<sys:message />
				<div class="panel panel-default">
					<div class="panel-heading">任务详情修改</div>
					<div class="panel-body">
						<form:form action="${ctx}/task/myupdateTask" method="post">
						<div class="row">
							<div class="col-lg-12">
								<input type="hidden" id="taskid" name="taskid" value='${myTask[0].taskid}'>
								<div class="form-inline">
									<label>任务名称：</label>
									<input id="taskname" name="taskname" value='${myTask[0].taskname}' class="form-control" style="width: 80%">
								</div>
								<br>
								<div class="form-group">
									<label>任务编号：${myTask[0].taskno}</label>
								</div>
								<div class="form-group">
									<label>执行人：${myTask[0].operator.username}</label>
								</div>
								<div class="form-group">
									<label>创建时间：${myTask[0].time_created}</label>
								</div>
								<div class="form-group">
									<label>任务状态：${myTask[0].taskstat}</label>
								</div>
								<div class="form-inline">
									<div class="form-group">
										<label>开始时间：</label>
										<input id="time_start" name="time_start" value="${fn:substring(myTask[0].time_start,0,19)}"  class="form-control" readonly="readonly"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'time_end\')}'});">
									</div>
									&nbsp;&nbsp;
									<div class="form-group">
										<label>结束时间：</label>
										<input id="time_end" name="time_end" value="${fn:substring(myTask[0].time_end,0,19)}" class="form-control"  readonly="readonly"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'time_start\')}'});">
									</div>
								</div>
								<br>
								<div class="form-group">
									<label>任务描述</label>
									<textarea id="taskdesc" name="taskdesc" class="form-control" rows="5">${myTask[0].taskdesc}</textarea>
								</div>
								<div class="form-group" style="text-align: center;">
									<br/>
									<input type="submit" value="修改" class="btn btn-primary">
									&nbsp;&nbsp;
									<a type="button" class="btn btn-primary" href="${ctx}/task/mylist/1">返回</a>
								</div>
							</div>
						</div>
						</form:form>
					</div>
				</div>
			</div>
			
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">任务附件文件</div>
					<div class="panel-body">
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
									<c:when test="${empty myTask[0].taskfile}">
										<tr>
											<td colspan=3>无附件文件</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${myTask}" var="file">
											<tr>
												<td>${file.taskfile.taskfilename}</td>
												<td>${fn:substring(file.taskfile.operatime,0,19)}</td>
												<td>
													<a href="${ctx}/task/mydown/${file.taskfile.taskfileid}">
														<i class="fa fa-cloud-download fa-fw"></i>下载
													</a>
													&nbsp;&nbsp;
													<a href="javascript:void(0);" onclick="deleteTaskFile('${ctx}', ${file.taskfile.taskfileid})">
														<i class="fa fa-cloud-download fa-fw"></i>删除
													</a>
												</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
						
						<div class="form-group">
							<label>修改上传附件（最多上传3个文件，且该任务中只能保留3个文件）</label>
							<input type="file" id="taskFile" name="taskFile" class="file" 
								data-show-preview="false" multiple 
								data-allowed-file-extensions='["doc", "docx", "xls", "xlsx", "ppt","pptx", "rar"]'>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="${ctxStatic}/js/task/taskMyTask.js"></script>
</body>
</html>