<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.skuRewardMoney,.skuReallyPrice,.skuPrice,.skuSurplusQuantity{width:14%;}
		.skuName input,.skuRewardMoney input,.skuReallyPrice input,.skuPrice input,.skuSurplusQuantity input{width: 90%;}
	</style>
	<script src="${ctxStatic}/jquery.form.min.js"></script>
	<script src="${ctxStatic}/art-template-master/lib/template-web.js"></script>
	<script type="text/template" id="wsProdAttributelistAVBasic">
		{{if list.length > 0}}
		<div class="control-group sku_attr">
			<label class="control-label">基本属性：</label>
			<div class="controls">
				{{each list as attr index0}}
				<li>
					<label for="">{{attr.attrName}}</label>
					{{if attr.wsProdAttrivalueList}}
					{{each attr.wsProdAttrivalueList as attrVal index1}}
					{{if attr.inputType =='1'}}
					<label for="wsProdAttrivalue{{index1}}">{{attrVal.attrvalueValue}}</label>
					<input id="wsProdAttrivalue{{index1}}" type="radio" name="prodSkuAttrList00"  value="{{attrVal.id}}" data-attrbuteValue="{{attrVal.id}}" data-attrbuteValueName="{{attrVal.attrvalueValue}}" data-attrbuteId="{{attr.id}}" data-attrbuteName="{{attr.attrName}}">
					{{/if}}
					{{if attr.inputType =='2'}}
					<label for="wsProdAttrivalue{{index1}}">{{attrVal.attrvalueValue}}</label>
					<input id="wsProdAttrivalue{{index1}}" type="checkbox" name="prodSkuAttrList00" value="{{attrVal.id}}" data-attrbuteValue="{{attrVal.id}}" data-attrbuteValueName="{{attrVal.attrvalueValue}}" data-attrbuteId="{{attr.id}}" data-attrbuteName="{{attr.attrName}}">
					{{/if}}
					{{/each}}
					{{/if}}
					{{if attr.inputType =='3'}}
					<input id="wsProdAttrivalue{{index0}}" type="text" name="prodSkuAttrList00" placeholder="在ws_prod_sku_attr表中初始化attrbute_value_name" data11-attrbuteValue="" data-attrbuteValueName="this.value" data-attrbuteId="{{attr.id}}" data-attrbuteName="{{attr.attrName}}">
					{{/if}}
				</li>
				{{/each}}
			</div>
		</div>
		{{/if}}
	</script>
	<script type="text/template" id="wsProdAttributelistAVSKU">
		{{if list.length > 0}}
		<div class="control-group sku_attr">
			<label class="control-label">销售属性：</label>
			<div class="controls">
				{{each list as attr index0}}
				<li>
					<label for="">{{attr.attrName}}</label>
					{{if attr.wsProdAttrivalueList}}
					{{each attr.wsProdAttrivalueList as attrVal index1}}
					{{if attr.inputType =='1'}}
					<label for="wsProdAttrivalue{{index1}}">{{attrVal.attrvalueValue}}</label>
					<input id="wsProdAttrivalue{{index1}}" type="radio" name="prodSkuAttrList00" onchange="skuAttrChange();"  value="{{attrVal.id}}" data-attrbuteValue="{{attrVal.id}}" data-attrbuteValueName="{{attrVal.attrvalueValue}}" data-attrbuteId="{{attr.id}}" data-attrbuteName="{{attr.attrName}}">
					{{/if}}
					{{if attr.inputType =='2'}}
					<label for="wsProdAttrivalue{{index1}}">{{attrVal.attrvalueValue}}</label>
					<input id="wsProdAttrivalue{{index1}}" type="checkbox" name="prodSkuAttrList00" onchange="skuAttrChange();"    value="{{attrVal.id}}" data-attrbuteValue="{{attrVal.id}}" data-attrbuteValueName="{{attrVal.attrvalueValue}}" data-attrbuteId="{{attr.id}}" data-attrbuteName="{{attr.attrName}}">
					{{/if}}
					{{/each}}
					{{/if}}
					{{if attr.inputType =='3'}}
					<input id="wsProdAttrivalue{{index0}}" type="text" name="prodSkuAttrList00" oninput="skuAttrChange();" placeholder="在ws_prod_sku_attr表中初始化attrbute_value_name" data11-attrbuteValue="" data-attrbuteValueName="this.value" data-attrbuteId="{{attr.id}}" data-attrbuteName="{{attr.attrName}}">
					{{/if}}
				</li>
				{{/each}}
			</div>
		</div>
		{{/if}}
	</script>
	<script type="text/template" id="skuList">
		<table>
			<thead>
			<tr>
				<th>SKU名称</th>
				<th>库存</th>
				<th>价格</th>
				<th>实际价格</th>
				<th>分销金额</th>
			</th>
			</thead>
			<tbody>
				{{each skuList as sku index}}
						<tr>
							<td class="skuName">
								<input name="prodSkuListNew[{{index}}].skuName" readonly="readonly" type="text" value="{{each sku as skuAttr}}{{skuAttr.attrbuteName}}:{{skuAttr.attrbuteValueName}} &nbsp;{{/each}}">
								<input name="prodSkuListNew[{{index}}].attributeValues" type="hidden" value="{{each sku as skuAttr}}{{skuAttr.attrbuteId}},{{/each}}">
								<input name="prodSkuListNew[{{index}}].attrivalueValues" type="hidden" value="{{each sku as skuAttr}}{{if skuAttr.attrbuteValue}}{{skuAttr.attrbuteValue}},{{/if}}{{/each}}">
								<input name="prodSkuListNew[{{index}}].product.id" type="hidden" value="${wsProduct.id}">
							</td>
							<td class="skuSurplusQuantity">
								<input name="prodSkuListNew[{{index}}].surplusQuantity" type="number" value="100">
							</td>
							<td class="skuPrice">
								<input name="prodSkuListNew[{{index}}].price" type="number" value="100">
							</td>
							<td class="skuReallyPrice">
								<input name="prodSkuListNew[{{index}}].reallyPrice" type="number" value="100">
							</td>
							<td class="skuRewardMoney">
								<input name="prodSkuListNew[{{index}}].rewardMoney" type="number" value="100">
							</td>
						</tr>
				{{/each}}
			</tbody>
		</table>
	</script>
	<script>
        //用前端去写这些复杂逻辑，提交表单前做处理
        var wsProdAttributelistAV=${fns:toJson(wsProdAttributelistAV)};//当前商品类别的所有属性列表
        var wsProdAttributelistAVBasic=[],//基本属性
            wsProdAttributelistAVSKU=[];//销售属性
        for (var i = 0; i < wsProdAttributelistAV.length; i++) {
            if(wsProdAttributelistAV[i].attrType=='1'){
                wsProdAttributelistAVBasic.push(wsProdAttributelistAV[i]);
            }else if(wsProdAttributelistAV[i].attrType=='2'){
                wsProdAttributelistAVSKU.push(wsProdAttributelistAV[i]);
            }
        }
        var wsProdAttributelistAVSKUHtml = template('wsProdAttributelistAVSKU',{list:wsProdAttributelistAVSKU});
		var wsProdAttributelistAVBasicHtml = template('wsProdAttributelistAVBasic',{list:wsProdAttributelistAVBasic});//skuIndex用于记录销售属性索引指针


        $(function () {
            $('#prodSkuAttrList').html(wsProdAttributelistAVSKUHtml+wsProdAttributelistAVBasicHtml);
            var prodSkuAttrList=${fns:toJson(wsProduct.prodSkuAttrList)};//当前商品所有属性列表,稍后处理
            setTimeout(function () {//初始化商品所拥有的属性值
                for (var i = 0; i < prodSkuAttrList.length; i++) {
                    $('input[name="prodSkuAttrList00"],input[type="text"][name="prodSkuAttrList00"]').each(function () {
                        var that=$(this);
                        if(that.attr('type')=='text'){
                            if(prodSkuAttrList[i].attrbuteId==that.attr('data-attrbuteId')){
                                that.val(prodSkuAttrList[i].attrbuteValueName);
                            }
						}else if(that.attr('type')=='checkbox'||that.attr('type')=='radio'){
                            if(prodSkuAttrList[i].attrbuteValue==that.attr('data-attrbuteValue')){
                                that.prop('checked',true);
							}
						}

                    });
                }
            });
        });
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					// loading('正在提交，请稍等...');
					var data=processData();
                    form.submit();//cny_note
                    <%--$("#inputForm").ajaxSubmit({--%>
                        <%--url: "${ctx}/prod/wsProduct/save",--%>
                        <%--type: "post",--%>
                        <%--enctype: 'multipart/form-data',--%>
                        <%--dataType:'json',--%>
                        <%--data:data,--%>
                        <%--beforeSubmit:function(data){--%>

                        <%--},--%>
                        <%--success: function (data)--%>
                        <%--{--%>
                            <%--window.location.href='${ctx}/prod/wsProduct';--%>
                            <%--//var msg = eval(data);--%>
                            <%--// alert(data.errMsg);--%>
                            <%--// window.location.reload();--%>
                        <%--},--%>
                        <%--error: function (data)--%>
                        <%--{--%>
                            <%--//var msg = eval(data);--%>
                            <%--// alert("出错");//msg.errCode--%>
                        <%--}--%>
                    <%--})--%>
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
        /**
		 * 提交表单前进行商品属性值收集
         * @returns {{}}
         */
        function processData() {
            // var data={};
			var i=0;
			var prodSkuAttrListInputArr=[];
            $('input[name="prodSkuAttrList00"]:checked,input[type="text"][name="prodSkuAttrList00"]').each(function () {//单选和复选、文本框
                var attrbuteValue=$(this).attr('data-attrbuteValue');
                debugger;
                var attrbuteValueName;
                if($(this).attr('type')=='text'){//文本类型
                    var attrbuteValueName=$(this).val();
                }else{//单选多选
                    var attrbuteValueName=$(this).attr('data-attrbuteValueName');
                }
                var attrbuteId=$(this).attr('data-attrbuteId');
                var attrbuteName=$(this).attr('data-attrbuteName');

               /* data['prodSkuAttrList['+i+'].attrbuteValue']=attrbuteValue;
                data['prodSkuAttrList['+i+'].attrbuteValueName']=attrbuteValueName;
                data['prodSkuAttrList['+i+'].attrbuteId']=attrbuteId;
                data['prodSkuAttrList['+i+'].attrbuteName']=attrbuteName;
                data['prodSkuAttrList['+i+'].prod.id']='${wsProduct.id}';*/
                prodSkuAttrListInputArr.push('<input type="hidden" name="prodSkuAttrListNew['+i+'].attrbuteValue" value="'+attrbuteValue+'"/>');
                prodSkuAttrListInputArr.push('<input type="hidden" name="prodSkuAttrListNew['+i+'].attrbuteValueName" value="'+attrbuteValueName+'"/>');
                prodSkuAttrListInputArr.push('<input type="hidden" name="prodSkuAttrListNew['+i+'].attrbuteId" value="'+attrbuteId+'"/>');
                prodSkuAttrListInputArr.push('<input type="hidden" name="prodSkuAttrListNew['+i+'].prod.id" value="${wsProduct.id}"/>');
				i++;
            });
            var prodSkuAttrListInput = prodSkuAttrListInputArr.join('');
            $('#prodSkuAttrListInput').html(prodSkuAttrListInput);
            // return data;
        }

        function skuAttrChange(obj){
            var data={};
            $('.sku_attr input[name="prodSkuAttrList00"]:checked,.sku_attr input[type="text"][name="prodSkuAttrList00"]').each(function () {//单选和复选、文本框
				if(!$(this).val()){//文本框时，如果值为空则不加入计算
				    return false;
				}
                var attrbuteValue=$(this).attr('data-attrbuteValue');
                var attrbuteValueName;
                if($(this).attr('type')=='text'){//文本类型
                    var attrbuteValueName=$(this).val();
                }else{//单选多选
                    var attrbuteValueName=$(this).attr('data-attrbuteValueName');
                }
                var attrbuteId=$(this).attr('data-attrbuteId');
                var attrbuteName=$(this).attr('data-attrbuteName');
                if(!data[attrbuteId]){
                    data[attrbuteId]=[];
				}
                data[attrbuteId].push({attrbuteValue:attrbuteValue,attrbuteValueName:attrbuteValueName,attrbuteId:attrbuteId,attrbuteName:attrbuteName});
            });
            var dataAttr=[];
            var skuObj={};
            for(var attr in data){
                var attr0=[];
                for (var i = 0; i < data[attr].length; i++) {
                    attr0.push(data[attr][i]);
                }
                dataAttr.push(attr0);
			}
            var skuList=combine(dataAttr);//组合算法
			//再做下筛选，是要所有属性都有选值才生产sku
            skuList=skuList.filter(function (item) {//当选择商品所有销售属性没有都有值的情况要过滤掉
                return item.length==wsProdAttributelistAVSKU.length;
            })
            var skuListHtml=template('skuList',{skuList:skuList});
            $('.skuList').html(skuListHtml);
		}
		/**
		 * 组合算法
		 **/
        function combine(arr) {
            var r = [];
            (function f(t, a, n) {
                if (n == 0) return r.push(t);
                for (var i = 0; i < a[n-1].length; i++) {
                    f(t.concat(a[n-1][i]), a, n - 1);
                }
            })([], arr, arr.length);
            return r;
        }
       /* var arr = [
            ['1','2', '3'],
            ['a','b', 'c'],
            ['x','y','z'],
            ['e','f','g','h', 'i']];
        var res = combine(arr);*/
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/prod/wsProduct/">产品列表</a></li>
		<li class="active"><a href="${ctx}/prod/wsProduct/form?id=${wsProduct.id}">产品<shiro:hasPermission name="prod:wsProduct:edit">${not empty wsProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="prod:wsProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="wsProduct" action="${ctx}/prod/wsProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品分类：</label>
			<div class="controls">
				<sys:treeselect id="prodCategory" name="prodCategory.id" value="${wsProduct.prodCategory.id}" labelName="prodCategory.name" labelValue="${wsProduct.prodCategory.name}"
								title="产品分类" url="/prod/wsProdCategory/treeData" notAllowSelectRoot="false" notAllowSelectParent="true" cssClass="required"/>&nbsp;
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">品牌：</label>
			<div class="controls">
				<form:select path="brand.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${wsBrandList}" itemLabel="cnname" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<form:input path="pname" htmlEscape="false" maxlength="128" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="512" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否首页推荐：</label>
			<div class="controls">
				<form:radiobuttons path="isHomeRecommd" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品主图：</label>
			<div class="controls">
				<%--<form:hidden id="prodImage" path="prodImage" htmlEscape="false" maxlength="500" class="input-xlarge required"/>--%>
					<form:hidden  id="prodImage" path="prodImage" htmlEscape="false" maxlength="500" class="input-xlarge required" cssStyle="width: 0px;margin:0;padding:0;border:none;"/>
					<sys:ckfinder input="prodImage" type="images" uploadPath="/prod/wsProduct" selectMultiple="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品副图：</label>
			<div class="controls">
				<form:hidden id="prodImages" path="prodImages" htmlEscape="false" maxlength="2000" class="input-xlarge"/>
				<sys:ckfinder input="prodImages" type="images" uploadPath="/prod/wsProduct" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">价格范围：</label>
			<div class="controls">
				<form:input path="rangePrice" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">最低价格：</label>
			<div class="controls">
				<form:input path="minPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场价格：</label>
			<div class="controls">
				<form:input path="defaultPrice" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商城价格：</label>
			<div class="controls">
				<form:input path="defaultReallyPrice" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分销金额：</label>
			<div class="controls">
				<form:input path="defaultRewardMoney" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">默认库存数量：</label>
			<div class="controls">
				<form:input path="defaultNum" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品状态：</label>
			<div class="controls">
				<form:select path="onGoodState" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上架时间：</label>
			<div class="controls">
				<input name="onGoodTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${wsProduct.onGoodTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分销比例：</label>
			<div class="controls">
				<form:input path="rewardRate" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货号：</label>
			<div class="controls">
				<form:input path="pnumber" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">体积：</label>
			<div class="controls">
				<form:input path="volume" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重量：</label>
			<div class="controls">
				<form:input path="weight" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">快递模版：</label>
			<div class="controls">
				<form:select path="express.id" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否赠品：</label>
			<div class="controls">
				<form:radiobuttons path="isGift" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关键描述：</label>
			<div class="controls">
				<form:input path="keyword" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">仓库发货地：</label>
			<div class="controls">
				<form:input path="warehouse" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品描述：</label>
			<div class="controls">
				<%--<form:input path="prodContent" htmlEscape="false" maxlength="5000" class="input-xlarge "/>--%>
				<form:textarea id="prodContent" htmlEscape="false" path="prodContent" rows="4" maxlength="5000" class="input-xxlarge"/>
				<sys:ckeditor replace="prodContent" uploadPath="/prod/wsProduct" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否支持退货：</label>
			<div class="controls">
				<form:radiobuttons path="isReturn" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">退货有效期(天)：</label>
			<div class="controls">
				<form:input path="returnDate" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">已售数量：</label>
			<div class="controls">
				<form:input path="selNum" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">浏览次数：</label>
			<div class="controls">
				<form:input path="clickNum" htmlEscape="false" maxlength="11" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge"/>
			</div>
		</div>
		<div id="prodSkuAttrList"></div>
		<div id="prodSkuAttrListInput"></div>
		<div class="control-group">
			<label class="control-label">SKU列表：</label>
			<div class="controls skuList">
				<table>
					<thead>
					<tr>
						<th>SKU名称</th>
						<th>库存</th>
						<th>价格</th>
						<th>实际价格</th>
						<th>分销金额</th>
						</th>
					</thead>
					<tbody>
					<c:forEach items="${wsProduct.prodSkuList}" varStatus="status" var="sku">
					<tr>
						<td class="skuName">
							<input name="prodSkuListNew[${status.index}].skuName" readonly="readonly" type="text" value="${sku.skuName}">
							<input name="prodSkuListNew[${status.index}].attributeValues" type="hidden" value="${sku.attributeValues}">
							<input name="prodSkuListNew[${status.index}].attrivalueValues" type="hidden" value="${sku.attrivalueValues}">
							<input name="prodSkuListNew[${status.index}].product.id" type="hidden" value="${sku.product.id}">
						</td>
						<td class="skuSurplusQuantity">
							<input name="prodSkuListNew[${status.index}].surplusQuantity" type="number" value="${sku.surplusQuantity}">
						</td>
						<td  class="skuPrice">
							<input name="prodSkuListNew[${status.index}].price" type="number" value="${sku.price}">
						</td>
						<td  class="skuReallyPrice">
							<input name="prodSkuListNew[${status.index}].reallyPrice" type="number" value="${sku.reallyPrice}">
						</td>
						<td  class="skuRewardMoney">
							<input name="prodSkuListNew[${status.index}].rewardMoney" type="number" value="${sku.rewardMoney}">
						</td>
					</tr>
					</c:forEach>
					</tbody>
				</table>

			</div>
		</div>

		<div class="form-actions" id="form_btns">
			<shiro:hasPermission name="prod:wsProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>

<script>
	$(function () {
        $('#btnSubmit').click(function () {


        });
    })
</script>
</html>