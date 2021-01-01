<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/12/29
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>admin1</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin-index.css">
    <%--    跳转用的资源，单独使用要改目录--%>
    <%--    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">--%>
    <%--    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>--%>
    <%--    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>--%>


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

    <style type="text/css">
        * {
            margin: 0;
        }

        .out {
            height: 80%;
            width: 80%;
            /*background-color: black;*/
            margin-left: 100px;
        }

        .tab-content {
            margin-top: 30px;
        }
    </style>
</head>
<body>
<div class="out">

    <div style="float: left;">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet"
              method="post">
            <div class="form-group">
                <label for="exampleInputName2">作者昵称</label>
                <input type="text" class="form-control" id="exampleInputName2" name="authorName">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">作者邮箱</label>
                <input type="text" class="form-control" id="exampleInputEmail2" name="authorEmail">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;">
        <div class="btn-group" role="group" aria-label="...">
            <a style="margin-right: 20px;" class="btn btn-primary"
               href="${pageContext.request.contextPath}/author/addAuthor.jsp">添加</a>
            <button type="button" class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中
            </button>
        </div>
    </div>


    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table class="table table-hover">
            <caption></caption>
            <thead>
            <tr>
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>作者昵称</th>
                <th>密码</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pb.list}" var="author" varStatus="s">
                <tr>
                    <th><input type="checkbox" name="aid" value="${author.id}"></th>
                    <td>${s.count}</td>
                    <td>${author.authorName}</td>
                    <td>${author.authorPassword}</td>
                    <td>${author.authorEmail}</td>
                    <td><a href="${pageContext.request.contextPath}/delAuthorServlet?id=${author.id}">删除</a>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </form>

    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage == 1}">
                <li class="disabled">
                    </c:if>

                    <c:if test="${pb.currentPage != 1}">
                <li>
                    </c:if>

                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage -1}&rows=5"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach begin="1" end="${pb.totalPage}" var="i">

                    <c:if test="${pb.currentPage==i}">
                        <li class="active"><a
                                href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage!=i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=5">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${pb.currentPage == pb.totalCount}">
                <li class="disabled">
                    </c:if>

                    <c:if test="${pb.currentPage != pb.totalCount}">
                <li>
                    </c:if>

                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage + 1}&rows=5"
                       aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px">
                                    共${pb.totalCount}条数据，共${pb.totalPage}页
                 </span>
            </ul>

        </nav>
    </div>
</div>
</body>
</html>
