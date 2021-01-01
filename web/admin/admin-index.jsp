<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>admin</title>

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
</head>
<body>
<label style="font-size: 24px;margin-left: 45%;">
    管理员界面
</label>
<div>


    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">作者管理</a></li>
        <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">读者管理</a></li>
        <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">小说管理</a></li>
        <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">分类管理</a></li>
        <li role="presentation"><a href="#genre" aria-controls="genre" role="tab" data-toggle="tab">增加分类</a></li>
    </ul>
    <div class="out">
        <!-- Tab panes -->
        <div class="tab-content">


            <%-- 这是作者管理页面--%>
            <div role="tabpanel" class="tab-pane active" id="home">
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

            <%-- 这是读者管理页面--%>
            <div role="tabpanel" class="tab-pane" id="profile">
                <a href="${pageContext.request.contextPath}/readerServlet">跳转到读者管理</a>
            </div>

            <%-- 这是小说管理页面--%>
            <div role="tabpanel" class="tab-pane" id="messages">
                <a href="${pageContext.request.contextPath}/novel/novel-manage.jsp">跳转小说管理</a>
            </div>

            <%-- 这是分类管理页面--%>
            <div role="tabpanel" class="tab-pane" id="settings">
                <a href="${pageContext.request.contextPath}/genreServlet">跳转分类管理</a>
            </div>

            <%-- 这是增加分类页面--%>
            <div role="tabpanel" class="tab-pane" id="genre">
                <a href="${pageContext.request.contextPath}/genre/index.jsp">跳转增加分类</a>
            </div>
        </div>
    </div>


</div>
</body>
</html>
