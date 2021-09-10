function necessary() {
	var need=true;
	$('.need').each(function(){
		if($(this).val()==''){
			alert($(this).attr('title')+'을 입력하세요!');
			$(this).focus();
			need=false;
			return need;	//alert창이 한번만 나오도록
		}
	});
	return need;
}

$(function () {
	if('${!empty vo.filename}'=='true'){
		$('#delete-file').css('display','inline-block');
		$('#file-name').css('padding-right','15px');
	}
});
$(function(){
	$('#attach-file').on('change',function(){
		$('#file-name').html(this.files[0].name);
		$('#file-name').css('padding-right','15px');
		$('#delete-file').css('display','inline-block');
	});
$('#delete-file').on('click',function(){
	$('#attach-file').val('');
	$('#file-name').html('');
	$('#file-name').css('padding-right','0px');
	$('#delete-file').css('display','none');
});
});
$(document).ready(function() {
    $('#byteInfo').on('keyup', function() {
        if($(this).val().length > 3000) {
            $(this).val($(this).val().substring(0, 3000));
            alert('텍스트 글자 수는 최대 3000자로 제한됩니다');
        }
    });
});