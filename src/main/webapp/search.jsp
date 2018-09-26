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
    <style>
        .onSelected:hover {
            background-color: #0d47a1;
            color: white;
        }

        .selected {
            background-color: #0d47a1;
            color: white;
        }
    </style>
</head>
<body class="mdui-theme-primary-indigo mdui-theme-accent-pink">
<%
    searchChoose = SessionUtil.INSTANCE.getSearchChoose(request);
    companyJobList = SessionUtil.INSTANCE.getCompanyJob(request);
%>
<!--顶部导航栏-->
<div class="mdui-container-fluid">
    <div class="mdui-toolbar mdui-color-theme">
        <a href="index" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">home</i></a>
        <span class="mdui-typo-title">拉勾 - 第三方版</span>
        <div class="mdui-toolbar-spacer"></div>
        <a href="javascript:;" class="mdui-ripple">登录</a>
        <a href="javascript:;" class="mdui-ripple">注册</a>
    </div>
</div>
<div class="mdui-container" style="margin-top: 20px">
    <div class="mdui-textfield">
        <form action="doSearch" method="post">
            <i class="mdui-icon material-icons">search</i>
            <input class="mdui-textfield-input" type="text" placeholder="Search" name="query"
                   value="<%=searchChoose.query%>"/>
        </form>
    </div>
    <div class="mdui-card">
        <div class="mdui-card-content">
            <div>
                <span class="mdui-text-color-theme">工作地点：</span>
                <%
                    for (String city : searchChoose.city) {
                %>
                <a href="appendSearch?city=<%=city%>"><span
                        class="<%=city.equals(searchChoose.searchBean.city)?"onSelected selected":"onSelected"%>"
                        style="padding: 8px"><%=city%></span></a>
                <%
                    }
                %>
            </div>
            <%
                if (searchChoose.isSchoolJob()) {
            %>
            <div>
                <span class="mdui-text-color-theme">工作性质：</span>
                <%
                    for (String x : searchChoose.gx) {
                %>
                <a href="appendSearch?gx=<%=x%>"><span
                        class="<%=x.equals(searchChoose.searchBean.gx)?"onSelected selected":"onSelected"%>"
                        style="padding: 8px"><%=x%></span></a>
                <%
                    }
                %>
            </div>
            <%
            } else {
            %>
            <div>
                <span class="mdui-text-color-theme">工作经验：</span>
                <%
                    for (String exp : searchChoose.gj) {
                %>
                <a href="appendSearch?gj=<%=exp%>"><span
                        class="<%=searchChoose.searchBean.gj.contains(exp)?"onSelected selected":"onSelected"%>"
                        style="padding: 8px"><%=exp%></span></a>
                <%
                    }
                %>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <div class="mdui-typo">
        <hr/>
    </div>
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
    <div class="mdui-btn-group">
        <button type="button" class="mdui-btn"><i class="mdui-icon material-icons">chevron_left</i>
        </button>
        <button type="button" class="mdui-btn mdui-btn-active">1</button>
        <button type="button" class="mdui-btn"><i class="mdui-icon material-icons">chevron_right</i>
        </button>
    </div>
</div>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</body>
</html>
