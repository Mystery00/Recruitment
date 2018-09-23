<%@ page import="java.util.List" %>
<%@ page import="kgc.laki.recruitment.model.response.SearchChoose" %>
<%@ page import="kgc.laki.recruitment.model.CompanyJob" %>
<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    SearchChoose searchChoose;
    List<CompanyJob> companyJobList;
%>
<html>
<head>
    <title>搜索</title>
</head>
<body class="mdui-theme-primary-teal mdui-theme-accent-pink">
<%
    searchChoose = SessionUtil.INSTANCE.getSearchChoose(request);
    companyJobList = SessionUtil.INSTANCE.getCompanyJob(request);
%>
<!--顶部导航栏-->
<div class="mdui-container-fluid">
    <div class="mdui-toolbar mdui-color-theme">
        <span class="mdui-typo-title">拉勾-第三方版</span>
        <div class="mdui-toolbar-spacer"></div>
        <a href="javascript:;" class="mdui-btn mdui-ripple">登录</a>
        <a href="javascript:;" class="mdui-btn mdui-ripple">注册</a>
    </div>
</div>
<div class="mdui-container">
    <ul class="mdui-list">
        <%
            for (CompanyJob companyJob : companyJobList) {
        %>
        <li class="mdui-list-item mdui-ripple"><%=companyJob.jobName%>
        </li>
        <li class="mdui-divider"></li>
        <%
            }
        %>
    </ul>
</div>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</body>
</html>
