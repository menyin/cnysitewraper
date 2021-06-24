<%@page contentType="text/html; charset=UTF-8" %>
<%@include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta name="decorator" content="cms_default_${site.theme}">
    <meta name="description" content="cms_default_${site.description}">
    <meta name="keywords" content="cms_default_${site.keywords}">
  <meta charset="UTF-8">
  <title>首页</title>
</head>
<body>
<div class="hero-unit" style="padding-bottom:35px;margin:10px 0;">
    <c:set var="article" value="${fnc:getArticle('ad2cda52e16b4a329f1db43154353857')}"/>

    <h1>${fns:abbr(article.title,28)}</h1><p></p>
    <p>${fns:abbr(fns:replaceHtml(article.articleData.content),280 )}</p>
    <p><a href="${article.url}" class="btn btn-primary btn-large">&nbsp;&nbsp;&nbsp;查看详情 »&nbsp;&nbsp;&nbsp;</a></p>
</div>
<div class="row">

    <c:forEach var="category" items="${fnc:getCategoryList(fnc:getCurrentSiteId(),'1',3,'')}">
    <div class="span4">
            <h4><small><a href="${ctx}/list-${category.id}${fns:getUrlSuffix()}" class="pull-right">更多&gt;&gt;</a></small>${category.name}</h4>
            <ul>
            <c:forEach var="articleItem" items="${fnc:getArticleList(fnc:getCurrentSiteId(), category.id, 8, '')}">
                <li><span class="pull-right"><fmt:formatDate value="${articleItem.createDate}" pattern="yyyy.MM.dd"/></span>
                    <a href="${articleItem.url}" style="color:green">${articleItem.title}</a>
                </li>
            </c:forEach>
            </ul>
    </div>
    </c:forEach>
</div>
</body>
</html>