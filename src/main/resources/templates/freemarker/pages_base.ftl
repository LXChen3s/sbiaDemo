<#-- 页面页头、页脚 -->
<#macro page_header>
    <!-- 页面头部 -->
    <header id="index_header">
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
    </header>
</#macro>
<#macro page_footer>
    <!-- 页面脚部 -->
    <footer>
        <div id="index_footer">
            <span> Copyright © 2018 </span>
        </div>
    </footer>
</#macro>