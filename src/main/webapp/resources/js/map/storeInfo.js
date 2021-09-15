function search_click(id1, storename, address, tel, lat, lng, close){
     var image = 'http://112.164.58.7/cafe/resources';
     var location = {lat : parseFloat(lat), lng : parseFloat(lng)};
     map = new google.maps.Map(
           document.getElementById('map'), {zoom: 15, center: location});
      var marker = new google.maps.Marker({position: location, map: map, title: name});
      var xy = "'" + id1 + "', '" + storename + "','" + address+ "','" +tel+ "','"+ lat + "','" + lng +"','"  +close+ "'";
      var filepath = '';

      $.ajax({

          url: 'filepath',
            success : function(data){
                $.each(data, function(key, value){
                   if(id1 == value.id){
                      filepath=value.filepath;
                   }

             });
            },error : function(req, text){
              //  alert(":" + req.status);
             }
       });

       if(filepath==''){

            image=''
          filepath = './img/cafe1.png';
       }


       var infowindow = new google.maps.InfoWindow({ content:""});
       google.maps.event.addListener(marker, "click", function() {
          infowindow.setContent('<img id = "img" src="' + image + filepath + '" width = "70px" height="70px "/><div  id ="store" style="height : 70px; width : 250px; margin : 3px;"  onclick = "opendetail('+id1+')"> <div id="store2">&nbsp&nbsp'+storename+'<br>&nbsp&nbsp'+ address+'<br>&nbsp&nbsptel   '+tel+'</div></div>')
         infowindow.open(map,marker);
       //새창띄우기

     });

   }

  $(function(){
     var inout = $('#inout1').val();
     $('#open').click(function(){
         if(inout == '0'){
            inout = 1;
           $('#open').attr('src','./img/left.png');
           $('#search_list').css("display", "none");

        }else if (inout == '1'){
            inout = 0;
            $('#open').attr('src','./img/right.png');
            $('#search_list').css("display", "block");
        }

       })

  });

       function option_inout2(){
         var inout = $('#inout2').val();

         if(inout == '0'){
            $('#inout2').val('1');
            $('#option').css("display", "block");
            $('.option_info').css("visibility", "visible")
        }else if (inout == '1'){
           $('#inout2').val('0');
           $('#option').css("display", "none");
            $('.option_info').css("visibility", "hidden")

        }
  }

     function enterkey() {
         if (window.event.keyCode == 13) {

              // 엔터키가 눌렸을 때 실행할 내용
              search_list();
         }
 }
   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


   function opendetail(id) {
      var img_position=1 ;
      //팝업창 띄우기
      $('#popup, #popup-background').css('display', 'block');
      // 가게 사진 데이터 가져오기
      $.ajax({
         data : {id : id},
         url : '/cafe/info/list',
         type:'get',
         dataType : 'json',
         success : function(data) {
            var image = 'http://112.164.58.217/cafe/resources';
            $('.storeimg').empty();
            $('.cafeprofile').empty();
            $('.iot').empty();
            $.each(data, function(index, value) {
               var cur = index+1;
               $('<div><div class="numbertext" style="position:absolute; top:0;">'+ cur +'/'+ data.length + '</div><img src="' + image + value.filepath +'" width="100%" height="620px;"/></div>').addClass('slides').hide().appendTo('.storeimg');
               $('<img src="'+image + value.filepath +'" width= "50px", height ="50px";" style="border-radius: 47px;")/>').addClass('cafeimg').hide().appendTo('.cafeprofile');
            });
      //슬라이드 효과 주기
            $('.slides').first().addClass('active');
            $('.slides').width('100%');
            $('.slides').first().show();
            $('.cafeimg').first().show();
            $('<a class= "prev" >&#10094; </a>').appendTo('.slides');
            $('<a class="next" >&#10095;</a>').appendTo('.slides');

            $('.next').on("click",function(){
               plusSlides();
            });
            $('.prev').on("click",function(){
               minusSlides();
            })
            },

         error : function(req, text) {
         }
      });



      //가게 정보 가져오기
      $.ajax({
    	  data : {id : id},
          url : '/cafe/info/detail',
          type:'get',
          dataType : 'json',
          success : function(data){
        	  $('.storename').empty();
              $('.address').empty();
              $('.tel').empty();
              $('.close').empty();
              $('.bookmarkbox').empty();
        	  $.each(data, function(index,value){
        		  var storename = value.storename;
        		  var tel = value.tel;
        		  var address = value.address;
        		  var close = value.close;


        		  $('<h1>'+storename+'</h1>').addClass('name').appendTo('.storename');
                  $('<div>' + address + '</div>').addClass('store_address').appendTo('.address');
                  $('<div>' + tel + '</div>').addClass('store_tel').appendTo('.tel');
                  $('<div>휴무일 :' + close + '<div>').addClass('store_close').appendTo('.close');
                  $('<a class="bookmarkimg" title="즐겨찾기" style="float: right; cursor:pointer; margin-right: 20px;" id="bookmarkbtn" onclick="bookmark('
                          +id+","+"'"+storename+"'"+","+"'"+address+"'"+","+tel+')">'
                         +'<img id="abc"src="" width="20px" height="20px" ></a>').appendTo('.bookmarkbox');
        	  });
        	  $('<a style="float: right; margin-right: 10px; cursor: pointer;" id="chat">'
                      +'<img alt="댓글" title="댓글" src="./img/chat.png" width="20px" height="20px"></a>').appendTo('.bookmarkbox');
                     $('<a title="실시간 테이블 보기" class="detailmark" style="float: right; margin-right: 10px; cursor: pointer;" onclick="iot('+id+')"><i class="axi axi-table" style="font-size:23px;"></i></a>').appendTo('.bookmarkbox');
         }
     });

      $('.name').first().show();
      $('.store_address').first().show();
      $('.store_tel').first().show();
      $('.store_close').first().show();
      //리뷰 데이터 가져오기
      $.ajax({
         data : {id : id},
         type:'get',
         url : '/cafe/info/reviews',
         dataType : 'json',
         success : function(data){

            $('.userid_container').empty();
            $('.rating').empty();
            $('.writedate').empty();
            $('.comment').empty();
            $('.container').empty();
            $('.input_section').empty();

            $.each(data, function(index,value){
               var date = value.writedate;
               var writedate = date.substr(0,19);
               var score = value.score;
               var realscore = JSON.stringify(score);
               var asdasd = parseFloat(score);
               var rcomment = value.rcomment;
               var email = value.email
               var reviewid = value.reviewid;
               var id = value.id;
               var dbimgpath = value.dbimgpath;
               var admin = value.admin;
               //email을 userid 형태로 자르기
               var indexof = email.indexOf("@");
               var userid = email.substr(0,indexof);
               var image = 'http://112.164.58.7/cafe/resources';
               // 점수에 따른 사진을 나타내기 위함
                if(score ==5){
                   var point = "./img/5.PNG";
               }else if(score == 4){
                  var point = "./img/4.PNG";
               } else if(score == 3){
                  var point = "./img/3.PNG";
               }else if (score==2){
                  var point = "./img/2.PNG";
               }else if ( score ==1){
                  var point = "./img/1.PNG";
               }else{
                  var point = "./img/0.PNG";
               }

               //var img = '${login_info.admin eq "n" ? image+value.dbimgpath : value.dbimgpath}';
               //프로필이미지를 네이버 로그인일 경우 / 그렇지 않을 경우 구분 하기 위함
               if (admin =="naver") {
                  var pmg = value.dbimgpath;
               }else if(admin == "n"){
                  var pmg = image+value.dbimgpath;
               }
               // db 데이터를 동적인 태그로 나타냄

            $('<div class = "review_list">'
               +'<div style="margin=10px;">'
               +'<div class="profileimg" style="margin-top :10px; float:left;">'
                +'<a><img class="imgs" src="'+pmg+'" width = "40px" height ="40px" style="border-radius:40px;"/></a>'
                +'</div>'
               +'<div class = "userid_container"><div><a class= "userid">'+email +'</a>'
               +'<div class ="rating"><img class="qwe-'+score +'" src="'+point+'"/></div></div></div>'
               +'<div class = "review_container"><div class = "comment">'+rcomment +'</div></div></div>').appendTo('.container');
               $('<div class="writedate">'+writedate +'</div>'
               +'<a style="cursor: pointer; float:right; margin-right:15px; display : none;" class="delete-'+userid+'" onclick="comment_delete('+id+","+reviewid+')">삭제</a></div>').appendTo('.container');
                $('.delete-'+"${login_info.userid}").show();
            });
                // 즐겨찾기 db정보를 가져오는 처리
               $.ajax({

               data:{id1:id,email: "${login_info.email}" },
               url : '/cafe/info/bookmark-list',
               type:'POST',
               success:function(data){
                  var count = JSON.stringify(data);
                  if(count=="1"){
                     $('#abc').attr("src","./img/bookmark-dark.png");
                  }else{
                     $('#abc').attr("src","./img/bookmark-white.png");
                  }
               }
            });
             //(별점,댓글 창)
            $('<div class="insert_div" style="padding: 0 10px 0 50px;">'
                  +'<form  id="insert_form" method="post" name="insert_form">'
                     +'<span class="star-input" id="star-input">'
                        +'<span class="input">'
                            +'<input type="radio" name="star-input" value="1" id="p1">'
                            +'<label for="p1">1</label>'
                            +'<input type="radio" name="star-input" value="2" id="p2">'
                            +'<label for="p2">2</label>'
                            +'<input type="radio" name="star-input" value="3" id="p3">'
                            +'<label for="p3">3</label>'
                            +'<input type="radio" name="star-input" value="4" id="p4">'
                            +'<label for="p4">4</label>'
                            +'<input type="radio" name="star-input" value="5" id="p5">'
                            +'<label for="p5">5</label></span></span>'
                            +'<input type="hidden" id="writerimg" value="${ login_info.dbimgpath}"/>'
                            +'<input type="hidden" id="admin" value="${login_info.admin}"/>'
                            +'<span><textarea class="commentbox" placeholder="댓글을 입력하세요" rows="5" cols="50" style="padding : 0; overflow-x:hidden; width:230px; float : left; overflow-y:auto; height: 5px;"></textarea>'
                           +'</form><a id ="send"  style="float: left; padding-top: 10px; cursor:pointer;"  onclick="comment_insert('+id
                           +')"><i class="axi axi-paper-plane" title="등록하기" style="font-size: 20px; color: #1976D2;" ></i></a></span></div>').appendTo('.input_section');
            $('#chat').first().show();

            $('.detailmark').first().show();

            $('.input_section').css("display","none");

              $('#chat').on("click",function(){

                  $('.input_section').css("display","block");
            });
         }

      });
   }
// 토글 기능을 하는 함수
var idx="true";
function iot(id){
   if(idx == "true"){
   	//true일때 가게 사진이 사라지고, iot 데이터가 나타남
        $('.storeimg').empty();
        $('<div><form id="iotform"><p class="ptag">Date : <input type="text" id="datepicker" /><a onclick="table('+id+')" style="cursor:pointer;"><i class="axi axi-search" title="검색"></i></a><a id="videocam" onclick=video()><i class="axi axi-videocam" title="실시간 가게 상황"></i> </a></p></form></div>').appendTo('.storeimg');
        $( "#datepicker" ).datepicker({dateFormat : 'yy-mm-dd',
                              changeMonth : true,
                              monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                              changeYear: true,
                              nextText: '다음 달',
                              prevText: '이전 달',
                              closeText: '닫기',
                              beforeShowDay: after
        });
        // 그래프가 들어올 div
        $('<div id="Line_Controls_Chart">'
        +'<div id ="lineChartArea"></div>'
        +'<div id="controlsArea" style="display:none;"></div>'
        +'</div>').appendTo('.storeimg');
        // iot 테이블의 총 개수를 가져오는 통신
        $.ajax({
         data:{id1 : id},
         dataType : 'json',
         type : 'POST',
         url:'iotTableSum',
         success:function(data){

         $('<hr/>').addClass('hr').appendTo('.storeimg');
         $('.hr').html('실시간 자리 현황');
         $('<div id="capacity" >총 테이블   : '+data+' 개</div>').appendTo('.storeimg');
         },error:function(){
            // alert('해당 카페는 Iot 서비스에 가입되어있지 않습니다');
         }
        });
        //iot 테이블에서 사용중인 테이블의 데이터를 가져오는 통신
        $.ajax({
          data:{id1 : id},
         dataType : 'json',
         type : 'POST',
         url:'usingtable',
         success:function(data){
            $('<div id = realtime>사용중인 테이블 : '+data+' 개</div>').appendTo('.storeimg');
         },error:function(){
             alert('해당 카페는 Iot 서비스에 가입되어있지 않습니다');
            opendetail(id);
         }
        });
      idx ="false";
     // false일때 iot정보가 사라지고 다시 팝업창을 호출하여 사진을 불러옴
      }else if(idx =="false"){
      idx ="true";//false를 다시 true로 바꿔 토글 기능을 하도록 하기 위함
      $('.storeimg').empty();//iot 내용을 비움
      opendetail(id);// 팝업창을 다시 호출함
   }
}
function after(date){
      if(date > new Date() ) //오늘 이후 날짜 선택 불가
         return [false];
      else
         return [true];
}

 //오른쪽으로 이미지 슬라이드를 하는 함수
var img_position=1 ;
function plusSlides() {
      var imgs = $('.slides');
      var img_count = imgs.length;
      $('.active').removeClass('active').addClass('oldActive');
      if (img_count > img_position){
         if ($('oldActive').is(':last-child')) {
            $('.storeimg').first().addClass('active');
         } else {
            $('.oldActive').next().addClass('active');
         }
         $('.oldActive').removeClass('oldActive');
         $('.slides').hide();
         $('.active').show();
         img_position +=1;
         $('.prev').show();
      }
      if(img_count == img_position){
         $('.next').hide();
      }
   }
//왼쪽으로 이미지 슬라이드를 하는 함수
function minusSlides() {
   var imgs = $('.slides');
   var img_count = imgs.length;
   $('.active').removeClass('active').addClass('oldActive');
   if ( 1 < img_position){
      if ($('oldActive').is(':first-child')) {
         $('.storeimg').last().addClass('active');
      } else {
         $('.oldActive').prev().addClass('active');
      }
      $('.oldActive').removeClass('oldActive');
      $('.slides').hide();
      $('.active').show();
      img_position -=1;
      $('.next').show();
   }
   if(img_position == 1){
      $('.prev').hide();
   }
}
//즐겨찾기를 추가하거나 삭제하는 함수
function bookmark(id,storename, address, tel){
const data{
    id1 : id,
    email:${login_info.email},
    storename:storename,
    address : address,
    tel :tel
  }
   $.ajax({
   data:data,
   url:'/cafe/info/bookmark',
   type:'get',
   success:function(data){
      if(data =="true"){
         alert('즐겨찾기에 추가되었습니다.');
         opendetail(id);
      }else{
         alert('즐겨찾기가 취소되었습니다.');
         opendetail(id);
      }
   }
  });
}

//댓글을 등록하는 함수
function comment_insert(id){
      var formdata = $('#insert_form').serialize();
      var score = formdata.substr(11,11);
      var rcomment = $('.commentbox').val();
      var writerimg = $('#writerimg').val();
      var admin  =$('#admin').val();
   if($('.commentbox').val()==''){
      alert("댓글을 입력하세요");
      $('.commentbox').val('');
      $('.commentbox').focus();
      return;
   }

  const data{
    id : id,
    email:${login_info.email},
    rcomment:rcomment,
    score : score,
    dbimgpath : writerimg,
    admin :admin
  }
   $.ajax({
      data:data,
      type:'POST',
      url:"insert-review",
      success : function(data){
         opendetail(id);

         if(data){
            alert('댓글이 등록되었습니다.');
            $('.commentbox').val('');

         }else{
            alert('실패했습니다.!!');
         }
      },
      error : function(req, text) {
         alert(test + ":" + req.status);
      }

   });
}
//댓글을 삭제하는 함수
function comment_delete(id,reviewid){
   if(confirm("정말 삭제하시겠습니까?")){

      $.ajax({
         data:{ reviewid : reviewid},
         url:'/cafe/info/delete-review',
         type:'DELETE',
         success : function(data){
            opendetail(id);
            if(data){
               alert('댓글이 삭제되었습니다.');
            }else{
               alert('실패했습니다.!!');
            }
         }
      });
   }else{
      alert('취소했습니다.!!');
   }
}