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
                <h2>
                    博客管理<#if (articleContent.id)??>> 更新博客<#else>> 添加博客</#if>
                </h2>
            </header>
            <div id="blog_content">
                <section id="change_blog">
                    <div id="change_permission">
                        <form action="${base.contextPath}/sbiademo/admin/changePermission" method="post">
                            <input class="form-control" type="text" name="url" placeholder="url"/>
                            <input class="form-control" type="text" name="name" placeholder="权限名称"/>
                            <button class="btn btn-primary btn-block" type="submit">提交</button>
                        </form>
                    </div>
                </section>
            </div>
        </section>
    </@adminNav>
</body>
</html>
