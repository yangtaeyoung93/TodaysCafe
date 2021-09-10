/*new*/
$(function(){   //선택한 첨부파일 이름을 화면에 표시
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
        }
    });
});
/*end*/