/*new*/
$(function(){
    $('#putinfile').on('change',function(){
      $('#delete-file').css('display','inline-block');
   });
$('#delete-file').on('click',function(){
   $('#putinfile').val('');
   $('#delete-file').css('display','none');
});
});

function necessary() {
   var need=true;
   $('.need').each(function(){
      if($(this).val()==''){
         alert($(this).attr('title')+'을 입력하세요!');
         $(this).focus();
         need=false;
         return need;
      }
   });
   return need;
}

$(document).ready(function() {
    $('#byteInfo').on('keyup', function() {
        if($(this).val().length > 3000) {
            $(this).val($(this).val().substring(0, 3000));
            alert('텍스트 글자 수는 최대 3000자로 제한됩니다');
        }
    });
});
/*end*/