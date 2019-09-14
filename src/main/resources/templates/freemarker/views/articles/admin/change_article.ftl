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
    <script src="${base.contextPath}/assert/ckeditor5_classic/ckeditor.js"></script>
    <script src="${base.contextPath}/assert/ckeditor5_classic/translations/zh-cn.js"></script>
</head>
<body>
    <@adminNav>
        <section id="blog">
            <header>
                <h2>
                    博客管理<#if (articleContent.id)??>> 更新博客<#else>> 添加博客</#if>
                </h2>
            </header>
            <div id="blog_content">
                <section id="change_blog">
                    <form action="${base.contextPath}/sbiademo/admin/saveArticle" method="post">
                        <div class="form-group">
                            <label for="ID">ID:</label>
                            <#if articleContent??>
                                <input type="hidden" class="form-control" id="ID" name="id" value="${articleContent.id}">
                                <input type="text" class="form-control" id="ID" name="id" disabled value="${articleContent.id}">
                            <#else>
                                <input type="text" class="form-control" id="ID" name="id" disabled placeholder="默认">
                            </#if>
                        </div>
                        <div class="form-group">
                            <label for="title">标题:</label>
                            <#if articleContent??>
                                <input type="text" class="form-control" id="title" name="title" value="${articleContent.title}">
                            <#else>
                                <input type="text" class="form-control" id="title" name="title" placeholder="请输入标题">
                            </#if>
                        </div>
                        <div class="form-group">
                            <label for="editor1">正文编辑：</label>
                            <#if articleContent??>
                                <textarea id="editor1" name="content">${articleContent.content}</textarea>
                            <#else>
                                <textarea id="editor1" name="content">请在此编辑...</textarea>
                            </#if>
                        </div>
                        <button type="submit" class="btn btn-primary">提交</button>
                    </form>
                    <script>
                        //获取路径
                        let pathName = window.document.location.pathname;
                        //截取，得到项目名称
                        let projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
                        ClassicEditor
                                .create( document.querySelector('#editor1'),{
                                    //工具栏
                                    toolbar:['heading','|','bold','italic','link','bulletedList','numberedList','blockQuote','mediaEmbed','|','imageUpload','imageStyle:full','imageStyle:side','imageTextAlternative','|','insertTable','tableColumn','tableRow','mergeTableCells','|','undo','redo'],
                                    //上传url，返回json数据包括uploaded、url
                                    ckfinder: {
                                        uploadUrl: projectName+'/sbiademo/admin/uploadImage?command=QuickUpload&type=Files&responseType=json'
                                    },
                                    //语言
                                    language: 'zh-cn'
                                })
                                .then(editor1 => {
                                    //console.log( 'Editor was initialized', editor1 );
                                    //console.log(Array.from( editor1.ui.componentFactory.names() ));
                                    //console.log(ClassicEditor.builtinPlugins.map( plugin => plugin.pluginName ));
                                })
                                .catch(err => {
                                    console.error( err.stack );
                                });
                    </script>
                </section>
            </div>
        </section>
    </@adminNav>
</body>
</html>