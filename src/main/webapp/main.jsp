<%@ page import="java.util.List" %>
<%@ page import="kgc.laki.recruitment.model.HotSearch" %>
<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %>
<%@ page import="kgc.laki.recruitment.model.KeywordGroup" %>
<%@ page import="kgc.laki.recruitment.model.Keyword" %>
<%@ page import="kgc.laki.recruitment.model.KeywordCategory" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<HotSearch> hotSearchList;
    List<KeywordCategory> keywordCategoryList;
%>
<html>
<head>
    <title>拉勾网</title>
</head>
<body class="mdui-theme-primary-teal mdui-theme-accent-pink">
<%
    hotSearchList = SessionUtil.INSTANCE.getHotSearch(request);
    keywordCategoryList = SessionUtil.INSTANCE.getKeyWord(request);
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
    <div class="mdui-textfield">
        <form action="/doSearch" method="post">
            <i class="mdui-icon material-icons">search</i>
            <input class="mdui-textfield-input" type="text" placeholder="Search" name="query"/>
        </form>
    </div>
    <div>
        <%
            for (HotSearch hotSearch : hotSearchList) {
        %>
        <div class="mdui-chip"><a href="<%=hotSearch.href%>"><span
                class="mdui-chip-title mdui-text-color-teal"><%=hotSearch.value%></span></a>
        </div>
        <%
            }
        %>
    </div>
    <div class="mdui-typo">
        <hr/>
    </div>
    <div class="mdui-tab mdui-tab-centered" mdui-tab>
        <%
            for (KeywordCategory keywordCategory : keywordCategoryList) {
        %>
        <a href="#<%=keywordCategory.category%>"
           class="mdui-ripple"><%=keywordCategory.category%>
        </a>
        <%
            }
        %>
    </div>
    <div>
        <%
            for (KeywordCategory keywordCategory : keywordCategoryList) {
        %>
        <div id="<%=keywordCategory.category%>">
            <div class="mdui-panel mdui-panel-popout mdui-p-a-2"
                 mdui-panel>
                <%
                    for (KeywordGroup keywordGroup : keywordCategory.keywordGroupList) {
                %>
                <div class="mdui-panel-item">
                    <div class="mdui-panel-item-header">
                        <div class="mdui-panel-item-title"><%=keywordGroup.title%>
                        </div>
                        <i class="mdui-panel-item-arrow mdui-icon material-icons">keyboard_arrow_down</i>
                    </div>
                    <div class="mdui-panel-item-body">
                        <%
                            for (Keyword keyword : keywordGroup.keywordList) {
                        %>
                        <div class="mdui-chip"><a href="<%=keyword.href%>"><span
                                class="mdui-chip-title mdui-text-color-teal"><%=keyword.value%></span></a>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</html>
