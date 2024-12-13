<%--
  User: min
  Date: 24. 11. 27.
  Time: 오전 10:18
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <%@ include file="../head.jsp" %>
</head>
<body class="m-5">
<h1 class="text-2xl">Dept Manager</h1>
<ul class="border p-5">
    <c:forEach var="dept" items="${depts}">
        <li class="pl-${dept.depth * 3}
                     mt-${4 - dept.depth *3}
                    <c:if test="${dept.isTop}">text-green-500
        font-bold</c:if>">
            <c:if test="${dept.pid != 0}">
                <span class="text-slate-400">📂 </span>
            </c:if>
            <a href="/depts/${dept.id}">
                    ${dept.dname}
            </a>
            <c:if test="${dept.captainName != null}">
                <small class="text-xs text-slate-400">
                    (${dept.captainName})
                </small>
            </c:if>
            <a href="${pageContext.request.contextPath}depts/0?pid=${dept.id}"
               class="float-right">추가</a>
        </li>
    </c:forEach>
</ul>
<a href="${pageContext.request.contextPath}depts/0" class="float-right">부서 추가</a>
</body>
</html>
