<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/editor/detail.css" />
<link rel="stylesheet" href="css/editor/list.css" />
<link rel="stylesheet" href="css/editor/modify.css" />
<link rel="stylesheet" href="css/editor/new.css" />
<link rel="stylesheet" href="css/map/map.css" />
<link rel="stylesheet" href="css/map/storeList.css" />
<link rel="stylesheet" href="css/qna/new.css" />
<link rel="stylesheet" href="css/qna/detail.css" />
<link rel="stylesheet" href="css/qna/reply.css" />
<link rel="stylesheet" href="css/qna/list.css" />
<link rel="stylesheet" href="css/qna/modify.css" />
<link rel="stylesheet" href="css/notice/modify.css" />
<link rel="stylesheet" href="css/notice/list.css" />
<link rel="stylesheet" href="css/notice/detail.css" />
<link rel="stylesheet" href="css/notice/new.css" />
<link rel="stylesheet" href="css/member/member.css" />
<link rel="stylesheet" href="css/mypage/detail.css" />
<link rel="stylesheet" href="css/mypage/modify.css" />
<link rel="stylesheet" href="css/mypage/mypage.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/qna/list.js" defer></script>
<script type="text/javascript" src="js/qna/detail.js" defer></script>
<script type="text/javascript" src="js/qna/modify.js" defer></script>
<script type="text/javascript" src="js/qna/new.js" defer></script>
<script type="text/javascript" src="js/notice/list.js" defer></script>
<script type="text/javascript" src="js/notice/modify.js" defer></script>
<script type="text/javascript" src="js/notice/new.js" defer></script>
<script type="text/javascript" src="js/member/member.js" defer></script>
<script type="text/javascript" src="js/mypage/detail.js"></script>
<script type="text/javascript" src="js/mypage/modify.js"></script>
<script type="text/javascript" src="js/editor/list.js" defer></script>
<script type="text/javascript" src="js/editor/modify.js" defer></script>
<script type="text/javascript" src="js/editor/new.js" defer></script>
<script type="text/javascript" src=""></script>
<style>
body { width: 100%; margin: 0 auto; }
.header__logo{
	background-color: #8C7273;
}

#radius-box11 {
	width: 50px;
	height: 50px;
	/* background-image:url("배경이미지경로"); */
	border-radius: 150px; /* 레이어 반크기만큼 반경을 잡기*/
	display: table-cell;
	vertical-align: middle;
	color: #ffffff;
	font-weight: bold;
	text-align: center;
}

#gouserdetail, .imgoverlay {
	cursor: pointer;
}

#font {
	font-size: 20px;
	
}
.pc {
	width: 100%;
	height: 100%;
	position: relative;
	top: 110px;
}
</style>

<script >
function go_logout() {
	$.ajax({
        url: 'weblogout',
        success: function(){
			location.href='weblogin';
        },error: function(req, text){
           alert( text + ": " +req.status);
        }
     });
}
initData();
function initData(){
      const lat = position.coords.latitude;
      const lng = position.coords.longitude;

      const xhr = new XMLHttpRequest();
      xhr.open('GET','./data?lat='+lat+"&lng="+lng;
      xhr.send();
      xhr.onload = () => {
        if(xhr.status == 200){
            console.log(xhr.response);
            console.log("성공");
        }else{
            console.log("실패");
      }

}
</script>
<div id="main-header" class="header" >
	<div class="header__top">
		<div class="header__button" id="burger-bt">
			<div class="bt">
				<span></span><span></span><span></span><span></span>
			</div>
		</div>
		<div class="header__logo">
			<a onclick="location='home'" id="font" style="color: white;">
				<!-- <img
               			src='https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/logo/logo-atelier-richelieu.svg'
               			alt='Richelieu image' /> -->
               		 Today  CaFe
			</a>
			<c:if test="${!empty login_info.email }">
				<!-- 프로필 정보 단건 이미지 -->
				<table style="float: right; text-align: center; position: absolute; right: 5%; top: 25%;">
					<tr>
							<c:if test="${login_info.admin eq 'n' }">
								<c:if test="${!empty login_info.dbimgpath }">
									<td><img id="radius-box11" alt="없음" src="<c:url value='/'/>${login_info.dbimgpath}" /></td>
									<td valign="top">[${login_info.email}]</td>
								</c:if>
								<c:if test="${empty login_info.dbimgpath }">
									<td><img id="radius-box11" alt="없음" src="img/base.png" /></td>
									<td valign="top">[${login_info.email}]</td>
								</c:if>
							</c:if>
							
							<c:if test="${login_info.admin eq 'naver' }">
								<c:if test="${!empty login_info.dbimgpath }">
									<td><img id="radius-box11" alt="없음" src="${login_info.dbimgpath}" /></td>
									<td valign="top">${login_info.email}</td>
								</c:if>
								<c:if test="${empty login_info.dbimgpath }">
									<td><img id="radius-box11" alt="없음" src="img/base.png" /></td>
									<td valign="top">${login_info.email}</td>
								</c:if>
							</c:if> 
						<td id="gouserdetail">
							<c:if test="${login_info.admin eq 'n'}">
								<a onclick="{location='detail.my?email=${login_info.email}'}">&nbsp;&nbsp;&nbsp; 회원 정보</a>
							</c:if>
						</td>
						<td>
							<a onclick="go_logout()" id="logout">&nbsp;&nbsp;&nbsp;로그아웃</a>
						</td>		
					</tr>

				</table>
			</c:if>
			
			
			<c:if test="${empty login_info}">
         		<a style="float: right; text-align: right; position: absolute; right: 5%; top: 25%" href="weblogin" id="login" >로그인</a> 
      		</c:if>
		</div>
		
		<!-- 검색버튼 눌렀을 때 샥  
		<div class="header_search">
				<form style="float: right; text-align: right; position: absolute; right: 35%; top: 25%" >
  					 <input type="text" id="searchText" placeholder="검색어 입력"  onkeyup="enterkey()"/>
 					 <button type="button"></button>
				</form>
		</div> --> 
	</div>
	<div class="header__menu">
		<nav class="header__nav transversal-nav">
			<div class="transversal-nav__cursor"></div>
			<div class="transversal-nav__background">
				<div class="transversal-nav__cover img-c crop-img">
					<img class="lzld " data-src-medium="./img/cafe/cafe2.png"
						data-src="./img/cafe/c_cafe2.png"
						src="https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/misc/pixel.gif"
						alt="" width="600" height="600">
				</div>
				<div class="transversal-nav__cover img-c crop-img">
					<img class="lzld "
						data-src-medium="https://atelierrichelieu.com/wp-content/uploads/2018/09/le-lieu.jpg"
						data-src="./img/cafe/c_cafe3.png"
						src="https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/misc/pixel.gif"
						alt="" width="600" height="600">
				</div>
				<div class="transversal-nav__cover img-c crop-img">
					<img class="lzld "
						data-src-medium="https://atelierrichelieu.com/wp-content/uploads/2018/09/cover_heritage-768x512.jpg"
						data-src="./img/cafe/c_cafe4.png"
						src="https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/misc/pixel.gif"
						alt="" width="600" height="600">
				</div>
				<div class="transversal-nav__cover img-c crop-img">
					<img class="lzld "
						data-src-medium="https://atelierrichelieu.com/wp-content/uploads/2018/09/actualites.jpg"
						data-src="./img/cafe/cafe9.png"
						src="https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/misc/pixel.gif"
						alt="" width="600" height="600">
				</div>
				<div class="transversal-nav__cover img-c crop-img">
					<img class="lzld "
						data-src-medium="https://atelierrichelieu.com/wp-content/uploads/2018/09/actualites.jpg"
						data-src="./img/cafe/c_cafe8.png"
						src="https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/misc/pixel.gif"
						alt="" width="600" height="600">
				</div>
				<div class="transversal-nav__cover img-c crop-img">
					<img class="lzld "
						data-src-medium="https://atelierrichelieu.com/wp-content/uploads/2018/09/xp2.jpg"
						data-src="./img/cafe/c_cafe6.png"
						src="https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/misc/pixel.gif"
						alt="" width="600" height="600">
				</div>
				<div class="transversal-nav__cover img-c crop-img">
					<img class="lzld "
						data-src-medium="https://atelierrichelieu.com/wp-content/uploads/2018/10/petit-salon_lieu-768x512.jpg"
						data-src="./img/cafe/c_cafe7.png"
						src="https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/misc/pixel.gif"
						alt="" width="600" height="600">
				</div>
				<div class="transversal-nav__cover img-c crop-img">
					<img class="lzld "
						data-src-medium="https://atelierrichelieu.com/wp-content/uploads/2018/09/contact.jpg"
						data-src="./img/cafe/c_cafe5.png"
						src="https://atelierrichelieu.com/wp-content/themes/richelieu/assets/img/misc/pixel.gif"
						alt="" width="600" height="600">
				</div>
			</div>
			<div class="transversal-nav__list-c">
				<ul class="main-list transversal-nav__list">
					
					<li class="transversal-nav__list__li header__nav__link ">
						<a onclick="location='home'"> HOME </a>
					</li>
					
					<li class="transversal-nav__list__li header__nav__link ">
						<a	href="https://atelierrichelieu.com/home/" 
						onclick="location='list.no'"> notice </a>
					</li>
					
					<li class="transversal-nav__list__li header__nav__link ">
						<a href="https://atelierrichelieu.com/le-lieu/" onclick="location='list.er'"> editorReview </a>
					</li>
					
					<li class="transversal-nav__list__li header__nav__link ">
						<a onclick="location='map?email=${login_info.email}'"> Search </a>
					</li> 
					
					<li class="transversal-nav__list__li header__nav__link ">
						<a href="https://atelierrichelieu.com/visite-interactive/" 
						onclick="location='list.qa'"> Q&A </a>
					</li>

					<!-- 로그인 정보 없이 마이페이지 요청시 로그인 페이지로 이동 값이 있으면 마이페이지로 정상 이동 -->
					<c:if test="${!empty login_info.email }">
					<li class="transversal-nav__list__li header__nav__link ">
						<a href="https://atelierrichelieu.com/contact/" 
						onclick="location='mypage?email=${login_info.email}'"> MYPAGE </a>
					</li>
					</c:if>
					<c:if test="${empty login_info.email }">
					<li class="transversal-nav__list__li header__nav__link ">
						<a href="https://atelierrichelieu.com/contact/" 
						onclick="location='weblogin'"> MYPAGE </a>
					</li>
					</c:if>
				</ul>
			</div>
		</nav>
	</div>
</div>
