function go_page(no) {
	$('input[name=curPage]').val(no);
	$('#list').submit();
}
$(document).ready(function(){
	  $('#list').find('input').val('');
	  $('select').find('option:first').attr('selected', 'selected');
	});