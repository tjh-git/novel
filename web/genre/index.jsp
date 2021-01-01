<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/12/29
  Time: 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>addgenre</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-index.css">
</head>
<body>
    <form action="${pageContext.request.contextPath}/addGenreServlet" method="post">
        <div style="margin-left: 30% ;margin-top: 10%;">
            <label>分类 :</label>
            <input name="genre" type="text" class="form-control" placeholder="Text input" style="width: 200px">
            <label>排序 :</label>
            <input name="sort" type="text" class="form-control" placeholder="Text input" style="width: 200px">

            <button type="submit" class="btn btn-primary btn-lg active" style="margin: 20px">提交</button>
            <button type="button" class="btn btn-default btn-lg active">重置</button>
        </div>
    </form>

</body>
</html>
