<%@ page import="com.hana4.library.dto.BookDTO" %>
<%@ page import="java.util.List" %>

<%--
  User: min
  Date: 24. 12. 7.
  Time: 오후 8:58
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>보유 도서 목록</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .borrow-link {
            color: blue;
            text-decoration: underline;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h2 style="text-align: center;">보유 도서 목록</h2>
<table>
    <thead>
    <tr>
        <th>도서명</th>
        <th>저자</th>
        <th>출판사</th>
        <th>ISBN</th>
        <th>대출 가능 여부</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>
                <a href="${pageContext.request.contextPath}/book/read?bno=${book.bno}" class="borrow-link">
                        ${book.title}
                </a>
            </td>
            <td>${book.author}</td>
            <td>${book.publisher}</td>
            <td>${book.isbn}</td>
            <td>${book.availability == 1 ? 'O' : 'X'}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
