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