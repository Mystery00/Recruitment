<%@ page import="kgc.laki.recruitment.utils.exception.KGCException" %>
<%@ page import="kgc.laki.recruitment.utils.SessionUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    KGCException kgcException;
%>
<html>
<head>
    <title>数据出错了~</title>
    <%
        kgcException = SessionUtil.INSTANCE.getException(request);
        if (kgcException == null) {
            response.sendRedirect("index");
            SessionUtil.INSTANCE.removeException(request);
            return;
        }
    %>
    <script>
        alert('<%=kgcException.getMsg()%>');
        window.location.href = 'index';
    </script>
</head>
<body>
</body>
</html>
