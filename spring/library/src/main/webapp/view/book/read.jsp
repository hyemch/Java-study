<%--
  User: min
  Date: 24. 12. 7.
  Time: 오후 9:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>도서 상세보기</title>
</head>
<body>
<h2>도서 상세 보기</h2>
<table border="1">
    <tr>
        <th>도서명</th>
        <td>${book.title}</td>
    </tr>
    <tr>
        <th>저자</th>
        <td>${book.author}</td>
    </tr>
    <tr>
        <th>출판사</th>
        <td>${book.publisher}</td>
    </tr>
    <tr>
        <th>ISBN</th>
        <td>${book.isbn}</td>
    </tr>
    <tr>
        <th>대출 가능 여부</th>
        <td>${book.availability == 1 ? '가능' : '불가능'}</td>
    </tr>
</table>

<c:if test="${book.availability == 1}">
    <form action="${pageContext.request.contextPath}/book/borrow" method="post">
        <input type="hidden" name="bno" value="${book.bno}" />
        <label for="borrowerId">대출 신청자 ID:</label>
        <input type="text" id="borrowerId" name="borrowerId" required />
        <button type="submit">대출 신청</button>
    </form>
</c:if>

<c:if test="${book.availability != 1}">
    <p style="color: red;">대출이 불가능한 도서입니다.</p>
</c:if>

<a href="${pageContext.request.contextPath}/book/list">목록으로 돌아가기</a>
</body>
</html>
