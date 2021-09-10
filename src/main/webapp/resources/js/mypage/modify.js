/*modify*/
$(function() {
	if('${!empty vo.dbimgpath}'=='true'){
		$('#delete-file').css('display', 'inline-block');
	}else{
		$('#delete-file').css('display','none');
	}
});

$(function() {
	$('#attach-file').on('change', function() {
		$("#radius-box").html(this.files[0].name);
		$('#delete-file').css('display', 'inline-block')
	});
	$('#delete-file').on('click', function() {
		$('#attach-file').val('');
		$("#radius-box").html('');
		$('#delete-file').css('display', 'none')

	});
});
//이미지 파일 교체시 교체한걸로 바꿔줌
$(document).ready(function(){
function readURL(input) {
	if(input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#radius-box').attr('src', reader.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

$("#attach-file").change(function(){
	readURL(this);
});

});
function df() {
	$("#attach").val("y");
	$("#radius-box").attr("src", "img/base.png");
}
/*end*/