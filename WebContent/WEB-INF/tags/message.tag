<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<c:if test="${not empty message}">
	<div id="messageBox" class="alert alert-${message.ctype}">
		<button data-dismiss="alert" class="close">×</button>${message.content}
	</div>
	<!-- 
	<c:choose>
		<c:when test="${message.ctype == 'success'}">
			<script type="text/javascript">
			layer.msg('${message.content}', {icon: 1});
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
			layer.msg('${message.content}', {icon: 2});
			</script>
		</c:otherwise>
	</c:choose>
	 -->
</c:if>