<%@ page import="kgc.laki.recruitment.utils.exception.KGCException" %>
<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    KGCException kgcException;
%>
<html>
<head>
    <title>数据出错了~</title>
    <script>
        var dialog = document.getElementById("hint_dialog");
        dialog.addEventListener('closed.mdui.dialog', function () {
            window.location.href = 'index'
        });
        dialog.addEventListener('confirm.mdui.dialog', function () {
            window.location.href = 'index'
        });
    </script>
</head>
<body>
<%
    kgcException = SessionUtil.INSTANCE.getExceptionThenDestory(request);
    if (kgcException == null) {
        response.sendRedirect("index");
        return;
    }
%>
<div class="mdui-dialog" id="hint_dialog">
    <div class="mdui-dialog-title">出错啦~</div>
    <div class="mdui-dialog-content"><%=kgcException.getMsg()%>
    </div>
    <div class="mdui-dialog-actions">
        <button class="mdui-btn mdui-ripple">重新加载</button>
    </div>
</div>
</body>
<link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
<script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
</html>
