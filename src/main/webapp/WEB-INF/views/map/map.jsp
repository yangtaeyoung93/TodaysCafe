<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Map</title>
<meta name="viewport" content="initial-scale=1.0">
<meta charset="utf-8">
<script type="text/javascript" src="js/map/star.js"/>
<script type="text/javascript" src="js/map/jquery-ui.min.js"/>
<script type="text/javascript" src="js/map/map.js"/>
<script type="text/javascript" src="js/map/storeInfo.js"/>
<script type="text/javascript" src="js/map/markerclusterer.js"/>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=g2ppo5suw4&callback=initMap" defer />
</head>
<body>
	<img id="open" src="./img/left.png" width="50px" height="50px" />
	<div id="search_list">
		<input id="inout1" type="hidden" value="1"> <input type="text"
			id="searchText" onkeyup="enterkey()" />
		<div id="searchbtn">
			<button onclick="search_list()">검색</button>
			<button onclick="option_inout2()">상세검색</button>
		</div>
		<br>
		<div id="option">
			<input id="inout2" type="hidden" value="0"><input id="pet"
				type="checkbox" onclick="optionList()">반려동물<input
				id="parking" type="checkbox" onclick="optionList()">주차장<input
				type="checkbox" onclick="optionList()" id="smoking">흡연
		</div>
		<div>
			<div id="list"></div>
		</div>
	</div>
	<div id="map"></div>
	<div id="popup">
		<div class="storeimg"></div>
		<div id="storereview">
			<header class="review_header" style="padding: 25px;">
				<div class="review_img">
					<div class="imgstore" style="float: left;">
						<canvas id="canvas">
               </canvas>
						<a class="cafeprofile"> </a>
					</div>
					<div class="store">
						<div class="storename"
							style="margin-left: 50px; line-height: 50px;"></div>
						<div class="address_container" style="float: left; width: 100%;">
							<div class="address" style="padding-top: 5px;">
								<img id="locationpng" alt="주소" src="./img/address.png"
									style="float: left;">
								<!-- <div class="store_address">광주광역시 서구 농성동</div> -->
							</div>
						</div>
						<div class="tel_container" style="float: left; width: 100%;">
							<div class="tel">
								<img id="telpng" alt="주소" src="./img/tel.png"
									style="float: left;">
								<!-- <div class="store_address">광주광역시 서구 농성동</div> -->
							</div>
						</div>
						<div class="close_container" style="width: 100%;">
							<div class="close">
								<!-- <div class="store_address">광주광역시 서구 농성동</div> -->
							</div>
						</div>
					</div>
				</div>
				<div></div>
			</header>
			<div class="review_list">
				<div class="content">
					<div class="view">
						<div class="comment">
							<ul class="comment">
								<li>가나다라마바사 아자카타파하</li>
							</ul>

						</div>
					</div>
				</div>
				<section id="bookmark" style="width: 100%; height: 30%">
					<img alt="" src="./img/bookmark-white.png" width="30px"
						height="30px;">
				</section>
				<section id="review_comment">
					<div>
						<form action="">
							<textarea class="text" placeholder="리뷰를 작성해 주세요"
								autocomplete="off" style="height: 30px;">
                     
                     </textarea>
							<button class="send" disabled type="submit">게시</button>
						</form>
					</div>
				</section>
			</div>
		</div>
	</div>
	<div id="popup-background"
		onclick="$('#popup, #popup-background').css('display','none')">
	</div>
</body>

</html>