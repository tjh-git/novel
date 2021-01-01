<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="org.common.*" %>
<%@page import="org.model.*" %>
<%@page import="org.dal.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 删除评论 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>删除评论</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>
<%
    Comment comment = new Comment();
    int result = 0;
//    int cid = ;
    result = comment.delete(DataConverter.toInt(request.getParameter("id")));
    int novelId = DataConverter.toInt(request.getParameter("novelId"));
//    System.out.println(" 评论cid:  "+cid  +"   novelId:  "+novelId);
    if(result==1){
        out.println("<script>alert('评论删除成功'); location.href ='comment/comment-manage.jsp?novelId="+novelId+"'; </script>");
    }else{
        out.println("<script>alert('评论删除失败'); location.href ='comment/comment-manage.jsp?novelId="+novelId+"'; </script>");
    }

%>

</body>
</html>