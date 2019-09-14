<#include "/pages_base.ftl"/>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>文章</title>
    <link rel="stylesheet" href="${base.contextPath}/css/index.css">
    <link rel="stylesheet" href="${base.contextPath}/css/index_header.css">
    <script type="text/javascript" src="${base.contextPath}/assert/jquery.min.js"></script>
</head>
<body>
    <@page_header></@page_header>
    <section>
        <div id="article_list">
            <section>
                <div id="article_list_main">
                    <ul>
                        <#list articles as article>
                            <li>
                                <article class="article_li">
                                    <header>
                                        <h2><a href="${base.contextPath}/sbiademo/articleContent/${article.id}">${article.title}</a></h2>
                                    </header>
                                    <div class="article_abs">
                                        <p>${article.abs}</p>
                                    </div>
                                    <footer>
                                        <span>${article.creatDate?datetime}</span>
                                    </footer>
                                </article>
                            </li>
                        </#list>
                    </ul>
                </div>
                <div id="page_bar">
                    <ul>
                        <li id="page_bar_li1">共有<span>${totalPage}</span>页,当前<span>1</span>页</li>
                        <li class="page_bar_li2"><a class="disable_a">上一页</a></li>
                        <li><a class="active_a">1</a></li>
                        <li><a>2</a></li>
                        <li><a>3</a></li>
                        <li><a>4</a></li>
                        <li><a>5</a></li>
                        <li><a>6</a></li>
                        <li><a>7</a></li>
                        <li><a>8</a></li>
                        <li><a>9</a></li>
                        <li><a>10</a></li>
                        <li class="page_bar_li2"><a>下一页</a></li>
                    </ul>
                </div>
            </section>
            <aside></aside>
        </div>
    </section>
    <@page_footer></@page_footer>

    <script>
        $(document).ready(function () {
            pageBar(1);
        });
    </script>
    <script type="text/javascript" language="JavaScript">
        $(document).on('click','#page_bar ul li a',function () {
            if($(this).attr('class')!=='disable_a'){
                //获取当前页数
                let content=this.innerHTML;
                let totalPage=$('#page_bar_li1 span:first-child').text();
                if(content==='上一页'){
                    content=$('.active_a').text()-1;
                }
                else if (content==='下一页') {
                    content=Number($('.active_a').text())+1;
                }
                //拼接字符串,对象转json
                let str={'pageNumber':content,'totalPage':totalPage};
                let Jstr=JSON.stringify(str);
                //执行ajax
                $.ajax({
                    url:"${base.contextPath}/sbiademo/articles",
                    type:"post",
                    contentType:"application/json;charset=utf-8",
                    data:Jstr,
                    dataType:"json",
                    success:function (result) {
                        //字符串转为json对象
                        let data=JSON.parse(result);
                        let tlist=data.pageBean.tlist;
                        changeArticleList(tlist);
                        changePageBar(content,totalPage);
                        pageBar(content);
                    },
                    error:function () {
                        alert("发送失败！！！");
                    }
                });
                //alert(Jstr);
            }
        });
    </script>
    <script>
        function pageBar(pageNumber) {
            let totalPage=$('#page_bar_li1 span:first-child').text();
            if(Number(pageNumber)===Number(totalPage)){
                //到最后一页时把下一页设为无效
                $('#page_bar ul li:last-child').find('a').addClass('disable_a');
            }
            else if(Number(pageNumber)===1){
                //到第一页时把上一页设为无效
                $('#page_bar ul li:eq(1)').find('a').addClass('disable_a');
            }
            //遍历li设置状态
            $('#page_bar ul').find('li').each(function () {
                let num=$(this).find('a').text();
                if(Number(num)===Number(pageNumber)){
                    $(this).find('a').addClass('active_a');
                }
                if(num==='上一页'||num==='下一页'){

                }
                else if(Number(num)>totalPage){
                    $(this).find('a').addClass('disable_a');
                }
            });
            //console.log(totalPage+','+pageNumber);
        }
    </script>
    <script>
        //更新分页标签
        function changePageBar(content,totalPage) {
            let outHtml='';
            if(Number(content)<=5){
                outHtml=outHtml+
                        '<li id="page_bar_li1">共有<span>'+totalPage+'</span>页,当前<span>'+content+'</span>页</li>\n' +
                        '<li class="page_bar_li2"><a>上一页</a></li>\n' +
                        '<li><a>1</a></li>\n' +
                        '<li><a>2</a></li>\n' +
                        '<li><a>3</a></li>\n' +
                        '<li><a>4</a></li>\n' +
                        '<li><a>5</a></li>\n' +
                        '<li><a>6</a></li>\n' +
                        '<li><a>7</a></li>\n' +
                        '<li><a>8</a></li>\n' +
                        '<li><a>9</a></li>\n' +
                        '<li><a>10</a></li>\n' +
                        '<li class="page_bar_li2"><a>下一页</a></li>';
            }
            else {
                let num=Number(content);
                outHtml=outHtml+
                        '<li id="page_bar_li1">共有<span>'+totalPage+'</span>页,当前<span>'+content+'</span>页</li>'+
                        '<li class="page_bar_li2"><a>上一页</a></li>' +
                        '<li><a>'+(num-4)+'</a></li>\n' +
                        '<li><a>'+(num-3)+'</a></li>\n' +
                        '<li><a>'+(num-2)+'</a></li>\n' +
                        '<li><a>'+(num-1)+'</a></li>\n' +
                        '<li><a>'+num+'</a></li>\n' +
                        '<li><a>'+(num+1)+'</a></li>\n' +
                        '<li><a>'+(num+2)+'</a></li>\n' +
                        '<li><a>'+(num+3)+'</a></li>\n' +
                        '<li><a>'+(num+4)+'</a></li>\n' +
                        '<li><a>'+(num+5)+'</a></li>\n' +
                        '<li class="page_bar_li2"><a>下一页</a></li>';
                //alert(outHtml);
            }
            $('#page_bar ul').html(outHtml);
        }
    </script>
    <script>
        //ajax后用于更新文章表
        function changeArticleList(tlist) {
            let outHtml='';
            //获取路径
            let pathName = window.document.location.pathname;
            //截取，得到项目名称
            let projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            for(let i=0;i<tlist.length;i++){
                outHtml=outHtml+
                        '<li>' +
                            '<article class="article_li">' +
                                '<header>' +
                                    '<h2><a href="'+projectName+'/sbiademo/articleContent/'+tlist[i].id+'">'+tlist[i].title+'</a></h2>' +
                                '</header>' +
                                '<div class="article_abs">' +
                                    '<p>'+tlist[i].abs+'</p>' +
                                '</div>' +
                                '<footer>' +
                                    '<span>'+new Date(tlist[i].creatDate).Format("yyyy-M-dd H:mm:ss")+'</span>' +
                                '</footer>' +
                            '</article>' +
                        '</li>';
            }
            $('#article_list_main ul').html(outHtml);
        }
    </script>
    <script>
        // 对Date的扩展，将 Date 转化为指定格式的String
        // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
        // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
        Date.prototype.Format = function (fmt) { //author: meizz
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "H+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }
    </script>

</body>
</html>