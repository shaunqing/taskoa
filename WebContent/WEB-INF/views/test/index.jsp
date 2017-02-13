<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/meta.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
	$("#inputForm").validate({
		submitHandler: function(form){
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			alert("xxx");
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
});
	</script>
	 
</head>
<body>
<sys:message /> 
<!-- form标签来自spring，使用此标签后，后台接收entity即可获取 -->
<form:form  id="inputForm" action="${ctx}/havij" method="get">
<input type="text" id="loginname" name="loginname">
<input type="text" id="password" name="password">
<input type="submit" value="submit">
</form:form>
1111
<a href="${basePath}task/list">test</a>
	

</body>
</html>