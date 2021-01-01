function getElemensByClassName(className) {  // 通过class获取
    var classArr = new Array();
    var tags = document.getElementsByTagName("*"); //获取所有节点
    for (var item in tags) {
        if (tags[item].nodeType == 1) {
            if (tags[item].getAttribute("class") == className) {
                classArr.push(tags[item]); //收集class匹配的节点
            }
        }
    }
    return classArr;
}

function delete_FF(element) {  // 在FireFox中删除子节点为空的元素
    var childs = element.childNodes;
    for (var i = 0; i < childs.length; i++) {
        var pattern = /\s/; //模式匹配，内容为空
        if (childs[i].nodeName == "#text" && pattern.test(childs[i].nodeValue)) {  //处理
            //alert(childs[i].nodeName);
            element.removeChild(childs[i]); //删除FF中获取的空节点
        }
    }
}

function $(obj) {
    return document.getElementById(obj);
}

window.onload = function () {
    onload1();
    onload2();
};

function onload2() {
    var persons = getElemensByClassName("person");
//  alert(persons);
    for (var item in persons) {  //遍历所有person，为它们绑定投票事件
        (function (_item) {    //匿名函数传入item, 防止因作用域问题导致item总为最后一个
            delete_FF(persons[_item]); //出去FF中空行代表的子节点
            persons[_item].setAttribute("id", "person" + (parseInt(_item) + 1)); //赋上id

            var childs = persons[_item].childNodes;
            for (var i = 0; i < childs.length; i++) {
                // alert(childs[i].nodeName);
                if (childs[i].nodeName == "BUTTON") {  //点击按钮投票
                    var oButton = childs[i];
                }
                if (childs[i].nodeName == "P") {  //投票结果更新
                    var oP = childs[i];
                    var oSpan = oP.getElementsByTagName("span")[0];
                }
                if (childs[i].nodeName=="A"){
                    var id = childs[i];;
                }
            }
            if (oButton != null) {
                oButton.onclick = function () {  //事件绑定
                    var num = oSpan.innerHTML; //获取票数


                    var idnum = id.innerHTML;
                    console.log("num"+num);
                    console.log("aid"+idnum);
                    // 和服务器中的num同步
                    var xhr = false;
                    if (window.XMLHttpRequest) {
                        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
                        xhr = new XMLHttpRequest();
                    } else {
                        // IE6, IE5 浏览器执行代码
                        xhr = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                    xhr.open("GET","../AjaxServlet?id="+idnum+"&num="+num,true);
                    xhr.send();
                    console.log("执行完成");

                    oSpan.innerHTML = (++num); //票数更新
                    this.setAttribute("disabled", "true"); // 一般只能投票一次的
                    alert("投票成功，谢谢您的支持");
                };
            }
        })(item); // 传入各项person
    }
}

//图片轮播
function onload1() {
    setInterval(change, 1000);
    var n = 1;

    function changePic(m) {
        return n = m;
    }

    function change() {
        var myImg = document.getElementById("ss1");
        myImg.src = "../image/0" + n + ".jpg";
        if (n < 5) n++;
        else n = 1;
    }
}

//
// function f() {
//     var conn = new ActiveXObject("ADODB.Connection");
//     conn.Open("Driver={SQL server};Server=.;DataBase=MySchool;UID=sa;Password=ok;");//打开数据库
//     var rs = new ActiveXObject("ADODB.Recordset");
//     var sql="select * from Student";
//     rs.open(sql, conn);
//     var html="";
//     while(!rs.EOF)
//     {
//         html=html+rs.Fields("StudentId")+" "+rs.Fields("studentName")+"<br/>";
//         rs.moveNext();
//     }
//     document.write(html);
//     rs.close();
//     rs = null;
//     conn.close();
//     conn = null;
// }