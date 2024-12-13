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
<h1 class="text-2xl">
    Dept Manager - ${dept.dname}
    <a href="/depts" class="float-right mr-5">Go list</a>
</h1>
<form action="/depts/${dept.id}/save" method="post" class="flex flex-col space-y-3 mt-5 border p-5">
    <label for="pid">상위부서:
        <select id="pid" name="pid" class="border">
            <c:forEach var="pdept" items="${depts}">
                <option value="${pdept.id}"
                        <c:if test="${pdept.id == dept.pid}">selected</c:if>>
                        ${'&nbsp;'.repeat(pdept.depth * 2)} ${pdept.dname}
                </option>
            </c:forEach>
        </select>
    </label>
    <label for="dname">부서명:
        <input type="text" id="dname" name="dname" value="${dept.dname}"
               class="border p-2 rounded-md">
    </label>
    <label for="pid">부서장:
        <select name="captain" id="captain" class="border w-32">
            <option value="">부서장</option>
            <c:forEach var="emp" items="${emps}">
                <option value="${emp.id}"
                        <c:if test="${emp.id == dept.captain}">selected</c:if>>
                        ${emp.ename}
                </option>
            </c:forEach>
        </select>
    </label>
    <div class="flex justify-around gap-5">
        <a href="/depts/${dept.id}/remove"
           class="hover:text-blue-800 border rounded-md px-2 bg-red-500 hover:bg-red-300 text-white w-full text-center ">삭제</a>
        <button type="reset"
                class="hover:text-blue-800 border rounded-md px-2 bg-slate-300 hover:bg-slate-200 w-full">
            변경내용 초기화
        </button>
        <button type="submit"
                class="hover:text-blue-800 border rounded-md px-2 bg-blue-300 text-white hover:bg-skyblue-300 w-full">
            저장
        </button>
    </div>

</form>
</body>
</html>
