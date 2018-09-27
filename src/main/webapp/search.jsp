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
        <a href="index" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">home</i></a>
        <span class="mdui-typo-title">拉勾 - 第三方版</span>
        <div class="mdui-toolbar-spacer"></div>
        <div class="mdui-textfield mdui-textfield-expandable mdui-textfield-expanded mdui-float-right">
            <form class="mdui-valign" action="doSearch" method="post">
                <i class="mdui-icon material-icons mdui-textfield-icon mdui-valign">search</i>
                <input style="margin-top:8px; border-bottom-color: white;box-shadow: rgba(0, 0, 0, 0.87) 0 0 0 0;"
                       class="mdui-textfield-input mdui-text-color-white"
                       type="text" placeholder="Search" name="query"
                       value="<%=searchChoose.searchBean.query%>"/>
                <i class="mdui-icon material-icons mdui-textfield-close mdui-valign">close</i>
            </form>
        </div>
        <a href="javascript:;" class="mdui-ripple">登录</a>
        <a href="javascript:;" class="mdui-ripple">注册</a>
    </div>
</div>
<div class="mdui-container" style="margin-top: 20px">
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
            <br>
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
            <br>
            <div>
                <span class="mdui-text-color-theme">学历要求：</span>
                <%
                    for (String xl : searchChoose.xl) {
                %>
                <a href="appendSearch?xl=<%=xl%>"><span
                        class="<%=searchChoose.searchBean.xl.contains(xl)?"onSelected selected":"onSelected"%>"
                        style="padding: 8px"><%=xl%></span></a>
                <%
                    }
                %>
            </div>
            <br>
            <div>
                <span class="mdui-text-color-theme">融资阶段：</span>
                <%
                    for (String jd : searchChoose.jd) {
                %>
                <a href="appendSearch?jd=<%=jd%>"><span
                        class="<%=searchChoose.searchBean.jd.contains(jd)?"onSelected selected":"onSelected"%>"
                        style="padding: 8px"><%=jd%></span></a>
                <%
                    }
                %>
            </div>
            <br>
            <div>
                <span class="mdui-text-color-theme">公司规模：</span>
                <%
                    for (String gm : searchChoose.gm) {
                %>
                <a href="appendSearch?gm=<%=gm%>"><span
                        class="<%=searchChoose.searchBean.gm.contains(gm)?"onSelected selected":"onSelected"%>"
                        style="padding: 8px"><%=gm%></span></a>
                <%
                    }
                %>
            </div>
            <br>
            <div>
                <span class="mdui-text-color-theme">行业领域：</span>
                <%
                    for (String hy : searchChoose.hy) {
                %>
                <a href="appendSearch?hy=<%=hy%>"><span
                        class="<%=searchChoose.searchBean.hy.contains(hy)?"onSelected selected":"onSelected"%>"
                        style="padding: 8px"><%=hy%></span></a>
                <%
                    }
                %>
            </div>
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
                            <span class="mdui-text-color-theme"><a
                                    href="getJobInfo?positionID=<%=companyJob.positionID%>"><%=companyJob.jobName%>[<%=companyJob.location%>]</a></span>
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
                            <span class="mdui-text-color-theme"><a
                                    href="getCompanyInfo?companyID=<%=companyJob.companyID%>"><%=companyJob.companyName%></a></span>
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
                      height="60" class="mdui-img-rounded"/></div>
        </li>
        <li class="mdui-divider mdui-m-y-0"></li>
        <%
            }
        %>
    </ul>
    <div class="mdui-btn-group">
        <a class="mdui-btn mdui-valign"
           href="<%=searchChoose.searchBean.getPage() == 1 ? "#!" : "appendSearch?page="+(searchChoose.searchBean.getPage()-1)%>"><i
                class="mdui-icon material-icons">chevron_left</i></a>
        <%
            for (int i = searchChoose.searchBean.getPage() - 4; i < searchChoose.searchBean.getPage(); i++) {
                if (i <= 0)
                    continue;
        %>
        <a class="mdui-btn mdui-valign" href="appendSearch?page=<%=i%>"><%=i%>
        </a>
        <%
            }
        %>
        <button type="button"
                class="mdui-btn mdui-btn-active mdui-valign"><%=searchChoose.searchBean.getPage()%>
        </button>
        <%
            for (int i = 1; i <= 4; i++) {
        %>
        <a class="mdui-btn mdui-valign"
           href="appendSearch?page=<%=searchChoose.searchBean.getPage() + i%>"><%=searchChoose.searchBean.getPage() + i%>
        </a>
        <%
            }
        %>
        <a class="mdui-btn mdui-valign"
           href="appendSearch?page=<%=searchChoose.searchBean.getPage()+1%>"><i
                class="mdui-icon material-icons">chevron_right</i></a>
    </div>
</div>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<link rel="stylesheet" href="css/search.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</body>
</html>
