/**
 * 初始化jqPaginator
 * @param id 控件id
 * @param total 显示多少页
 * @param current 当前页号
 * @param url 执行分页时调用的链接
 */
function initJqPaginator(id,total,current,url) {
	// string to int
	total = parseInt(total);
	current = parseInt(current);
	
	// 防止报错导致不显示分页插件
	if(total == 0) {
		total = 1;
	}
	
	$("#"+id).jqPaginator({
		totalPages : total,
		currentPage : current,
        visiblePages: 5,
        wrapper: '<ul class="pagination"></ul>',
        first: '<li class="first"><a href="javascript:void(0);">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
        next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
        last: '<li class="last"><a href="javascript:void(0);">尾页</a></li>',
        page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
        onPageChange: function (num, type) {
            if (type === "change") {
            	location = url + num;
            }
        }
	});
}

function initJqPaginator4Ajax(id) {
	$("#"+id).jqPaginator({
		totalPages : 1,
		currentPage : 1,
        visiblePages: 5,
        wrapper: '<ul class="pagination"></ul>',
        first: '<li class="first"><a href="javascript:void(0);">首页</a></li>',
        prev: '<li class="prev"><a href="javascript:void(0);">上一页</a></li>',
        next: '<li class="next"><a href="javascript:void(0);">下一页</a></li>',
        last: '<li class="last"><a href="javascript:void(0);">尾页</a></li>',
        page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
        onPageChange: function (num, type) {
            if (type === "change") {
         	   searchReport(num);
            }
        }
    });
}