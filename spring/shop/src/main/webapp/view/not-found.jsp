<%--
  User: min
  Date: 24. 11. 26.
  Time: 오전 10:06
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>${data} Not Found</title>
</head>
<body>
<h1 class="text-2xl text-red-300">${data} Not Found</h1>
<div class="text-center">

    <c:if test="${message != null}">
        <div>${message}</div>
    </c:if>
    <div>
        <a href="${pageContext.request.contextPath}/">HOME</a>
    </div>
</div>
</body>
</html>
