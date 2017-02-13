function formSubmit(url) {
	var form = $("<form>");
	form.attr('style', 'display:none');
	form.attr('method', 'post');
	form.attr('action', url);
	form.submit();
	form.remove();
}
