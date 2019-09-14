<#include "/admin_base.ftl"/>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>权限管理主页</title>
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
                <h2>权限管理</h2>
            </header>
            <div id="blog_content">
                <div id="permission_list">
                    <div id="permission_list_tag"><i class="fa fa-caret-right"></i>权限列表</div>
                    <div id="permission_list_content">
                        <div>
                            <button id="add_permission" type="button" class="btn btn-primary"  data-toggle="modal" data-target="#myModal">添加权限</button>
                        </div>
                        <#if permissions??>
                            <div>
                                <table class="table table-striped table-bordered table-hover">
                                    <colgroup>
                                        <col style="width: 8%">
                                        <col style="width: 16%">
                                        <col style="width: 8%">
                                        <col style="width: 8%">
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>URL</th>
                                        <th>权限名称</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#list permissions as permission>
                                        <tr>
                                            <td>${permission.id}</td>
                                            <td>${permission.url}</td>
                                            <td>${permission.name}</td>
                                            <td>
                                                <div class="btn-group">
                                                    <button type="button" class="btn btn-primary btn-sm updatePermission" data-toggle="modal" data-target="#myModal">更新</button>
                                                    <button type="button" class="btn btn-warning btn-sm deletePermission" data-toggle="modal" data-target="#myModal">删除</button>
                                                </div>
                                            </td>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </div>
                        </#if>
                    </div>
                </div>
                <div id="role_list">
                    <div id="role_list_tag"><i class="fa fa-caret-right"></i>角色列表</div>
                    <div id="role_list_content">
                        <div>
                            <button id="add_role" type="button" class="btn btn-primary"  data-toggle="modal" data-target="#myModal">添加角色</button>
                        </div>
                        <div>
                            <table class="table table-striped table-bordered table-hover">
                                <colgroup>
                                    <col style="width: 8%">
                                    <col style="width: 16%">
                                    <col style="width: 32%">
                                    <col style="width: 8%">
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>角色名</th>
                                    <th>权限</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list roles as role>
                                <tr>
                                    <td>${role.id}</td>
                                    <td>${role.name}</td>
                                    <td>
                                        <#if (role.pList)??>
                                            <#list role.pList as perm>
                                                ${perm.name}<#if perm_has_next>,</#if>
                                            </#list>
                                        </#if>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary btn-sm updateRole" data-toggle="modal" data-target="#myModal">更新</button>
                                            <button type="button" class="btn btn-warning btn-sm deleteRole" data-toggle="modal" data-target="#myModal">删除</button>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div id="person_list">
                    <div id="person_list_tag"><i class="fa fa-caret-right"></i>用户-角色列表</div>
                    <div id="person_list_content">
                        <div>
                            <table class="table table-striped table-bordered table-hover">
                                <colgroup>
                                    <col style="width: 8%">
                                    <col style="width: 8%">
                                    <col style="width: 16%">
                                    <col style="width: 8%">
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>角色</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list users as user>
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.name}</td>
                                    <td>
                                        <#if (user.rList)??>
                                            <#list user.rList as role>
                                                ${role.name}<#if role_has_next>,</#if>
                                            </#list>
                                        </#if>
                                    </td>
                                    <td>
                                        <div class="btn-group">
                                            <button type="button" class="btn btn-primary btn-sm updateUser" data-toggle="modal" data-target="#myModal">更新</button>
                                        </div>
                                    </td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 模态框 -->
            <div class="modal fade" id="myModal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- 模态框头部 -->
                        <div class="modal-header">
                            <h4 class="modal-title">模态框头部</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- 模态框主体 -->
                        <div class="modal-body">
                            模态框内容..
                        </div>

                        <!-- 模态框底部 -->
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                        </div>

                    </div>
                </div>
            </div>

        </section>
    </@adminNav>
    <script>
        $(document).on('click','#permission_list_tag',function () {
            let listContent=$("#permission_list_content");
            foldChange($(this),listContent);
        });
        $(document).on('click','#role_list_tag',function () {
            let listContent=$("#role_list_content");
            foldChange($(this),listContent);
        });
        $(document).on('click','#person_list_tag',function () {
            let listContent=$("#person_list_content");
            foldChange($(this),listContent);
        });
    </script>
    <script>
        function foldChange(tag,content) {
            let tubiao=tag.find('i');
            let tubiao_class=tubiao.attr('class');
            //计算自动的高度
            let futureHeight= content.css({'height':'auto','line-height':'24px'}).height();
            if(tubiao_class==='fa fa-caret-right'){
                tubiao.attr('class','fa fa-caret-down');
                content.animate({
                    height:futureHeight,
                    lineHeight:'24px',
                    padding:'24px'
                });
            }
            else{
                tubiao.attr('class','fa fa-caret-right');
                content.animate({
                    height:'0',
                    lineHeight:'0',
                    padding:'0'
                });
            }
        }
    </script>
    <script>
        //添加权限
        $(document).on('click','#add_permission',function () {
            $('.modal-title').html('添加权限');
            let outHtml='<input id="permission_url" class="form-control" style="margin-bottom: 16px" type="text" name="url" placeholder="url"/>' +
                        '<input id="permission_name" class="form-control" type="text" name="name" placeholder="权限名称"/>';
            $('.modal-body').html(outHtml);
            let footOutHtml='<button id="add_permission_execute" type="button" class="btn btn-primary" data-dismiss="modal">提交</button>'+
                            '<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>';
            $('.modal-footer').html(footOutHtml);
        });
    </script>
    <script>
        //执行添加权限
        $(document).on('click','#add_permission_execute',function () {
            let pUrl=$('#permission_url').val();
            let pName=$('#permission_name').val();
            //拼接字符串,对象转json
            let str={'pUrl':pUrl,'pName':pName};
            let Jstr=JSON.stringify(str);
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/changePermission",
                type:"post",
                contentType:"application/json;charset=utf-8",
                data:Jstr,
                dataType:"json",
                success:function (result) {
                    //结果成功，刷新权限表
                    changePermissionList(result.permissions);
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
        });
    </script>
    <script>
        //删除权限
        $(document).on('click','.deletePermission',function () {
            let id=$(this).parents('tr').find('td').eq(0).text();
            let url=$(this).parents('tr').find('td').eq(1).text();
            let name=$(this).parents('tr').find('td').eq(2).text();

            $('.modal-title').html('删除权限');
            let outHtml='<input id="permission_id" hidden class="form-control" type="text" name="id" value="'+id+'"/>'+
                    '<input id="permission_url" disabled class="form-control" style="margin-bottom: 16px" type="text" name="url" value="'+url+'"/>' +
                    '<input id="permission_name" disabled class="form-control" type="text" name="name" value="'+name+'"/>';
            $('.modal-body').html(outHtml);
            let footOutHtml='<button id="delete_permission_execute" type="button" class="btn btn-primary" data-dismiss="modal">提交</button>'+
                    '<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>';
            $('.modal-footer').html(footOutHtml);
        });
        //执行删除权限
        $(document).on('click','#delete_permission_execute',function () {
            let id=$('#permission_id').val();
            let url=$('#permission_url').val();
            let name=$('#permission_name').val();
            //拼接字符串,对象转json
            let str={'id':id,'url':url,'name':name};
            let Jstr=JSON.stringify(str);
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/deletePermission",
                type:"post",
                contentType:"application/json;charset=utf-8",
                data:Jstr,
                dataType:"json",
                success:function (result) {
                    //结果成功，刷新权限表
                    changePermissionList(result.permissions);
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
        });
    </script>
    <script>
        //更新权限
        $(document).on('click','.updatePermission',function () {
            let id=$(this).parents('tr').find('td').eq(0).text();
            let url=$(this).parents('tr').find('td').eq(1).text();
            let name=$(this).parents('tr').find('td').eq(2).text();

            $('.modal-title').html('更新权限');
            let outHtml='<input id="permission_id" hidden class="form-control" type="text" name="id" value="'+id+'"/>'+
                        '<input id="permission_url" class="form-control" style="margin-bottom: 16px" type="text" name="url" value="'+url+'"/>' +
                        '<input id="permission_name" class="form-control" type="text" name="name" value="'+name+'"/>';
            $('.modal-body').html(outHtml);
            let footOutHtml='<button id="update_permission_execute" type="button" class="btn btn-primary" data-dismiss="modal">提交</button>'+
                    '<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>';
            $('.modal-footer').html(footOutHtml);
        });
        //执行更新权限
        $(document).on('click','#update_permission_execute',function () {
            let id=$('#permission_id').val();
            let url=$('#permission_url').val();
            let name=$('#permission_name').val();
            //拼接字符串,对象转json
            let str={'id':id,'url':url,'name':name};
            let Jstr=JSON.stringify(str);
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/updatePermission",
                type:"post",
                contentType:"application/json;charset=utf-8",
                data:Jstr,
                dataType:"json",
                success:function (result) {
                    //结果成功，刷新权限表
                    changePermissionList(result.permissions);
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
        });
        //ajax后用于更新权限表
        function changePermissionList(tlist) {
            let outHtml='';
            for(let i=0;i<tlist.length;i++){
                outHtml=outHtml+
                        '<tr>'+
                        '<td>'+tlist[i].id+'</td>'+
                        '<td>'+tlist[i].url+'</td>'+
                        '<td>'+tlist[i].name+'</td>'+
                        '<td>'+
                        '<div class="btn-group">'+
                        '<button type="button" class="btn btn-primary btn-sm updatePermission" data-toggle="modal" data-target="#myModal">更新</button>'+
                        '<button type="button" class="btn btn-warning btn-sm deletePermission" data-toggle="modal" data-target="#myModal">删除</button>'+
                        '</div>'+
                        ' </td>'+
                        '</tr>';
            }
            $('#permission_list_content div table tbody').html(outHtml);
        }
    </script>
    <script>
        //添加角色
        $(document).on('click','#add_role',function () {
            $('.modal-title').html('添加角色');
            let outHtml='<input id="role_name" class="form-control" style="margin-bottom: 16px" type="text" name="name" placeholder="角色名称"/>' +
                        '<label style="margin-bottom: 16px;display:block">添加权限：</label>';
            //获取所有权限
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/getAllPermission",
                type:"get",
                async: false,
                dataType:"json",
                success:function (result) {
                    //结果成功,拼接outHtml
                    let permissions=result.permissions;
                    for(let i=0;i<permissions.length;i++){
                        outHtml=outHtml+
                                '<input style="margin:0 16px 8px 8px" class="role_permission" type="checkbox" name="perm[]" value="'+permissions[i].id+'">'+permissions[i].name+'<br>';
                    }
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
            $('.modal-body').html(outHtml);
            let footOutHtml='<button id="add_role_execute" type="button" class="btn btn-primary" data-dismiss="modal">提交</button>'+
                    '<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>';
            $('.modal-footer').html(footOutHtml);
        });
        //执行添加角色
        $(document).on('click','#add_role_execute',function () {
            let rName=$('#role_name').val();
            let checked=$('.role_permission:checked');
            let pArray=new Array();
            let i=0;
            $.each(checked,function () {
                pArray[i++]=$(this).val();
            });
            //拼接字符串,对象转json字符串
            let str={'rName':rName,'pArray':pArray};
            let Jstr=JSON.stringify(str);
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/saveRole",
                type:"post",
                contentType:"application/json;charset=utf-8",
                data:Jstr,
                dataType:"json",
                success:function (result) {
                    //结果成功，刷新角色表
                    //console.log(result.roles);
                    changeRoleList(result.roles);
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
        });
        //ajax后用于更新角色表
        function changeRoleList(tlist) {
            let outHtml='';
            for(let i=0;i<tlist.length;i++){
                let pOutHtml='';
                if(tlist[i].pList!==undefined){
                    for(let j=0;j<tlist[i].pList.length;j++){
                        pOutHtml=pOutHtml+tlist[i].pList[j].name;
                        if(j!==tlist[i].pList.length-1){
                            pOutHtml=pOutHtml+',';
                        }
                    }
                }
                outHtml=outHtml+
                        '<tr>'+
                        '<td>'+tlist[i].id+'</td>'+
                        '<td>'+tlist[i].name+'</td>'+
                        '<td>'+pOutHtml+'</td>'+
                        '<td>'+
                        '<div class="btn-group">'+
                        '<button type="button" class="btn btn-primary btn-sm updateRole" data-toggle="modal" data-target="#myModal">更新</button>'+
                        '<button type="button" class="btn btn-warning btn-sm deleteRole" data-toggle="modal" data-target="#myModal">删除</button>'+
                        '</div>'+
                        ' </td>'+
                        '</tr>';
            }
            $('#role_list_content div table tbody').html(outHtml);
        }
    </script>
    <script>
        //更新角色
        $(document).on('click','.updateRole',function () {
            let id=$(this).parents('tr').find('td').eq(0).text();
            let name=$(this).parents('tr').find('td').eq(1).text();
            let pList=$(this).parents('tr').find('td').eq(2).text();

            $('.modal-title').html('更新角色');
            let outHtml='<input id="role_id" hidden class="form-control" type="text" name="id" value="'+id+'"/>'+
                    '<input id="role_name" class="form-control" style="margin-bottom: 16px" type="text" name="name" value="'+name+'"/>' +
                    '<label style="margin-bottom: 16px;display:block">更新权限：</label>';
            //获取所有权限
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/getAllPermission",
                type:"get",
                async: false,
                dataType:"json",
                success:function (result) {
                    //结果成功,拼接outHtml
                    let permissions=result.permissions;
                    for(let i=0;i<permissions.length;i++){
                        if(pList.indexOf(permissions[i].name)!==-1){
                            outHtml=outHtml+
                                    '<input checked style="margin:0 16px 8px 8px" class="role_permission" type="checkbox" name="perm[]" value="'+permissions[i].id+'">'+permissions[i].name+'<br>';
                        }
                        else{
                            outHtml=outHtml+
                                    '<input style="margin:0 16px 8px 8px" class="role_permission" type="checkbox" name="perm[]" value="'+permissions[i].id+'">'+permissions[i].name+'<br>';
                        }
                    }
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
            $('.modal-body').html(outHtml);
            let footOutHtml='<button id="update_role_execute" type="button" class="btn btn-primary" data-dismiss="modal">提交</button>'+
                    '<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>';
            $('.modal-footer').html(footOutHtml);
        });
        //执行更新角色
        $(document).on('click','#update_role_execute',function () {
            let id=$('#role_id').val();
            let name=$('#role_name').val();
            let checked=$('.role_permission:checked');
            let pArray=new Array();
            let i=0;
            $.each(checked,function () {
                pArray[i++]=$(this).val();
            });

            //拼接字符串,对象转json
            let str={'id':id,'pArray':pArray,'name':name};
            let Jstr=JSON.stringify(str);
            //console.log(Jstr);
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/updateRole",
                type:"post",
                contentType:"application/json;charset=utf-8",
                data:Jstr,
                dataType:"json",
                success:function (result) {
                    //结果成功，刷新权限表
                    changeRoleList(result.roles);
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
        });
    </script>
    <script>
        //删除角色
        $(document).on('click','.deleteRole',function () {
            let id=$(this).parents('tr').find('td').eq(0).text();
            let name=$(this).parents('tr').find('td').eq(1).text();

            $('.modal-title').html('删除角色');
            let outHtml='<input  id="role_id" hidden class="form-control" type="text" name="id" value="'+id+'"/>'+
                    '<input disabled id="role_name" class="form-control" style="margin-bottom: 16px" type="text" name="name" value="'+name+'"/>';
            $('.modal-body').html(outHtml);
            let footOutHtml='<button id="delete_role_execute" type="button" class="btn btn-primary" data-dismiss="modal">提交</button>'+
                    '<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>';
            $('.modal-footer').html(footOutHtml);
        });
        //执行删除角色
        $(document).on('click','#delete_role_execute',function () {
            let id=$('#role_id').val();

            //拼接字符串,对象转json
            let str={'id':id};
            let Jstr=JSON.stringify(str);
            //console.log(Jstr);
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/deleteRole",
                type:"post",
                contentType:"application/json;charset=utf-8",
                data:Jstr,
                dataType:"json",
                success:function (result) {
                    //结果成功，刷新权限表
                    changeRoleList(result.roles);
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
        });
    </script>
    <script>
        //更新用户角色关系
        $(document).on('click','.updateUser',function () {
            let id=$(this).parents('tr').find('td').eq(0).text();
            let name=$(this).parents('tr').find('td').eq(1).text();
            let rList=$(this).parents('tr').find('td').eq(2).text();

            $('.modal-title').html('更新用户角色关系');
            let outHtml='<input id="user_id" hidden class="form-control" type="text" name="id" value="'+id+'"/>'+
                    '<input disabled id="user_name" class="form-control" style="margin-bottom: 16px" type="text" name="name" value="'+name+'"/>' +
                    '<label style="margin-bottom: 16px;display:block">更新用户角色关系：</label>';
            //获取所有角色
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/getAllRole",
                type:"get",
                async: false,
                dataType:"json",
                success:function (result) {
                    //结果成功,拼接outHtml
                    let roles=result.roles;
                    for(let i=0;i<roles.length;i++){
                        if(rList.indexOf(roles[i].name)!==-1){
                            outHtml=outHtml+
                                    '<input checked style="margin:0 16px 8px 8px" class="user_role" type="checkbox" name="perm[]" value="'+roles[i].id+'">'+roles[i].name+'<br>';
                        }
                        else{
                            outHtml=outHtml+
                                    '<input style="margin:0 16px 8px 8px" class="user_role" type="checkbox" name="perm[]" value="'+roles[i].id+'">'+roles[i].name+'<br>';
                        }
                    }
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
            $('.modal-body').html(outHtml);
            let footOutHtml='<button id="update_user_execute" type="button" class="btn btn-primary" data-dismiss="modal">提交</button>'+
                    '<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>';
            $('.modal-footer').html(footOutHtml);
        });
        //执行更新用户角色关系
        $(document).on('click','#update_user_execute',function () {
            let id=$('#user_id').val();
            let checked=$('.user_role:checked');
            let rArray=new Array();
            let i=0;
            $.each(checked,function () {
                rArray[i++]=$(this).val();
            });

            //拼接字符串,对象转json
            let str={'id':id,'rArray':rArray};
            let Jstr=JSON.stringify(str);
            //console.log(Jstr);
            //执行ajax
            $.ajax({
                url:"${base.contextPath}/sbiademo/admin/updateUserRole",
                type:"post",
                contentType:"application/json;charset=utf-8",
                data:Jstr,
                dataType:"json",
                success:function (result) {
                    //结果成功，刷新用户角色表
                    changeUserRoleList(result.users);
                },
                error:function () {
                    alert("发送失败！！！");
                }
            });
        });
        //ajax后用于更新用户角色表
        function changeUserRoleList(tlist) {
            let outHtml='';
            for(let i=0;i<tlist.length;i++){
                let rOutHtml='';
                if(tlist[i].rList!==undefined){
                    for(let j=0;j<tlist[i].rList.length;j++){
                        rOutHtml=rOutHtml+tlist[i].rList[j].name;
                        if(j!==tlist[i].rList.length-1){
                            rOutHtml=rOutHtml+',';
                        }
                    }
                }
                outHtml=outHtml+
                        '<tr>'+
                        '<td>'+tlist[i].id+'</td>'+
                        '<td>'+tlist[i].name+'</td>'+
                        '<td>'+rOutHtml+'</td>'+
                        '<td>'+
                        '<div class="btn-group">'+
                        '<button type="button" class="btn btn-primary btn-sm updateUser" data-toggle="modal" data-target="#myModal">更新</button>'+
                        '</div>'+
                        ' </td>'+
                        '</tr>';
            }
            $('#person_list_content div table tbody').html(outHtml);
        }
    </script>
</body>
</html>