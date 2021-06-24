<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>
      <sitemesh:title default="欢迎来到cnysite">${site.title}</sitemesh:title>
  </title>
    <%@include file="/WEB-INF/views/modules/cms/front/include/head.jsp"%>
    <sitemesh:head/>
</head>
<body>
<div class="navbar navbar-fixed-top" style="position:static;margin-bottom:10px;">
    <div class="navbar-inner">
        <div class="container">
            <c:choose>
                <c:when test="${not empty site.logo}">
                    <a href="${ctx}/index-${site.id}${fns:getUrlSuffix()}"><img src="${site.logo}" alt="${site.title}"></a>
                </c:when>
                <c:otherwise>
                    <a class="brand" href="${ctx}/index-${site.id}${fns:getUrlSuffix()}">${site.title}</a>
                </c:otherwise>
            </c:choose>
            <div class="nav-collapse">
                <ul id="main_nav" class="nav nav-pills">
                    <li class="${not empty isIndex&&isIndex?'active':''}">
                        <a href="${ctx}/index-1.html"><span>返回主站</span></a>
                    </li>
                    <c:forEach var="categoryItem" items="${fnc:getMainNavList(site.id)}">
                        <c:set var="categoryItemIdStr" value=",${categoryItem.id}," />
                        <li class="<c:if test="${requestScope.category.id eq categoryItem.id or fn:indexOf(requestScope.category.parentIds,categoryItemIdStr) ge 1}">active</c:if>">
                            <a href="${categoryItem.url}" target=""><span>${categoryItem.name}</span></a>
                        </li>
                    </c:forEach>
                    <%--<li class=""><a href="/cnysite/f/list-202b8858053144239207699982627213.html" target=""><span>软件介绍</span></a></li>--%>

                    <li id="siteSwitch" class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="站点"><i class="icon-retweet"></i></a>
                        <ul class="dropdown-menu">
                            <c:forEach var="site" items="${fnc:getSiteList()}">
                                <li><a href="#" onclick="location='${ctx}/index-${site.id}${fns:getUrlSuffix()}'">${site.name}</a></li>
                            </c:forEach>

                        </ul>
                    </li>
                    <li id="themeSwitch" class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large"></i></a>
                        <ul class="dropdown-menu">
                            <c:forEach var="theme" items="${fns:getDictList('theme')}">
                                <li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${theme.value}?url='+location.href">${theme.label}</a></li>
                            </c:forEach>
                        </ul>
                        <!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
                    </li>
                </ul>
                <form class="navbar-form pull-right" action="${ctx}/search" method="get">
                    <input name="q" maxlength="20" style="width:65px;" placeholder="全站搜索..." value="" type="text">
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>
<div class="container">
    <div class="content">
        <sitemesh:body/>
    </div>
    <hr style="margin:20px 0 10px;">
    <footer>
        <div class="footer_nav"><a href="${ctx}/guestbook" target="_blank">公共留言</a> | <a href="${ctx}/search" target="_blank">全站搜索</a> | <a href="${ctx}/map-${site.id}${fns:getUrlSuffix()}" target="_blank">站点地图</a> | <a href="mailto:thinkgem@163.com">技术支持</a> | <a href="${pageContext.request.contextPath}${fns:getAdminPath()}" target="_blank">后台管理</a></div>
        <div class="pull-right">${fns:getDate('yyyy年MM月dd日 E')}</div><div class="copyright">${site.copyright}</div>
    </footer>
</div> <!-- /container -->
</body>
</html>
