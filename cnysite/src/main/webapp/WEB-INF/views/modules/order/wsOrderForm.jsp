<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li><a href="${ctx}/order/wsOrder/">订单列表</a></li>
		<li class="active"><a href="${ctx}/order/wsOrder/form?id=${wsOrder.id}">订单<shiro:hasPermission name="order:wsOrder:edit">${not empty wsOrder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="order:wsOrder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wsOrder" action="${ctx}/order/wsOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">订单编号：</label>
			<div class="controls">
				<form:input path="orderSn" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				<form:input path="orderState" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付成功：</label>
			<div class="controls">
				<form:input path="jsPayState" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">买家留言：</label>
			<div class="controls">
				<form:textarea path="buysWords" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">会员：</label>
			<div class="controls">
				<form:input path="memberId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推荐人会员：</label>
			<div class="controls">
				<form:input path="ruid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">物流方式：</label>
			<div class="controls">
				<form:input path="logisticsMethod" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">付款时间：</label>
			<div class="controls">
				<input name="paytime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${wsOrder.paytime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发货时间：</label>
			<div class="controls">
				<input name="sendTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${wsOrder.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货时间：</label>
			<div class="controls">
				<input name="receviceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${wsOrder.receviceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支付方式：</label>
			<div class="controls">
				<form:radiobuttons path="payment" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递单号：</label>
			<div class="controls">
				<form:input path="trackingno" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递公司：</label>
			<div class="controls">
				<form:input path="express" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">运费：</label>
			<div class="controls">
				<form:input path="postage" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠券编号：</label>
			<div class="controls">
				<form:input path="couponId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优惠券优惠金额：</label>
			<div class="controls">
				<form:input path="couponMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订单总价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际支付金额：</label>
			<div class="controls">
				<form:input path="reallyPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">赠送积分：</label>
			<div class="controls">
				<form:input path="score" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货地址编号：</label>
			<div class="controls">
				<form:input path="addressId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货人名称：</label>
			<div class="controls">
				<form:input path="consignee" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话：</label>
			<div class="controls">
				<form:input path="tel" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮编：</label>
			<div class="controls">
				<form:input path="zipCode" htmlEscape="false" maxlength="30" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">城市：</label>
			<div class="controls">
				<form:input path="city" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">详细地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预支付编号，支付方式不同，长度不同。其中微信支付有效期为2小时：</label>
			<div class="controls">
				<form:input path="prepayId" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预支付编号生成的时间：</label>
			<div class="controls">
				<input name="prepayDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${wsOrder.prepayDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退款状态：</label>
			<div class="controls">
				<form:input path="returnState" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退款编号：</label>
			<div class="controls">
				<form:input path="returnId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退款金额：</label>
			<div class="controls">
				<form:input path="returnMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">订单子表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>商品sku编号</th>
								<th>数量</th>
								<th>商品单价</th>
								<th>商品实际单价</th>
								<th>实际支付总金额</th>
								<th>分销金额</th>
								<th>备注信息</th>
								<shiro:hasPermission name="order:wsOrder:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="wsOrderItemList">
						</tbody>
						<shiro:hasPermission name="order:wsOrder:edit"><tfoot>
							<tr><td colspan="9"><a href="javascript:" onclick="addRow('#wsOrderItemList', wsOrderItemRowIdx, wsOrderItemTpl);wsOrderItemRowIdx = wsOrderItemRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="wsOrderItemTpl">//<!--
						<tr id="wsOrderItemList{{idx}}">
							<td class="hide">
								<input id="wsOrderItemList{{idx}}_id" name="wsOrderItemList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="wsOrderItemList{{idx}}_delFlag" name="wsOrderItemList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="wsOrderItemList{{idx}}_skuId" name="wsOrderItemList[{{idx}}].skuId" type="text" value="{{row.skuId}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="wsOrderItemList{{idx}}_quantity" name="wsOrderItemList[{{idx}}].quantity" type="text" value="{{row.quantity}}" maxlength="11" class="input-small "/>
							</td>
							<td>
								<input id="wsOrderItemList{{idx}}_unitPrice" name="wsOrderItemList[{{idx}}].unitPrice" type="text" value="{{row.unitPrice}}" class="input-small "/>
							</td>
							<td>
								<input id="wsOrderItemList{{idx}}_reallyUnitPrice" name="wsOrderItemList[{{idx}}].reallyUnitPrice" type="text" value="{{row.reallyUnitPrice}}" class="input-small "/>
							</td>
							<td>
								<input id="wsOrderItemList{{idx}}_reallyPrice" name="wsOrderItemList[{{idx}}].reallyPrice" type="text" value="{{row.reallyPrice}}" class="input-small "/>
							</td>
							<td>
								<input id="wsOrderItemList{{idx}}_rewardMoney" name="wsOrderItemList[{{idx}}].rewardMoney" type="text" value="{{row.rewardMoney}}" class="input-small "/>
							</td>
							<td>
								<textarea id="wsOrderItemList{{idx}}_remarks" name="wsOrderItemList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<shiro:hasPermission name="order:wsOrder:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#wsOrderItemList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var wsOrderItemRowIdx = 0, wsOrderItemTpl = $("#wsOrderItemTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(wsOrder.wsOrderItemList)};
							for (var i=0; i<data.length; i++){
								addRow('#wsOrderItemList', wsOrderItemRowIdx, wsOrderItemTpl, data[i]);
								wsOrderItemRowIdx = wsOrderItemRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="order:wsOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>