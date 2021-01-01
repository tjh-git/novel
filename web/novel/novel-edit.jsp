<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="org.common.*" %>
<%@page import="org.model.*" %>
<%@page import="org.dal.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 发布小说 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>小说编辑发布界面</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script type="text/css">
        body {
            /*background-color:#e6e7d1;*/
            background-color: black;
        }


        body {

        }
    </script>
        <%
    request.setCharacterEncoding("utf-8");
    Genre cls = new Genre();
    List<GenreInfo> list = cls.getList();

%>
<body style="background-color: #e6e7d1; ">
欢迎您：<%=Utilty.readCookie(request, "author")%>
<%
    request.setCharacterEncoding("utf-8");
    String act = request.getParameter("action");
    Novel novel = new Novel();
    NovelInfo ninfo = new NovelInfo();
    ninfo.setTitle(request.getParameter("txtTitle"));
    ninfo.setContext(request.getParameter("content"));
//	ninfo.setContext(editor.txt.text());
    ninfo.setGenreId(DataConverter.toInt(request.getParameter("selClass")));
    if(ninfo.getContext()!=null && !"".equals(ninfo.getContext()))
         novel.insert(ninfo);
    System.out.println(ninfo);


%>
<p style="font-size: 23px ; margin-left: 500px">当前位置：小说编辑</p>
<form id="form1" name="form1" method="post" action="${pageContext.request.contextPath}/novel/novel-edit.jsp">
    <table style="margin: 10px">
        <tr>
            <td style="font-size: 23px;" width="200px">小说标题:</td>
            <td><input type="text" name="txtTitle" id="txtTitle" width="500px"/></td>
        </tr>
        <tr>
            <td style="margin-left: 30px;font-size: 23px">小说所属分类:</td>
            <td>
                <select   name="selClass" id="selClass" style="font-size: 24px; width: 200px">
                    <%
                        for (GenreInfo cinfo : list) {
                    %>
                    <option value="<%=cinfo.getId() %>" style="font-size: 20px">
                        <%if (cinfo.getId() == ninfo.getId()) %>
                        <%=cinfo.getName() %>
                    </option>
                    <%
                        }
                    %>

                </select>
            </td>
        </tr>
        <tr>
            <td style="font-size: 23px">小说内容:</td>
            <td><textarea hidden="hidden" id="text1" rows="4" cols="100" name="content"></textarea></td>
            <td>
                <div id="div1" style="margin-left: -200px">
                    <%--						<p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>--%>
                </div>
                <%--					<textarea id="text1" style="width:100%; height:200px;"></textarea>--%>

                <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
                <script type="text/javascript" src="//unpkg.com/wangeditor/dist/wangEditor.min.js"></script>
                <script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>
                <link href="http://cdn.bootcss.com/highlight.js/8.0/styles/monokai_sublime.min.css" rel="stylesheet">

                <script type="text/javascript">
                    const SINA_URL_PATH = 'http://img.t.sinajs.cn/t4/appstyle/expression/ext/normal'

                    const E = window.wangEditor
                    const editor = new E('#div1')
                    editor.config.height = 450//宽度
                    // 配置颜色（文字颜色、背景色）
                    editor.config.colors = [
                        '#000000',
                        '#e6e7d1',
                        '#1c487f',
                        '#4d80bf'
                    ]
                    //http://localhost:8080/novel/novel/novel-edit.jsp

                    const $text1 = $('#text1')
                    editor.config.onchange = function (html) {
                        // 第二步，监控变化，同步更新到 textarea
                        $text1.val(html)
                    }
                    editor.highlight = hljs//代码高亮
                    //表情
                    editor.config.emotions = [
                        {
                            title: '新浪', // tab 的标题
                            type: 'image', // 'emoji' 或 'image' ，即 emoji 形式或者图片形式
                            content: [
                                {alt: '[坏笑]', src: `${SINA_URL_PATH}/50/pcmoren_huaixiao_org.png`},
                                {alt: '[舔屏]', src: `${SINA_URL_PATH}/40/pcmoren_tian_org.png`},
                                {alt: '[污]', src: `${SINA_URL_PATH}/3c/pcmoren_wu_org.png`},
                            ]
                        },
                        {
                            title: 'emoji',  // tab 的标题
                            type: 'emoji', // 'emoji' / 'image'
                            // emoji 表情，content 是一个数组即可
                            content: '😀 😃 😄 😁 😆 😅 😂 😊 😇 🙂 🙃 😉 😓 😪 😴 🙄 🤔 😬 🤐'.split(/\s/),
                        }
                    ]

                    editor.config.placeholder = '编辑区域'
                    editor.create()


                    // 第一步，初始化 textarea 的值
                    $text1.val(editor.txt.html())

                    // const html = editor.txt.html();
                    // const safeHtml = xss(html);
                    // console.log('处理过 xss 攻击的 html', safeHtml);

                </script>
            </td>
        </tr>

        <tr>
            <td colspan="2" style=" margin-left: 300px;">
                <input type="submit" name="button" id="button" value="提交" style="width: 50px;height: 30px; margin-left: 140%;margin-top: 20px"/>
                <input type="reset" name="button2" id="button2" value="重置" style="width: 50px;height: 30px; margin-left: 160% ;position: relative ; margin-top: -30px"/>
            </td>


        </tr>
    </table>
</form>
</body>
</html>