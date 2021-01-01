<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/12/27
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="margin: 100px;  ">
        <form class="form-horizontal" action="${pageContext.request.contextPath}/addAuthorServlet" method="post">
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label" >作者昵称</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputEmail2" placeholder="作者昵称" name="authorName">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password" name="authorPassword">
                </div>
            </div>

            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="inputPassword4" placeholder="Email" name="authorEmail">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default" >Sign in</button>
                </div>
            </div>
        </form>
    </div>

</body>
</html>
