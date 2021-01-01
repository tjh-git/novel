<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2020/12/30
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


</head>
<body>
<div id="div1" >
    <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
</div>
<textarea id="text1"  hidden="hidden" style="width:100%; height:200px;"></textarea>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="//unpkg.com/wangeditor/dist/wangEditor.min.js"></script>
<script type="text/javascript">
    const E = window.wangEditor
    const editor = new E('#div1')
    const $text1 = $('#text1')
    editor.config.onchange = function (html) {
        // 第二步，监控变化，同步更新到 textarea
        $text1.val(html);
        console.log($text1.html());
    }
    editor.create()

    // 第一步，初始化 textarea 的值
    $text1.val(editor.txt.html())
</script>

</body>
</html>
