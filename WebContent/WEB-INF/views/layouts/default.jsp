<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<!DOCTYPE html>
<html style="overflow-x: auto; overflow-y: auto;">
<head>
<title><sitemesh:title /></title>
<sitemesh:head />
</head>
<body>
	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		style="margin-bottom: 0">
		<div class="navbar-header">
			<a class="navbar-brand" href="index.html" style="font-size: x-large;">任务工作OA系统</a>
		</div>

		<!-- 顶部功能栏 -->
		<ul class="nav navbar-top-links navbar-right">
			<!-- 信封 -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-envelope fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-messages">
					<li><a href="#">
							<div>
								<strong>John Smith</strong> <span class="pull-right text-muted">
									<em>Yesterday</em>
								</span>
							</div>
							<div>Lorem ipsum dolor sit amet, consectetur adipiscing
								elit. Pellentesque eleifend...</div>
					</a></li>

					<li class="divider"></li>

					<li><a class="text-center" href="#"> <strong>Read
								All Messages</strong><i class="fa fa-angle-right"></i>
					</a></li>
				</ul></li>

			<!-- 任务进度 -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-tasks fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-tasks">
					<li><a href="#">
							<div>
								<p>
									<strong>Task 1</strong> <span class="pull-right text-muted">40%
										Complete</span>
								</p>
								<div class="progress progress-striped active">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100" style="width: 40%">
										<span class="sr-only">40% Complete (success)</span>
									</div>
								</div>
							</div>
					</a></li>

					<li class="divider"></li>

					<li><a class="text-center" href="#"> <strong>See
								All Tasks</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul></li>

			<!-- 闹钟 -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="#">
							<div>
								<i class="fa fa-comment fa-fw"></i> New Comment <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>

					<li class="divider"></li>

					<li><a class="text-center" href="#"> <strong>See
								All Alerts</strong> <i class="fa fa-angle-right"></i>
					</a></li>
				</ul></li>

			<!-- 用户 -->
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">
					<li><a href="#"><i class="fa fa-user fa-fw"></i> User
							Profile</a></li>
					<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
					<li class="divider"></li>
					<li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i>Logout</a></li>
				</ul></li>
		</ul>

		<!-- 左侧菜单 -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">

					<!-- 搜索框 -->
					<li class="sidebar-search">
						<div class="input-group custom-search-form">
							<input type="text" class="form-control" placeholder="Search...">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
					</li>

					<!-- 任务单管理 -->
					<li><a href="#"><i class="fa fa-tasks fa-fw"></i>任务单管理 <span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="task/showTaskAdd">创建新任务</a></li>
							<li><a href="task/showCreated">我创建的任务</a></li>
							<li><a href="task/showDepart">部门内任务</a></li>
							<li><a href="task/showTodo">被分配任务</a></li>
							<li><a href="task/showShare">共享的任务</a></li>
						</ul></li>

					<!-- 故障单管理 -->
					<li><a href="index.html"><i
							class="glyphicon glyphicon-wrench fa-fw"></i>故障单管理</a></li>

					<!-- 基础数据管理 -->
					<li><a href="metadata"><i class="fa fa-database fa-fw"></i>基础数据管理</a></li>

					<!-- 多级菜单样例 -->
					<li><a href="#"><i class="fa fa-sitemap fa-fw"></i>多级菜单样例
							<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="#">Second Level Item</a></li>
							<li><a href="#">Second Level Item</a></li>
							<li><a href="#">Third Level <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">
									<li><a href="#">Third Level Item</a></li>
									<li><a href="#">Third Level Item</a></li>
									<li><a href="#">Third Level Item</a></li>
									<li><a href="#">Third Level Item</a></li>
								</ul></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>

	<sitemesh:body />

	<div id="footer">Copyright &copy; 2016-2017 Power By LiQing</div>
</body>
</html>