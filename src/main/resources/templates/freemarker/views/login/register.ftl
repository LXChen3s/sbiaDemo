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
<section>
    <div id="login_form">
        <form action="${base.contextPath}/sbiademo/register" method="post">
            <div style="color: red;margin-bottom: 16px;">${error!""}</div>
            <input type="text" class="form-control" name="username" placeholder="用户名"/>
            <input type="password" class="form-control" name="password" placeholder="密码"/>
            <button class="btn btn-primary btn-block" type="submit">注册</button>
        </form>
        <a href="${base.contextPath}/sbiademo/login"><button id="btn_register" class="btn btn-block">返回登录</button></a>
    </div>
    <canvas id="window_back">
    </canvas>
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
    //设置canvas高度，登录表单位置
    function setNavHeight() {
        let window_height=window.innerHeight;
        let window_width=window.innerWidth;
        let login_form=$('#login_form');
        let login_form_width=login_form.css('width');
        let newWidth=parseInt((parseInt(window_width)-parseInt(login_form_width))/2);
        login_form.css('margin-left',newWidth);
        login_form.css('display','block');
        $('#window_back').css('height',window_height);
    }
</script>
<script>
    function drawCanvas() {
        let window_height=window.innerHeight;
        let window_width=window.innerWidth;
        //获取Canvas对象(画布)
        let canvas = document.getElementById("window_back");
        //简单地检测当前浏览器是否支持Canvas对象，以免在一些不支持html5的浏览器中提示语法错误
        if(canvas.getContext){
            //获取对应的CanvasRenderingContext2D对象(画笔)
            let ctx = canvas.getContext("2d");

            //创建新的图片对象
            let img = new Image();
            //指定图片的URL
            img.src = "1.jpg";
            //浏览器加载图片完毕后再绘制图片
            img.onload = function(){
                //以Canvas画布上的坐标(10,10)为起始点，绘制图像
                //图像的宽度和高度分别缩放到350px和100px
                ctx.drawImage(img,0, 0, 4000, 2000, 0, 0, window_width, window_height);
            };
        }
    }
</script>
</body>
</html>