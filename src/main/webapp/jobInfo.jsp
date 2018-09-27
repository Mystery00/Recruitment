<%@ page import="kgc.laki.recruitment.model.JobInfo" %>
<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %>
<%@ page import="kgc.laki.recruitment.utils.exception.KGCException" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%
        KGCException kgcException = SessionUtil.INSTANCE.getException(request);
        if (kgcException != null) {
    %>
    <script>
        alert('数据可能出错啦~\n<%=kgcException.getMsg()%>');
    </script>
    <%
            SessionUtil.INSTANCE.removeException(request);
        }
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
        <span class="mdui-typo-title">包就业招聘网</span>
        <div class="mdui-toolbar-spacer"></div>
        <div class="mdui-textfield mdui-textfield-expandable mdui-float-right">
            <form class="mdui-valign" action="doSearch" method="post">
                <i class="mdui-icon material-icons mdui-textfield-icon mdui-valign">search</i>
                <input style="margin-top:8px; border-bottom-color: white;box-shadow: rgba(0, 0, 0, 0.87) 0 0 0 0;"
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
    <div class="mdui-card mdui-row">
        <div class="mdui-typo-display-2 mdui-card-header mdui-col-xs-8"><%=jobInfo.jobName%>
        </div>
        <div class="mdui-card-content mdui-col-xs-8">
            <div class="mdui-typo-subheading">
                <p><span
                        class="mdui-text-color-red"><%=jobInfo.salary%></span><%=jobInfo.city%><%=jobInfo.exp%><%=jobInfo.grade%><%=jobInfo.gx%>
                </p>
            </div>
            <div>
                <%
                    for (String tag : jobInfo.tag) {
                %>
                <div class="mdui-chip"><span
                        class="mdui-chip-title mdui-text-color-theme"><%=tag%></span></div>
                <%
                    }
                %>
            </div>
            <div class="mdui-typo-subheading"><p><%=jobInfo.publishTIme%> 发布在拉勾网
            </p></div>
        </div>
        <div class="mdui-card-content  mdui-col-xs-4">
            <button class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-theme-accent">收藏</button>
        </div>
    </div>
    <br>
    <div class="mdui-row">
        <div class="mdui-card mdui-col-xs-8">
            <div class="mdui-card-content">
                <div class="mdui-typo-subheading">
                    <div class="mdui-typo-title">职位描述：</div>
                </div>
                <%=jobInfo.description%>
            </div>
            <div class="mdui-card-primary">
                <div class="mdui-card-primary-title">工作地点：</div>
                <div class="mdui-card-primary-subtitle"><%=jobInfo.workAddress%>
                </div>
            </div>
        </div>
        <div class="mdui-card mdui-col-xs-4">
            <div class="mdui-card-media" style="margin-top: 16px"><img
                    class="mdui-img-rounded mdui-center"
                    style="width: 100px"
                    src="<%=jobInfo.companyIconUrl%>" width="100"
                    height="100"></div>
            <div class="mdui-card-primary">
                <a href="getCompanyInfo?companyID=<%=jobInfo.companyID%>">
                    <div class="mdui-text-center mdui-typo-display-1"><%=jobInfo.companyName%>
                    </div>
                </a>
            </div>
            <div class="mdui-card-content">
                <div class="mdui-typo-subheading"><p>公司领域：<%=jobInfo.companyHY%>
                </p></div>
                <div class="mdui-typo-subheading"><p>融资阶段：<%=jobInfo.jd%>
                </p></div>
                <div class="mdui-typo-subheading"><p>公司规模：<%=jobInfo.gm%>
                </p></div>
                <div class="mdui-typo-subheading"><p>公司主页：<a class="mdui-typo-subheading"
                                                             href="<%=jobInfo.companyWebsite%>"><%=jobInfo.companyWebsite%>
                </a>
                </p></div>
            </div>
        </div>
    </div>
</div>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<link rel="stylesheet" href="css/search.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</body>
</html>
