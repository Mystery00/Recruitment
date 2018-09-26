<%@ page import="kgc.laki.recruitment.model.JobInfo" %>
<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %><%--
  Created by IntelliJ IDEA.
  User: myste
  Date: 2018/09/26
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        JobInfo jobInfo = SessionUtil.INSTANCE.getJobInfo(request);
    %>
    <title><%=jobInfo.jobName%> - <%=jobInfo.companyName%>
    </title>
</head>
<body class="mdui-theme-primary-indigo mdui-theme-accent-pink">
<!--顶部导航栏-->
<div class="mdui-container-fluid">
    <div class="mdui-toolbar mdui-color-theme">
        <a href="index" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">home</i></a>
        <span class="mdui-typo-title">拉勾 - 第三方版</span>
        <div class="mdui-toolbar-spacer"></div>
        <div class="mdui-textfield mdui-textfield-expandable mdui-float-right">
            <form class="mdui-valign" action="doSearch" method="post">
                <i class="mdui-icon material-icons mdui-textfield-icon mdui-valign">search</i>
                <input style="margin-top:8px; border-bottom-color: white;!important; border-bottom-width: 2px"
                       class="mdui-textfield-input mdui-text-color-white my-textfield-input"
                       type="text" placeholder="Search" name="query"/>
                <i class="mdui-icon material-icons mdui-textfield-close mdui-valign">close</i>
            </form>
        </div>
        <a href="javascript:;" class="mdui-ripple">登录</a>
        <a href="javascript:;" class="mdui-ripple">注册</a>
    </div>
</div>
<div class="mdui-container" style="margin-top: 20px">
    <div class="mdui-card">
        <div class="mdui-typo-display-2 mdui-card-header"><%=jobInfo.jobName%>
        </div>
        <div class="mdui-card-content">
            <div class="mdui-typo-subheading">
                <p><span
                        class="mdui-text-color-red"><%=jobInfo.salary%></span><%=jobInfo.city%><%=jobInfo.exp%><%=jobInfo.grade%><%=jobInfo.gx%>
                </p>
            </div>
            <div>
                <%
                    for (String tag : jobInfo.tag) {
                %>
                <div class="mdui-chip"><span class="mdui-chip-title"><%=tag%></span></div>
                <%
                    }
                %>
            </div>
            <div class="mdui-typo-subheading"><p><%=jobInfo.publishTIme%>
            </p></div>
        </div>
    </div>
</div>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<link rel="stylesheet" href="css/search.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</body>
</html>
