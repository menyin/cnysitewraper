<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>快递模板管理</title>
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
		<li><a href="${ctx}/config/wsExFaretemplate/">快递模板列表</a></li>
		<li class="active"><a href="${ctx}/config/wsExFaretemplate/form?id=${wsExFaretemplate.id}">快递模板<shiro:hasPermission name="config:wsExFaretemplate:edit">${not empty wsExFaretemplate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="config:wsExFaretemplate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wsExFaretemplate" action="${ctx}/config/wsExFaretemplate/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">模板名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货时间：</label>
			<div class="controls">
				<form:input path="dispatchTime" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货地址：</label>
			<div class="controls">
				<form:input path="shopAddr" htmlEscape="false" maxlength="2500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计价方式：</label>
			<div class="controls">
				<form:select path="valuationModel" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('valuation_model')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否指定条件包邮：</label>
			<div class="controls">
				<form:radiobuttons path="isInclPostAgeByif" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">运送方式：</label>
			<div class="controls">
				<form:select path="carryWay" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('carry_way')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">快递运输方式表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>地区</th>
								<th>首件数量</th>
								<th>首件重量</th>
								<th>首体积</th>
								<th>首费用</th>
								<th>续件</th>
								<th>续重量</th>
								<th>续体积</th>
								<th>续费</th>
								<th>是否默认运费</th>
								<th>标记</th>
								<shiro:hasPermission name="config:wsExFaretemplate:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="wsExCarrymodeList">
						</tbody>
						<shiro:hasPermission name="config:wsExFaretemplate:edit"><tfoot>
							<tr><td colspan="13"><a href="javascript:" onclick="addRow('#wsExCarrymodeList', wsExCarrymodeRowIdx, wsExCarrymodeTpl);wsExCarrymodeRowIdx = wsExCarrymodeRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="wsExCarrymodeTpl">//<!--
						<tr id="wsExCarrymodeList{{idx}}">
							<td class="hide">
								<input id="wsExCarrymodeList{{idx}}_id" name="wsExCarrymodeList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="wsExCarrymodeList{{idx}}_delFlag" name="wsExCarrymodeList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<%--<sys:treeselect id="wsExCarrymodeList{{idx}}_region" name="wsExCarrymodeList[{{idx}}].region.id" value="{{row.region.id}}" labelName="wsExCarrymodeList{{idx}}." labelValue="{{row.}}"
									title="区域" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>--%>
									<sys:treeselect id="wsExCarrymodeList{{idx}}_region" name="wsExCarrymodeList[{{idx}}].region.id" value="{{row.region.id}}" labelName="wsExCarrymodeList[{{idx}}].region.name" labelValue="{{row.region.name}}"
									title="区域" url="/sys/area/treeData" cssClass="" allowClear="true" notAllowSelectParent="true"/>
							</td>
							<td>
								<input id="wsExCarrymodeList{{idx}}_firstPiece" name="wsExCarrymodeList[{{idx}}].firstPiece" type="text" value="{{row.firstPiece}}" class="input-small  number"/>
							</td>
							<td>
								<input id="wsExCarrymodeList{{idx}}_firstWeight" name="wsExCarrymodeList[{{idx}}].firstWeight" type="text" value="{{row.firstWeight}}" class="input-small  number"/>
							</td>
							<td>
								<input id="wsExCarrymodeList{{idx}}_firstBulk" name="wsExCarrymodeList[{{idx}}].firstBulk" type="text" value="{{row.firstBulk}}" class="input-small  number"/>
							</td>
							<td>
								<input id="wsExCarrymodeList{{idx}}_firstAmount" name="wsExCarrymodeList[{{idx}}].firstAmount" type="text" value="{{row.firstAmount}}" class="input-small  number"/>
							</td>
							<td>
								<input id="wsExCarrymodeList{{idx}}_secondPiece" name="wsExCarrymodeList[{{idx}}].secondPiece" type="text" value="{{row.secondPiece}}" class="input-small  number"/>
							</td>
							<td>
								<input id="wsExCarrymodeList{{idx}}_secondWeight" name="wsExCarrymodeList[{{idx}}].secondWeight" type="text" value="{{row.secondWeight}}" class="input-small  number"/>
							</td>
							<td>
								<input id="wsExCarrymodeList{{idx}}_secondBulk" name="wsExCarrymodeList[{{idx}}].secondBulk" type="text" value="{{row.secondBulk}}" class="input-small  number"/>
							</td>
							<td>
								<input id="wsExCarrymodeList{{idx}}_secondAmount" name="wsExCarrymodeList[{{idx}}].secondAmount" type="text" value="{{row.secondAmount}}" class="input-small  number"/>
							</td>
							<td>
								<c:forEach items="${fns:getDictList('yes_no')}" var="dict" varStatus="dictStatus">
									<span><input id="wsExCarrymodeList{{idx}}_isDefault${dictStatus.index}" name="wsExCarrymodeList[{{idx}}].isDefault" type="radio" value="${dict.value}" data-value="{{row.isDefault}}"><label for="wsExCarrymodeList{{idx}}_isDefault${dictStatus.index}">${dict.label}</label></span>
								</c:forEach>
							</td>
							<td>
								<textarea id="wsExCarrymodeList{{idx}}_remarks" name="wsExCarrymodeList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="config:wsExFaretemplate:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#wsExCarrymodeList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var wsExCarrymodeRowIdx = 0, wsExCarrymodeTpl = $("#wsExCarrymodeTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(wsExFaretemplate.wsExCarrymodeList)};
							for (var i=0; i<data.length; i++){
								addRow('#wsExCarrymodeList', wsExCarrymodeRowIdx, wsExCarrymodeTpl, data[i]);
								wsExCarrymodeRowIdx = wsExCarrymodeRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="config:wsExFaretemplate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>