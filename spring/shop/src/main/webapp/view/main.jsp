<%--
  User: min
  Date: 24. 11. 25.
  Time: 오전 11:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<html>
<%@ include file="./head.jsp" %>

<body>
<div class="container mx-auto">
    <h1 class="text-3xl text-green-800">Shop Main</h1>
    <h3 class="text-lg">${version}</h3>

    <c:set var="num" value="100000"/>
    <fmt:formatNumber value="${num}" var="numx" pattern="#,###"/>
    num: ${numx}
    <fmt:formatDate value="${createdate}" var="createdatex" pattern="yyyy-MM-dd HH:mm:ss"/>
    , createdate: ${createdatex}

    <ul class="border p-3 w-96">
        <c:forEach var="cust" items="${custs}" varStatus="status">
            <%--        <li>--%>
            <%--                ${status.count} - ${cust.id}. ${cust.name}--%>
            <%--            :: ${list.get(status.index)} - ${list[stauts.index]}--%>
            <%--        </li>--%>
            <li class='<c:if test="${status.first}">XXX</c:if> <c:if test="${status.last}">ZZZ</c:if>'>
                <a href="/modify/${cust.id}" class="hover:text-blue-800">${status.count} - ${cust.id}. ${cust.name}
                    <small class="text-xs text-gray-500">${cust.tel}</small>
                </a>
                    <%--                <a href="/remove/${cust.id}" class="hover:text-blue-700 float-right">Remove</a>--%>
                <a href="javascript:del(${cust.id})" class="hover:text-blue-700 float-right">Remove</a>
            </li>
        </c:forEach>
        <c:if test="${custs.size() == 0}">
            <p>There is no custs...</p>
        </c:if>
        <div><a href="/add" class="underline">ADD CUST</a></div>
        <script>
            function del(id) {
                if (confirm('Are u sure??')) {
                    window.location.href = '/remove/' + id;
                }
            }
        </script>
    </ul>
</div>

</body>
</html>
