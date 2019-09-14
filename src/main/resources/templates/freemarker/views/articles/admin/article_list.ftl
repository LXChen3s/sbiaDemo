<#include "/admin_base.ftl"/>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${base.contextPath}/assert/css/bootstrap.min.css">
    <link rel="stylesheet" href="${base.contextPath}/css/index.css">
    <link rel="stylesheet" href="${base.contextPath}/css/index_header.css">
    <link rel="stylesheet" href="https://cdn.staticfile.org/font-awesome/4.7.0/css/font-awesome.css">
    <script type="text/javascript" charset="UTF-8" src="${base.contextPath}/assert/jquery.min.js"></script>
    <script type="text/javascript" src="${base.contextPath}/assert/js/bootstrap.min.js"></script>
</head>
<body>
    <@adminNav>
        <section id="blog">
            <header>
                <h2>博客管理</h2>
            </header>
            <div id="blog_content">
                <div id="blog_option">
                    <a href="${base.contextPath}/sbiademo/admin/changeArticle?id=-1"><button type="button" class="btn btn-primary">添加博客</button></a>
                </div>
                <div>
                    <table class="table table-striped table-bordered table-hover">
                        <colgroup>
                            <col style="width: 8%">
                            <col style="width: 16%">
                            <col style="width: 8%">
                            <col style="width: 8%">
                            <col style="width: 8%">
                            <col style="width: 8%">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>标题</th>
                            <th>创建人</th>
                            <th>创建日期</th>
                            <th>阅读量</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list articles as article>
                            <tr>
                                <td>${article.id}</td>
                                <td>${article.title}</td>
                                <td>${article.user.name}</td>
                                <td>${article.creatDate?datetime}</td>
                                <td>${article.articleRead}</td>
                                <td>
                                    <div class="btn-group">
                                        <a href="${base.contextPath}/sbiademo/admin/changeArticle?id=${article.id}"><button type="button" class="btn btn-primary btn-sm">更新</button></a>
                                        <button type="button" class="btn btn-warning btn-sm deleteButton" data-toggle="modal" data-target="#deleteArticle">删除</button>
                                    </div>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                    <!-- 删除模态框 -->
                    <div class="modal fade" id="deleteArticle">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <!-- 模态框头部 -->
                                <div class="modal-header">
                                    <h4 class="modal-title">删除操作确认</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>
                                <!-- 模态框主体 -->
                                <div class="modal-body">
                                    模态框内容..
                                </div>
                                <!-- 模态框底部 -->
                                <div class="modal-footer">
                                    <button type="button" id="rel_deleteArticle" class="btn btn-warning" data-dismiss="modal">确认</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="admin_page_bar">
                    <div id="admin_page_bar_header">
                        <p>共有<span>${totalPage}</span>页，当前第<span>1</span>页</p>
                    </div>
                    <div id="admin_page_bar_content">
                        <ul class="pagination">
                            <li class="page-item disabled"><a class="page-link" href="#">上一页</a></li>
                            <li class="page-item active"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">4</a></li>
                            <li class="page-item"><a class="page-link" href="#">5</a></li>
                            <li class="page-item"><a class="page-link" href="#">6</a></li>
                            <li class="page-item"><a class="page-link" href="#">7</a></li>
                            <li class="page-item"><a class="page-link" href="#">8</a></li>
                            <li class="page-item"><a class="page-link" href="#">9</a></li>
                            <li class="page-item"><a class="page-link" href="#">下一页</a></li>
                        </ul>
                    </div>
                    <div id="admin_page_bar_footer">
                        <p>跳转到 页</p>
                    </div>
                </div>
            </div>
        </section>
    </@adminNav>
    <script>
        $(document).ready(function () {
            setPageBar(1);
        });
    </script>
    <script>
        $(document).on('click','.page-item',function () {
            if($(this).attr('class').search('disabled')===-1){
                //获取被点击标签页
                let clickNum=$(this).find('a').text();
                let totalPage=Number($('#admin_page_bar_header p span:first-child').text());
                if(clickNum==='上一页'){
                    clickNum=Number($('.active').find('a').text())-1;
                }
                else if(clickNum==='下一页'){
                    clickNum=Number($('.active').find('a').text())+1;
                }
                //拼接字符串,对象转json
                let str={'pageNumber':clickNum,'totalPage':totalPage};
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
                        changePageBar(Number(clickNum));
                        setPageBar(Number(clickNum));
                    },
                    error:function () {
                        alert("发送失败！！！");
                    }
                });
            }
        });
    </script>
    <script>
        //ajax后用于更新文章表
        function changeArticleList(tlist) {
            let outHtml='';
            let proName=getProjectName();
            for(let i=0;i<tlist.length;i++){
                outHtml=outHtml+
                        '<tr>'+
                        '<td>'+tlist[i].id+'</td>'+
                        '<td>'+tlist[i].title+'</td>'+
                        '<td>'+tlist[i].user.name+'</td>'+
                        '<td>'+new Date(tlist[i].creatDate).Format("yyyy-M-dd H:mm:ss")+'</td>'+
                        '<td>'+tlist[i].articleRead+'</td>'+
                        '<td>'+
                        '<div class="btn-group">'+
                        '<a href="'+proName+'/sbiademo/admin/changeArticle?id='+tlist[i].id+'"><button type="button" class="btn btn-primary btn-sm">更新</button></a>'+
                        '<button type="button" class="btn btn-warning btn-sm deleteButton" data-toggle="modal" data-target="#deleteArticle">删除</button>'+
                        '</div>'+
                        ' </td>'+
                        '</tr>';
            }
            $('#blog_content table tbody').html(outHtml);
        }
    </script>
    <script>
        //模态框弹出内容
        $(document).on('click','.deleteButton',function () {
            let title=$(this).parents('tr').find('td').eq(1).text();
            let id=$(this).parents('tr').find('td').eq(0).text();
            let outHtml='确认删除：'+title+'<p id="deleteId" hidden="hidden">'+id+'</p>';
            $('.modal-body').html(outHtml);
        });
    </script>
    <script>
        //删除文章操作
        $(document).on('click','#rel_deleteArticle',function () {
            let id=$('#deleteId').text();
            let pageNum=$('.active').find('a').text();
            let totalPage=Number($('#admin_page_bar_header p span:first-child').text());
            $.ajax({
                url:'${base.contextPath}/sbiademo/admin/deleteArticle?id='+id+'&pageNum='+pageNum+'&totalPage='+totalPage,
                type:"get",
                contentType:"application/json;charset=utf-8",
                dataType:'json',
                success:function (result) {
                    //字符串转为json对象
                    let data=JSON.parse(result);
                    console.log(data);
                    let tlist=data.pageBean.tlist;
                    changeArticleList(tlist);
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
        });
    </script>
    <script type="text/javascript" src="${base.contextPath}/js/index.js"></script>
</body>
</html>