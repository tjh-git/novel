<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="org.common.*" %>
<%@ page import="org.model.*" %>
<%@ page import="org.dal.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 下架小说处理 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
	Novel novel=new Novel();
	int result=0;
	result=novel.delete(DataConverter.toInt(request.getParameter("id")));
	if(result==1){
 		out.println("<script>alert('小说删除成功'); location.href ='novel/novel-manage.jsp'; </script>");
 		//response.sendRedirect("../novel/novel-manage.jsp");
		
	}else{
		out.println("<script>alert('小说删除失败'); location.href ='novel/novel-manage.jsp';</script>");
	}
%>
  </body>
</html>