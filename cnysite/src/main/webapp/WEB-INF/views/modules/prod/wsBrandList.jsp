<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>品牌管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/prod/wsBrand/">品牌列表</a></li>
		<shiro:hasPermission name="prod:wsBrand:edit"><li><a href="${ctx}/prod/wsBrand/form">品牌添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wsBrand" action="${ctx}/prod/wsBrand/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>中文名称：</label>
				<form:input path="cnname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>英文名称：</label>
				<form:input path="enname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>官网地址：</label>
				<form:input path="websiteurl" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>中文名称</th>
				<th>英文名称</th>
				<th>logo</th>
				<th>是否有效</th>
				<th>官网地址</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="prod:wsBrand:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsBrand">
			<tr>
				<td><a href="${ctx}/prod/wsBrand/form?id=${wsBrand.id}">
					${wsBrand.cnname}
				</a></td>
				<td>
					${wsBrand.enname}
				</td>
				<td>
					<img alt="" src="${wsBrand.logo}" style="width:50px;">
				</td>
				<td>
					${fns:getDictLabel(wsBrand.state, 'yes_no', '')}
				</td>
				<td>
					${wsBrand.websiteurl}
				</td>
				<td>
					<fmt:formatDate value="${wsBrand.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${wsBrand.remarks}
				</td>
				<shiro:hasPermission name="prod:wsBrand:edit"><td>
    				<a href="${ctx}/prod/wsBrand/form?id=${wsBrand.id}">修改</a>
					<a href="${ctx}/prod/wsBrand/delete?id=${wsBrand.id}" onclick="return confirmx('确认要删除该品牌吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>