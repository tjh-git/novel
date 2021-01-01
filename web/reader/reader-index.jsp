<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/12/28
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reader</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-index.css">
</head>
<body>

<div class="out" >
    <label style="font-size: 25px">读者管理</label>
    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table class="table table-hover">
            <caption></caption>
            <thead>
            <tr>
<%--                <th><input type="checkbox" id="firstCb"></th>--%>
                <th>编号</th>
                <th>读者昵称</th>
                <th>密码</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${reader}" var="reader" varStatus="s">
                <tr>
<%--                    <th><input type="checkbox" name="aid" value="${author.id}"></th>--%>
                    <td>${s.count}</td>
                    <td>${reader.readerName}</td>
                    <td>${reader.readerPassword}</td>
                    <td>${reader.readerEmail}</td>
                    <td><a href="${pageContext.request.contextPath}/delReaderServlet?id=${reader.id}">删除</a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </form>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">

                </li>


                </li>
                <span style="font-size: 25px;margin-left: 5px">
                                    共${readerCount}条数据
                            </span>
            </ul>

        </nav>
    </div>
</div>

</body>
</html>
