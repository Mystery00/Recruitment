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
<body class="mdui-theme-primary-indigo mdui-theme-accent-pink">
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
        <li class="mdui-list-item mdui-ripple">
            <div class="mdui-list-item-content mdui-container">
                <div class="mdui-row mdui-row-gapless">
                    <div class="mdui-col-xs-6">
                        <div class="mdui-list-item-title">
                            <span class="mdui-text-color-theme"><%=companyJob.jobName%>[<%=companyJob.location%>]</span>
                            <small><%=companyJob.publishTime%>
                            </small>
                        </div>
                        <div class="mdui-list-item-text mdui-list-item-one-line">
                            <span class="mdui-text-color-red"><strong><%=companyJob.money%></strong></span>
                            工作经验<%=companyJob.exp%>
                            / <%=companyJob.grade%>
                        </div>
                        <div class="mdui-list-item-text" style="height: 32px;">
                            <%
                                for (String s : companyJob.tag) {
                            %>
                            <div class="mdui-chip">
                                <span class="mdui-chip-title mdui-text-color-theme"><%=s%></span>
                            </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                    <div class="mdui-col-xs-6">
                        <div class="mdui-list-item-title">
                            <span class="mdui-text-color-theme"><%=companyJob.companyName%></span>
                        </div>
                        <div class="mdui-list-item-text mdui-list-item-one-line">
                            <%=companyJob.hy%> / <%=companyJob.jd%> / <%=companyJob.personNum%>
                        </div>
                        <div class="mdui-list-item-text"
                             style="height: 32px;line-height: 32px">
                            "<%=companyJob.temptation%>"
                        </div>
                    </div>
                </div>
            </div>
            <div><img src="<%=companyJob.companyImgUrl%>" width="60"
                      height="60"/></div>
        </li>
        <li class="mdui-divider mdui-m-y-0"></li>
        <%
            }
        %>
    </ul>
</div>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</body>
</html>
