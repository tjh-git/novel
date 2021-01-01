<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="org.common.*" %>
<%@page import="org.model.*" %>
<%@page import="org.dal.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 小说管理 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    request.setCharacterEncoding("utf-8");
    Novel novel = new Novel();
    String keyword = request.getParameter("keyword");
//    String keyword = "";
    List<NovelInfo> list = novel.getList(keyword);
//    System.out.println("list: "+list);
%>

<html>
<head>
    <title>小说管理</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link type="text/css" href="../css/manage.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>

    <script type="text/css">

        tr{
            background-color: #F8FFFA;
            height: 100px;
        }
        td{
        height: 30px;
        }
    </script>
</head>

<body>
<div class="header">
    <h2>当前位置：小说管理</h2>
</div>
<div class="left">
    <%--    <ul>--%>
    <%--        <li><a href="../novel/novel-manage.jsp">小说管理</a></li>--%>
    <%--    </ul>--%>
</div>

<div style="margin-left: 30%;">

    <div class="context">
        <div class="th">
            <form id="form1" name="form1" method="post"
                  action="${pageContext.request.contextPath}/novel/novel-manage.jsp">
                请输入要查询的小说标题：
                <input type="text" name="keyword" id="keyword" style="height:25px"/>
                <input class="en" type="submit" name="button" id="button" value="查询" style="cursor: pointer"/>
            </form>
        </div>
        <div style=" float: left; margin-left: -30px;">
            <div id="dataShow" style="width:450px;margin:0 auto;">
                <table class="table" id="table" style="width:800px;"></table>
            </div>
            <br/>
            <div id="page" style="margin-left: -80px; margin-top: -20px"></div>
        </div>

        <div>

            <script type="text/javascript">
                <%--alert("${pageContext.request.contextPath}/novel/novel-manage.jsp")--%>
                //编造表数据
                //表头
                var head =
                    '<thead style="margin-left: -200px"><tr>' +
                    '<th>小说标题 </th>' +
                    '<th>所属分类 </th>' +
                    '<th>发布时间</th>' +
                    '<th>小说票数 </th>' +
                    '<th>操作 </th>' +
                    '</tr></thead><tbody class="table">';
                //表内容，后台返回的内容封装到这里
                var pageData = [];
                <%
                  for(NovelInfo info:list){
              %>
                var data = '<tr>' +
                    '<td width="300"><%=info.getTitle() %></td>' +
                    '<td ><%=info.getGenreName() %></td>' +
                    '<td><%=info.getCreatedTime() %></td>' +
                    '<td><%=info.getVoteNumber() %></td>' +
                    '<td>' +
                    '<a href="${pageContext.request.contextPath}/novel/novel-delete.jsp?id=<%=info.getId()%>">删除小说</a>' + '&nbsp;&nbsp;' +
                    '|<a href="../comment/comment-manage.jsp?novelId=<%=info.getId()%>">管理评论</a>' +
                    '</td>'
                    + '</tr>';
                pageData.push(data);
                //表结尾
                var end = '</tbody>';
                <%
                    }
                %>
                $(function () {
                    var Count = pageData.length;//记录条数
                    var PageSize = 8;//设置每页示数目
                    var PageCount = Math.ceil(Count / PageSize);//计算总页数
                    var currentPage = 1;//当前页，默认为1。
                    //造个简单的分页按钮
                    for (var i = 1; i <= PageCount; i++) {
                        var pageN = '<a href="#" selectPage="' + i + '"style="margin-left:210px">第' + i + '页</a>';
                        $('#page').append(pageN);
                    }
                    //显示默认页（第一页）
                    $('#table').empty().append(head);
                    for (i = (currentPage - 1) * PageSize; i < PageSize * currentPage; i++) {
                        $('#table').append(pageData[i]);
                    }
                    $('#table').append(end);

                    //显示选择页的内容
                    $('a').click(function () {
                        var selectPage = $(this).attr('selectPage');
                        $('#table').html('');
                        $('#table').append(head);
                        for (i = (selectPage - 1) * PageSize; i < PageSize * selectPage; i++) {
                            $('#table').append(pageData[i]);
                        }
                        $('#table').append(end);
                    });
                    $("tr").css("bgColor", "#F8FFFA", "height", "100px");//为单数行表格设置背景颜色
                    $("tr:even").css("background-color", "#E7E5DC");//为双数行表格设置背颜色素
                    $("td").css("height", "30px");
                    // $("td").css("width", "800px");
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>