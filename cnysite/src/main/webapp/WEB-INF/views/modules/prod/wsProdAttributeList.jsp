<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格属性管理</title>
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
		<li class="active"><a href="${ctx}/prod/wsProdAttribute/">规格属性列表</a></li>
		<shiro:hasPermission name="prod:wsProdAttribute:edit"><li><a href="${ctx}/prod/wsProdAttribute/form">规格属性添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wsProdAttribute" action="${ctx}/prod/wsProdAttribute/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品类别：</label>
				<sys:treeselect id="category" name="prodCategory.id" value="${wsProdAttribute.prodCategory.id}" labelName="prodCategory.name" labelValue="${wsProdAttribute.prodCategory.name}"
								title="栏目" url="/prod/wsProdCategory/treeData" module="wsProdAttribute" notAllowSelectRoot="false" cssClass="input-small"/>
			</li>
			<li><label>属性名称：</label>
				<form:input path="attrName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品类别</th>
				<th>属性名称</th>
				<th>属性类型</th>
				<th>是否搜索</th>
				<th>更新时间</th>
				<shiro:hasPermission name="prod:wsProdAttribute:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsProdAttribute">
			<tr>
				<td><a href="${ctx}/prod/wsProdAttribute/form?id=${wsProdAttribute.id}">
					${wsProdAttribute.prodCategory.name}
				</a></td>
				<td>
					${wsProdAttribute.attrName}
				</td>
				<td>
					${fns:getDictLabel(wsProdAttribute.attrType, 'product_attribute_type', '')}
				</td>
				<td>
					${fns:getDictLabel(wsProdAttribute.isSearch, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${wsProdAttribute.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="prod:wsProdAttribute:edit"><td>
    				<a href="${ctx}/prod/wsProdAttribute/form?id=${wsProdAttribute.id}">修改</a>
					<a href="${ctx}/prod/wsProdAttribute/delete?id=${wsProdAttribute.id}" onclick="return confirmx('确认要删除该规格属性吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>