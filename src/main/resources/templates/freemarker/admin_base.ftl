<#-- 后台通用导航 -->
<#macro adminNav>
    <section>
        <div id="admin_main">
            <aside>
                <div id="admin_left">
                    <header>
                        <a href="${base.contextPath}/sbiademo/index"><i class="fa fa-desktop"></i></a>
                        <h1>后台管理</h1>
                    </header>
                    <nav>
                        <ul  id="admin_navBar">
                            <li><a><i class="fa fa-home"></i>首页</a></li>
                            <li><a href="${base.contextPath}/sbiademo/admin/articlesIndex"><i class="fa fa-pencil"></i>博客</a></li>
                            <li>
                                <a href="${base.contextPath}/sbiademo/admin/getPermissionIndex">
                                    <i class="fa fa-address-book"></i>权限管理
                                </a>
                            </li>
                            <li><a href="${base.contextPath}/sbiademo/loginOut"><i class="fa fa-reply"></i>退出</a></li>
                        </ul>
                    </nav>
                </div>
            </aside>
            <section>
                <div id="admin_right">
                    <#nested />
                </div>
            </section>
        </div>
    </section>
    <script>
        $(document).ready(function () {
            setNavHeight();
        });
    </script>
    <script>
        window.onresize=function () {
            setNavHeight();
        };
    </script>
    <script>
        //设置左边栏高度
        function setNavHeight() {
            let window_height=window.innerHeight;
            $('#admin_left').css('height',window_height);
        }
    </script>
</#macro>