<#include "/pages_base.ftl"/>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>${articleContent.title}</title>
    <link rel="stylesheet" href="${base.contextPath}/css/index.css">
    <link rel="stylesheet" href="${base.contextPath}/css/index_header.css">
</head>
<body>
    <@page_header></@page_header>
    <!-- 页面主要内容 -->
    <section id="article_main">
        <!-- 文章实体 -->
        <article id="article_content">
            <header>
                <nav id="article_content_nav">
                    <ul>
                        <li class="article_content_nav_li"><a href="/1">${articleContent.user.nickname}的个人空间</a></li>
                        <li class="article_content_nav_li"><a href="/2">${articleContent.category.categoryName}</a></li>
                        <li><strong>${articleContent.title}</strong></li>
                    </ul>
                </nav>
                <h1>${articleContent.title}</h1>
                <div id="article_content_header_div">
                    <div><img src="${base.contextPath}/images/renWuTouXiang.jpg"></div>
                    <div><p>${articleContent.user.nickname} 发表于 ${articleContent.creatDate?datetime}</p></div>
                    <div><p>字数 xxx</p></div>
                    <div><p>阅读量 xxx</p></div>
                    <div><p>点赞 xxx</p></div>
                    <div><p>评论 xxx</p></div>
                </div>
                <div id="article_content_header_tags">
                    <a href="/2">spring</a>
                    <a href="/2">spring</a>
                    <a href="/2">spring</a>
                </div>
            </header>
            <div id="article_content_main">
                ${articleContent.content}
            </div>
            <footer>
                <p>© 著作权归作者所有</p>
            </footer>
        </article>
        <!-- 文章辅助信息 -->
        <aside id="article_aside">
            <p>xx</p>
        </aside>
    </section>
    <@page_footer></@page_footer>
</body>
</html>