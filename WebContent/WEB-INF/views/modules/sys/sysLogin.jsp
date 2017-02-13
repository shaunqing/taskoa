<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务工作OA系统 - 登录</title>
</head>
<body>

	<div class="container">
		<div class="row">
		
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h2 style="text-align: center;">任务工作OA系统 </h2>
					</div>
					
					<sys:message/>
					
					<div class="panel-body">
						<form:form action="login" method="post">
							<div class="form-group">
								<h3><small>用户名</small></h3>
								<input id="loginname" name="loginname" class="form-control" autofocus>
							</div>
							<div class="form-group">
								<h3><small>密码</small></h3>
								<input id="password" name="password"  class="form-control" type="password" >
							</div>
							<div class="checkbox">
								<label>
									<input name="remember" type="checkbox" value="Remember Me">Remember Me
								</label>
							</div>
							<input type="submit" class="btn btn-lg btn-success btn-block" value="登录" />
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>