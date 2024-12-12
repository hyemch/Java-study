<%--
  User: min
  Date: 24. 12. 7.
  Time: 오후 9:28
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>대출 결과</title>
</head>
<body>
<h2>도서 대출 결과</h2>
<table border="1">
    <tr>
        <th>도서명</th>
        <td>${book.title}</td>
    </tr>
    <tr>
        <th>신청자 ID</th>
        <td>${book.borrowerId}</td>
    </tr>
    <tr>
        <th>대출일</th>
        <td>${book.startDate}</td>
    </tr>
    <tr>
        <th>반납일</th>
        <td>${book.endDate}</td>
    </tr>
</table>

<a href="${pageContext.request.contextPath}/book/list">목록으로 돌아가기</a>
</body>
</html>
