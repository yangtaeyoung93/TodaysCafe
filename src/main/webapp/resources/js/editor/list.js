/* editor/list.jsp */

$(document).ready(function() {
    $('img').hover(function(){
        $(this).find('img').addClass('hover');

    },function(){
       $(this).find('img').removeClass('hover');
    });
});

 $(document).ready(function(){
     $('#list').find('input').val('');
     $('select').find('option:first').attr('selected', 'selected');
   });
   /*end*/

/*ditail*/
$(document).ready(function(){
   var imgs;
    var img_count;
    var img_position = 1;

    imgs = $(".slide ul");
    img_count = imgs.children().length;


    $('#back').click(function () {
      back();
    });
    $('#next').click(function () {
      next();
    });

    function back() {
      if(1<img_position){
        imgs.animate({
          left:'+=650px'
        });
        img_position--;
      }
    }
    function next() {
      if(img_count>img_position){
        imgs.animate({
          left:'-=650px'
        });
        img_position++;
      }
    }
});
/*end*/