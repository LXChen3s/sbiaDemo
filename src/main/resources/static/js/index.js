// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
Date.prototype.Format = function (fmt) { //author: meizz
    let o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (let k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function navbarchange() {
    let navbar=document.getElementById('index_nav');
    window.onscroll=function () {
        navbar.style.backgroundColor='#171717';
    };
}
/* 后台博客页分页标签 */
function setPageBar(currentPage) {
    let curNum=Number(currentPage);
    let totalNum=Number($('#admin_page_bar_header p span:first-child').text());
    if(curNum===1){
        $('.pagination li:first-child').addClass('disabled');
    }
    else if(curNum===totalNum){
        $('.pagination li:last-child').addClass('disabled');
    }
    $('.page-item').each(function () {
        let tempNum=$(this).find('a').text();
        if(tempNum==='上一页'||tempNum==='下一页'){

        }
        else if(Number(tempNum)===curNum){
            $(this).addClass('active');
        }
        else if(Number(tempNum)>totalNum){
            $(this).addClass('disabled');
        }
    });
}
function changePageBar(currentPage) {
    $('#admin_page_bar_header p span:eq(1)').html(currentPage);
    let outHtml='';
    if(currentPage>5){
        outHtml=outHtml+
            '<li class="page-item"><a class="page-link" href="#">上一页</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+(currentPage-4)+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+(currentPage-3)+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+(currentPage-2)+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+(currentPage-1)+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+currentPage+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+(currentPage+1)+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+(currentPage+2)+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+(currentPage+3)+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">'+(currentPage+4)+'</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">下一页</a></li>';
    }
    else {
        outHtml=outHtml+
            '<li class="page-item"><a class="page-link" href="#">上一页</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">1</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">2</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">3</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">4</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">5</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">6</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">7</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">8</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">9</a></li>'+
            '<li class="page-item"><a class="page-link" href="#">下一页</a></li>';
    }
    $('.pagination').html(outHtml);
}

function getProjectName() {
    //获取路径
    let pathName = window.document.location.pathname;
    //截取，得到项目名称
    let projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    console.log(pathName);
    console.log(projectName);
    return projectName;
}