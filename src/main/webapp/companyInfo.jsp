<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %>
<%@ page import="kgc.laki.recruitment.model.CompanyInfo" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%
        CompanyInfo companyInfo = SessionUtil.INSTANCE.getCompanyInfo(request);
    %>
    <title><%=companyInfo.companyName%>
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
        <div class="mdui-col-xs-2">
            <img src="<%=companyInfo.companyIconUrl%>" width="164" height="164">
        </div>
        <div class="mdui-col-xs-10">
            <div class="mdui-card-primary">
                <a href="<%=companyInfo.companyUrl%>">
                    <div class="mdui-card-primary-title"><%=companyInfo.companyName%>
                    </div>
                </a>
                <div class="mdui-card-primary-subtitle"><%=companyInfo.companyIntroduce%>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="mdui-row">
        <div class="mdui-card mdui-col-xs-8">
            <div class="mdui-card-content">
                <div class="mdui-typo-subheading">
                    <div class="mdui-typo-title">公司介绍：</div>
                </div>
                <div>
                    <%
                        String htmlString = "<p>" + companyInfo.introductionString.replaceAll("\n", "</p><p>") + "</p>";
                    %>
                    <%=htmlString%>
                </div>
                <div>
                    <%
                        for (String url : companyInfo.pictures) {
                    %>
                    <img src="<%=url%>" width="480" height="320">
                    <%
                        }
                    %>
                </div>
            </div>
            <div class="mdui-card-primary">
                <div class="mdui-card-primary-title">工作地点：</div>
                <div class="mdui-card-primary-subtitle"><%=companyInfo.location%>
                </div>
            </div>
        </div>
        <div class="mdui-card mdui-col-xs-4">
            <div class="mdui-card-primary">
                <div class="mdui-card-primary-title">公司基本信息</div>
            </div>
            <div class="mdui-card-content">
                <div class="mdui-typo-subheading"><p>公司领域：<%=companyInfo.hy%>
                </p></div>
                <div class="mdui-typo-subheading"><p>融资阶段：<%=companyInfo.jd%>
                </p></div>
                <div class="mdui-typo-subheading"><p>公司规模：<%=companyInfo.gm%>
                </p></div>
                <div class="mdui-typo-subheading"><p>城市：<%=companyInfo.city%>
                </p></div>
            </div>
            <div class="mdui-card-primary">
                <div class="mdui-card-primary-title">公司标签</div>
            </div>
            <div class="mdui-card-content">
                <%
                    for (String tag : companyInfo.labels) {
                %>
                <div class="mdui-chip"><span
                        class="mdui-chip-title mdui-text-color-theme"><%=tag%></span></div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<link rel="stylesheet" href="css/search.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</body>
</html>
