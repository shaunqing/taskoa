<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--自定义标签--%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<%--meta info--%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="${ctxStatic}/library/ie9/html5shiv.min.js"></script>
	<script src="${ctxStatic}/library/ie9/respond.min.js"></script>
<![endif]-->
    
<%--Bootstrap Core CSS--%>
<link href="${ctxStatic}/library/bootstrap/css/bootstrap.css" rel="stylesheet">
<%-- MetisMenu CSS --%>
<link href="${ctxStatic}/library/metisMenu/metisMenu.min.css" rel="stylesheet">
<%-- Custom CSS --%>
<link href="${ctxStatic}/library/dist/css/sb-admin-2.css" rel="stylesheet">
<%-- Custom Fonts --%>
<link href="${ctxStatic}/library/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<%-- jquery-validation --%>
<link href="${ctxStatic}/library/jquery-validation/jquery.validate.min.css" rel="stylesheet">


<%-- jQuery --%>
<script src="${ctxStatic}/library/jquery/jquery.min.js"></script>
<%-- ajaxForm --%>
<script src="${ctxStatic}/library/jquery/jquery.form.js"></script>
<%-- Bootstrap Core JavaScript --%>
<script src="${ctxStatic}/library/bootstrap/js/bootstrap.min.js"></script>
<%-- Layer --%>
<script src="${ctxStatic}/library/layer/layer.js" ></script>
<script src="${ctxStatic}/library/utils/layerUtil.js"></script>
<%-- 手风琴效果菜单 --%>
<script src="${ctxStatic}/library/metisMenu/metisMenu.min.js"></script>
<%-- 左侧菜单折叠 --%>
<script src="${ctxStatic}/library/dist/js/sb-admin-2.min.js"></script>
<%-- jqPaginator --%>
<script src="${ctxStatic}/library/utils/jqPaginator.js"></script>
<script src="${ctxStatic}/library/utils/jqPaginatorInit.js"></script>
<%-- jquery-validation --%>
<script src="${ctxStatic}/library/jquery-validation/jquery.validate.min.js"></script>
<script src="${ctxStatic}/library/jquery-validation/jquery.validate.method.js"></script>
<%-- Table --%>
<script src="${ctxStatic}/library/tablesorter/jquery.tablesorter.js"></script>
<script src="${ctxStatic}/library/tablesorter/tables.js"></script>
<%-- My97DatePicker --%>
<script src="${ctxStatic}/library/My97DatePicker/WdatePicker.js"></script>