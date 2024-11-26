<%--
  User: min
  Date: 24. 11. 25.
  Time: 오후 3:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modify</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/modify/${cust.id}" method="POST">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name">
    <input type="submit" value="ADD">
</form>
</body>
</html>
