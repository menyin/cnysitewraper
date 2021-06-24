<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
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
		<li class="active"><a href="${ctx}/prod/wsProduct/">产品列表</a></li>
		<shiro:hasPermission name="prod:wsProduct:edit"><li><a href="${ctx}/prod/wsProduct/form">产品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wsProduct" action="${ctx}/prod/wsProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品分类：</label>
			</li>
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="512" class="input-medium"/>
			</li>
			<li><label>产品状态：</label>
				<form:select path="onGoodState" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品分类</th>
				<th>品牌</th>
				<th>产品名称</th>
				<th>标题</th>
				<th>是否首页推荐</th>
				<th>是否赠品</th>
				<th>是否支持退货</th>
				<th>已售数量</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="prod:wsProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="wsProduct">
			<tr>
				<td><a href="${ctx}/prod/wsProduct/form?id=${wsProduct.id}">
					${wsProduct.prodCategory.name}
				</a></td>
				<td>
					${fns:getDictLabel(wsProduct.brand.id, '', '')}
				</td>
				<td>
					${wsProduct.pname}
				</td>
				<td>
					${wsProduct.title}
				</td>
				<td>
					${fns:getDictLabel(wsProduct.isHomeRecommd, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(wsProduct.isGift, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(wsProduct.isReturn, 'yes_no', '')}
				</td>
				<td>
					${wsProduct.selNum}
				</td>
				<td>
					<fmt:formatDate value="${wsProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${wsProduct.remarks}
				</td>
				<shiro:hasPermission name="prod:wsProduct:edit"><td>
    				<a href="${ctx}/prod/wsProduct/form?id=${wsProduct.id}">修改</a>
					<a href="${ctx}/prod/wsProduct/delete?id=${wsProduct.id}" onclick="return confirmx('确认要删除该产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>