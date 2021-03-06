<%@ page import="java.util.List" %>
<%@ page import="kgc.laki.recruitment.model.HotSearch" %>
<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %>
<%@ page import="kgc.laki.recruitment.model.KeywordGroup" %>
<%@ page import="kgc.laki.recruitment.model.KeywordCategory" %>
<%@ page import="kgc.laki.recruitment.utils.exception.KGCException" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    List<HotSearch> hotSearchList;
    List<KeywordCategory> keywordCategoryList;
%>
<html>
<head>
    <title>拉勾网</title>
</head>
<body class="mdui-theme-primary-indigo mdui-theme-accent-pink">
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
    hotSearchList = SessionUtil.INSTANCE.getHotSearch(request);
    keywordCategoryList = SessionUtil.INSTANCE.getKeyWord(request);
%>
<!--顶部导航栏-->
<div class="mdui-container-fluid">
    <div class="mdui-toolbar mdui-color-theme">
        <a href="index" class="mdui-btn mdui-btn-icon"><i class="mdui-icon material-icons">home</i></a>
        <span class="mdui-typo-title">包就业招聘网</span>
        <div class="mdui-toolbar-spacer"></div>
        <a href="javascript:;" class="mdui-ripple">登录</a>
        <a href="javascript:;" class="mdui-ripple">注册</a>
    </div>
</div>
<div class="mdui-container">
    <div class="mdui-textfield">
        <form action="doSearch" method="post">
            <i class="mdui-icon material-icons">search</i>
            <input class="mdui-textfield-input" type="text" placeholder="Search" name="query"/>
        </form>
    </div>
    <div>
        <%
            for (HotSearch hotSearch : hotSearchList) {
        %>
        <div class="mdui-chip"><a href="doSearch?query=<%=hotSearch.value%>"><span
                class="mdui-chip-title mdui-text-color-theme"><%=hotSearch.value%></span></a>
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
            <div class="mdui-panel mdui-panel-popout mdui-p-a-2" mdui-panel>
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
                            for (String keyword : keywordGroup.keywordList) {
                        %>
                        <div class="mdui-chip"><a href="doSearch?query=<%=keyword%>"><span
                                class="mdui-chip-title mdui-text-color-theme"><%=keyword%></span></a>
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
