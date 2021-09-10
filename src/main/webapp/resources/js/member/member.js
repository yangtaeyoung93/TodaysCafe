
   var c_succ="n";
   var p_succ = "n";
   var pc_succ = "n";
   var e_succ = "n";

   function fnCheck() {
      if (p_succ.match("y")   && pc_succ.match("y") && e_succ.match("y")) {
         if(c_succ.match("y")){
            return true;
         }else{
            alert("이용약관을 확인해주세요.");
            return false;
         }
      } else {
         alert("회원정보를 전부 입력해주세요");
         return false;
      }
   }

   $(document).ready( function() {

  //이메일 값 받아서
$('#email_join').blur(
function() {
 var email = $("#email_join").val();
 var space = /\s/g;
 var reg = /^[_\.0-9a-zA-Z-]+@([0-9a-zA-Z][0-9a-zA-Z-]+\.)+[a-zA-Z]{2,6}$/i;
 if (email === ''){
    return $("#email_status").show().html('이메일을 입력해주세요.');
 }else if (email.match(space)){
    return $("#email_status").show().html('메일에 공백이 포함되어 있습니다.');
 }else if (!reg.test(email)){
    return $("#email_status").show().html('이메일 형식이 잘못되어있습니다.');
 }else {
    $.ajax({
             type : 'post',
             url : 'id_check',//컨트롤러 호출
             data : {email : email},
             success : function(data) {
                if (data.match(true)) {
                   $("#email_status")   .show().html('사용가능한 이메일입니다.');
                   return e_succ = "y";
                } else if (data.match(false)) {
                   $("#email_status")   .show().html('아이디가 중복됩니다.');
                }
             },
             error : function(error) {
                alert("서버가 아픕니다 ㅜㅜ");
             }
    });
 }
});

  //비밀번호 유효성 검사
  $('#userpwd_join')
        .focusout(
              function() {
                 var pwd = $('#userpwd_join').val();
                 var space = /\s/g;
                 var reg = /[^a-zA-Z0-9]/g;
                 var upper = /[A-Z]/g, lower = /[a-z]/g, digit = /[0-9]/g;
                 if (pwd === '')
                    return $("#pwd_status").show()
                          .html('비밀번호를 입력해주세요');
                 else if (pwd.match(space))
                    return $("#pwd_status")
                          .show()
                          .html(
                                '비밀번호에 공백이 포함되어있습니다.');
                 else if (reg.test(pwd))
                    return $("#pwd_status")
                          .show()
                          .html(
                                '영문 대/소문자, 숫자만 가능합니다.');
                 else if (pwd.length < 8)
                    return $("#pwd_status").show()
                          .html('8글자 이상 입력해주세요');
                 else if (pwd.length > 30)
                    return $("#pwd_status").show()
                          .html('30글자 이하로 입력해주세요');
                 else {
                    $("#pwd_status").show().html(
                          '사용가능한 비밀번호입니다.');
                    return p_succ = "y";
                 }
              });

  //비밀번호 일치 확인
  $('#userpwd_ck').focusout(
        function() {
           var pwd = $('#userpwd_join').val();
           var pwd_ck = $('#userpwd_ck').val();
           if (pwd_ck === '')
              return $("#pwd_ck_status").show().html(
                    '비밀번호 확인을 입력해주세요.');
           else if (pwd_ck === pwd) {
              $("#pwd_ck_status").show().html(
                    '비밀번호가 일치합니다.');
              return pc_succ = "y";
           } else
              return $("#pwd_ck_status").show().html(
                    '비밀번호가 일치하지 않습니다.');
        });
  $('.tabs .tab').click(function() {
      if ($(this).hasClass('signin')) {
         $('.tabs .tab').removeClass('active');
         $(this).addClass('active');
         $('.cont').hide();
         $('.signin-cont').show();
      }
      if ($(this).hasClass('signup')) {
         $('.tabs .tab').removeClass('active');
         $(this).addClass('active');
         $('.cont').hide();
         $('.signup-cont').show();
      }
   });

   $('.container .bg').mousemove(
         function(e) {
            var amountMovedX = (e.pageX * -1 / 30);
            var amountMovedY = (e.pageY * -1 / 9);
            $(this).css('background-position',
                  amountMovedX + 'px ' + amountMovedY + 'px');
   });
});


   function showpopup() {
      $("#popup").css("display", "block");
   }
   function popup_close() {
      $("#popup").css("display", "none");
      c_succ="y";
   }

   //로그인
   function go_login(){
	   email= $('#email').val();
      $.ajax({
         type: 'post',
         url: 'login',
         data: { email: email,
               userpwd: $('#userpwd').val() },
         success: function(data){
            if( data=="y" ){
               location.href="home";
            }else if(data=="n"){
               location.href= "joinNotEmail?email="+email;
            }else if(data=="d"){
               alert('아이디 또는 비밀번호가 틀립니다.');
            }
         },
         error: function(req, text){
            alert( text + ": " + req.status );
         }
      });
   }
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