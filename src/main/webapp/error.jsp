<%@ page import="kgc.laki.recruitment.utils.exception.KGCException" %>
<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    KGCException kgcException;
%>
<html>
<head>
    <title>数据出错了~</title>
    <link rel="stylesheet" href="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/css/mdui.min.css">
    <script src="//cdnjs.loli.net/ajax/libs/mdui/0.4.1/js/mdui.min.js"></script>
    <%
        kgcException = SessionUtil.INSTANCE.getExceptionThenDestory(request);
        if (kgcException == null) {
            response.sendRedirect("index");
            return;
        }
    %>
    <script>
        alert('<%=kgcException.getMsg()%>');
        window.location.href = '/index';
    </script>
</head>
<body>
</body>
</html>
