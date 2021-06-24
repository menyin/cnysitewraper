<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>规格属性管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/prod/wsProdAttribute/">规格属性列表</a></li>
		<li class="active"><a href="${ctx}/prod/wsProdAttribute/form?id=${wsProdAttribute.id}">规格属性<shiro:hasPermission name="prod:wsProdAttribute:edit">${not empty wsProdAttribute.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="prod:wsProdAttribute:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wsProdAttribute" action="${ctx}/prod/wsProdAttribute/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品类别：</label>
			<div class="controls">
				<sys:treeselect id="category" name="prodCategory.id" value="${wsProdAttribute.prodCategory.id}" labelName="prodCategory.name" labelValue="${wsProdAttribute.prodCategory.name}"
								title="栏目" url="/prod/wsProdCategory/treeData" module="wsProdAttribute" notAllowSelectRoot="false" cssClass="input-small"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">属性名称：</label>
			<div class="controls">
				<form:input path="attrName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">属性类型：</label>
			<div class="controls">
				<form:select path="attrType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_attribute_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">输入类型：</label>
			<div class="controls">
				<form:select path="inputType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('input_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否搜索：</label>
			<div class="controls">
				<form:radiobuttons path="isSearch" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否必填：</label>
			<div class="controls">
				<form:radiobuttons path="isRequire" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态（是否有效）：</label>
			<div class="controls">
				<form:radiobuttons path="state" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">规格属性值表（规格属性表的子表）：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>规格属性值</th>
								<th>是否有效</th>
								<th>排序</th>
								<th>备注信息</th>
								<shiro:hasPermission name="prod:wsProdAttribute:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="wsProdAttrivalueList">
						</tbody>
						<shiro:hasPermission name="prod:wsProdAttribute:edit"><tfoot>
							<tr><td colspan="6"><a href="javascript:" onclick="addRow('#wsProdAttrivalueList', wsProdAttrivalueRowIdx, wsProdAttrivalueTpl);wsProdAttrivalueRowIdx = wsProdAttrivalueRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="wsProdAttrivalueTpl">//<!--
						<tr id="wsProdAttrivalueList{{idx}}">
							<td class="hide">
								<input id="wsProdAttrivalueList{{idx}}_id" name="wsProdAttrivalueList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="wsProdAttrivalueList{{idx}}_delFlag" name="wsProdAttrivalueList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="wsProdAttrivalueList{{idx}}_attrvalueValue" name="wsProdAttrivalueList[{{idx}}].attrvalueValue" type="text" value="{{row.attrvalueValue}}" maxlength="255" class="input-small "/>
							</td>
							<td>
								<c:forEach items="${fns:getDictList('yes_no')}" var="dict" varStatus="dictStatus">
									<span><input id="wsProdAttrivalueList{{idx}}_state${dictStatus.index}" name="wsProdAttrivalueList[{{idx}}].state" type="radio" value="${dict.value}" data-value="{{row.state}}"><label for="wsProdAttrivalueList{{idx}}_state${dictStatus.index}">${dict.label}</label></span>
								</c:forEach>
							</td>
							<td>
								<input id="wsProdAttrivalueList{{idx}}_sort" name="wsProdAttrivalueList[{{idx}}].sort" type="text" value="{{row.sort}}" maxlength="10" class="input-small  digits"/>
							</td>
							<td>
								<textarea id="wsProdAttrivalueList{{idx}}_remarks" name="wsProdAttrivalueList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="prod:wsProdAttribute:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#wsProdAttrivalueList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var wsProdAttrivalueRowIdx = 0, wsProdAttrivalueTpl = $("#wsProdAttrivalueTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(wsProdAttribute.wsProdAttrivalueList)};
							for (var i=0; i<data.length; i++){
								addRow('#wsProdAttrivalueList', wsProdAttrivalueRowIdx, wsProdAttrivalueTpl, data[i]);
								wsProdAttrivalueRowIdx = wsProdAttrivalueRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="prod:wsProdAttribute:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>