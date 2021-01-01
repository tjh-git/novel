<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Genre</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-index.css">


    <script>
        function deleteAuthor(id) {
            if (confirm("您确定要删除吗？")) {
                location.href = "${pageContext.request.contextPath}/delAuthorServlet?id=" + id;
            }
        }

        window.onload = function () {
            document.getElementById("delSelected").onclick = function () {
                if (confirm("您确定要删除吗？")) {
                    document.getElementById("form").submit();
                }
            }

            document.getElementById("firstCb").onclick = function () {
                var cbs = document.getElementsByName("aid");
                for (var i = 0; i < cbs.length; i++) {
                    cbs[i].checked = this.checked;
                }

            }
        }

    </script>

</head>
<body>

<div class="out">
    <label style="font-size: 25px">分类管理</label>
    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table class="table table-hover">
            <caption></caption>
            <thead>
            <tr>
<%--                <th><input type="checkbox" id="firstCb"></th>--%>
                <th>编号</th>
                <th>分类名称</th>
                <th>分类排序</th>
                <th>操作</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${genre}" var="genre" varStatus="s">
                <tr>
<%--                    <th><input type="checkbox" name="aid" value="${genre.id}"></th>--%>
                    <td>${s.count}</td>
                    <td>${genre.name}</td>
                    <td>${genre.sort}</td>
                    <td><a href="${pageContext.request.contextPath}/delGenreServlet?id=${genre.id}">删除</a>
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
                                    共${genreCount}条数据
                            </span>
            </ul>

        </nav>
    </div>
</div>

</body>
</html>
