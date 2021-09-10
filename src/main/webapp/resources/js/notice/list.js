/*list*/
function go_page(no) {
   $('input[name=curPage]').val(no);
   $('#list').submit();
}
$(document).ready(function(){
 $('#list').find('input').val('');
 $('select').find('option:first').attr('selected', 'selected');
});

/*end*/

function necessary() {
   var need=true;
   $('.need').each(function(){
      if($(this).val()==''){
         alert($(this).attr('title')+'을 입력하세요!');
         $(this).focus();
         need=false;
         return need;   //alert창이 한번만 나오도록
      }
   });
   return need;
}