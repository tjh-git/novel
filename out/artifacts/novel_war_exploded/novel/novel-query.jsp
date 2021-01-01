<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="org.common.*" %>
<%@page import="org.model.*" %>
<%@page import="org.dal.*" %>
<%@ page import="java.io.PrintWriter" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 小说管理 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	request.setCharacterEncoding("utf-8");
	Novel novel=new Novel();
	String keyword=request.getParameter("keyword");
	List<NovelInfo> list=novel.getList(keyword);
%>

<html>
  <head>  
    <title>小说查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	  <script type="text/css">
		  body{
		  	background-color:＃88C6E5;
		  }
	  </script>

  </head>
  
  <body>
    <div class="header">
    	<h2>小说查询</h2>
    </div>

		<%if(list.size()==0){
			%>
			<p><a>列表为空</a></p>
			<%
		}
		%>
	<%
    		for(NovelInfo info:list){

    	%>
    	<tr>
<%--			<td><a href="${pageContext.request.contextPath}/novel/novel-information.jsp?id=<%=info.getId()%>"><%=info.getTitle() %></a></td>--%>
<%--			<td><%=info.getTitle() %></td>--%>
<%--			<td><%=info.getContext() %></td>--%>
<%--			<td><%=info.getVoteNumber() %></td>--%>
<%--			<td><%=info.getCreatedTime() %></td>--%>
<%--			<td><%out.print("<br>"); %></td>--%>

			<div class="post">
				<h1 class="title"><a href="/novel/novel/novel-information.jsp?id=<%=info.getId()%>"><%=info.getTitle() %></a></h1>
				<p class="byline"><%=info.getCreatedTime() %></p>
				<div class="entry">
					<p><%=Utilty.Substring(info.getContext(), 300) %>
				</div>
				<p class="meta"><a class="more">分类：<%=info.getGenreName() %></a>
					&nbsp;&nbsp;&nbsp;<a href="novel/novel-information.jsp?id=<%=info.getId()%>">详情</a>&nbsp;&nbsp;&nbsp;
				</p>
			</div>

    	</tr>
    	<%
    		}
    	%>
  </body>
</html>