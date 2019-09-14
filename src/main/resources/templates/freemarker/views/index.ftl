<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="${base.contextPath}/css/index.css">
</head>
<body onload="navbarchange()">
    <header id="index_header">
        <div id="index_header_picture">
            <nav id="index_nav">
                <div id="index_nav_container">
                    <div id="index_nav_left"></div>
                    <div id="index_nav_main">
                        <ul id="index_nav_ul">
                            <li><a href="${base.contextPath}/sbiademo/index">首页</a></li>
                            <li><a href="${base.contextPath}/sbiademo/articlesIndex">博客</a></li>
                            <li><a href="${base.contextPath}/1">首页</a></li>
                            <li><a href="${base.contextPath}/sbiademo/admin/articlesIndex">后台管理</a></li>
                        </ul>
                    </div>
                    <div id="index_nav_right"></div>
                </div>
            </nav>
        </div>
    </header>
    <section id="index_main">
    <section id="index_left_section">
        <div id="index_left">
            <article class="index_article_1">
                <header class="index_article_h1">
                    <a class="index_article_a1" href="/xx">${article1.category.categoryName}</a>
                    <h2><a href="${base.contextPath}/sbiademo/articleContent/${article1.id}">${article1.title}</a></h2>
                </header>
                <div class="index_article_img1">
                    <a href="${base.contextPath}/sbiademo/articleContent/${article1.id}">
                        <img src="${base.contextPath}/images/defaultImage.jpg">
                    </a>
                </div>
                <div class="index_article_abs1">
                    <div class="index_article_abs1_content">
                        <p>
                            ${article1.abs}
                        </p>
                        <div>
                            <a href="${base.contextPath}/sbiademo/articleContent/${article1.id}">了解更多</a>
                        </div>
                    </div>
                    <div></div>
                </div>
            </article>
            <article class="index_article_2">
                <div class="index_article_img1">
                    <a href="${base.contextPath}/sbiademo/articleContent/${article2.id}">
                        <img src="${base.contextPath}/images/defaultImage.jpg">
                    </a>
                </div>
                <header class="index_article_h1">
                    <a class="index_article_a1" href="/4">${article2.category.categoryName}</a>
                    <h2><a href="${base.contextPath}/sbiademo/articleContent/${article2.id}">${article2.title}</a></h2>
                </header>
                <div class="index_article_abs1">
                    <div class="index_article_abs1_content">
                        <p>
                            ${article2.abs}
                        </p>
                        <div>
                            <a href="${base.contextPath}/sbiademo/articleContent/${article2.id}">了解更多</a>
                        </div>
                    </div>
                    <div></div>
                </div>
            </article>
            <article class="index_article_3">
                <div class="index_article_img1">
                    <a href="${base.contextPath}/sbiademo/articleContent/${article3.id}">
                        <img src="${base.contextPath}/images/defaultImage.jpg">
                    </a>
                </div>
                <header class="index_article_h1">
                    <a class="index_article_a1" href="/4">${article3.category.categoryName}</a>
                    <h2><a href="${base.contextPath}/sbiademo/articleContent/${article3.id}">${article3.title}</a></h2>
                </header>
                <div class="index_article_abs1">
                    <div class="index_article_abs1_content">
                        <p>
                            ${article3.abs}
                        </p>
                        <div>
                            <a href="${base.contextPath}/sbiademo/articleContent/${article3.id}">了解更多</a>
                        </div>
                    </div>
                    <div></div>
                </div>
            </article>
            <article class="index_article_2">
                <div class="index_article_img1">
                    <a href="${base.contextPath}/sbiademo/articleContent/${article4.id}">
                        <img src="${base.contextPath}/images/defaultImage.jpg">
                    </a>
                </div>
                <header class="index_article_h1">
                    <a class="index_article_a1" href="/4">${article4.category.categoryName}</a>
                    <h2><a href="${base.contextPath}/sbiademo/articleContent/${article4.id}">${article4.title}</a></h2>
                </header>
                <div class="index_article_abs1">
                    <div class="index_article_abs1_content">
                        <p>
                            ${article4.abs}
                        </p>
                        <div>
                            <a href="${base.contextPath}/sbiademo/articleContent/${article4.id}">了解更多</a>
                        </div>
                    </div>
                    <div></div>
                </div>
            </article>
            <article class="index_article_3">
                <div class="index_article_img1">
                    <a href="${base.contextPath}/sbiademo/articleContent/${article5.id}">
                        <img src="${base.contextPath}/images/defaultImage.jpg">
                    </a>
                </div>
                <header class="index_article_h1">
                    <a class="index_article_a1" href="/4">${article5.category.categoryName}</a>
                    <h2><a href="${base.contextPath}/sbiademo/articleContent/${article5.id}">${article5.title}</a></h2>
                </header>
                <div class="index_article_abs1">
                    <div class="index_article_abs1_content">
                        <p>
                            ${article5.abs}
                        </p>
                        <div>
                            <a href="${base.contextPath}/sbiademo/articleContent/${article5.id}">了解更多</a>
                        </div>
                    </div>
                    <div></div>
                </div>
            </article>
        </div>
    </section>
    <aside id="index_right_aside">
        <div id="index_right">
            <h1>分类</h1>
            <#list categorys as category>
                <h2>${category.categoryName}(${category.count})</h2>
            </#list>
        </div>
    </aside>
    </section>
        <footer>
        <div id="index_footer">
            <span> Copyright © xxxx </span>
        </div>
    </footer>

    <script type="text/javascript" src="${base.contextPath}/js/index.js"></script>
    <script>
        //获取路径
        let pathName = window.document.location.pathname;
        //截取，得到项目名称
        let projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        document.getElementById("index_header_picture").style.backgroundImage='url("'+projectName+'/images/beijing.jpg")';
    </script>
</body>
</html>