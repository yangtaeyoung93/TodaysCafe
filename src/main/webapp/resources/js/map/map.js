var map;
	var myLatLng;
	var markers = [];

	window.onload = getLocation;

	function getLocation() {
		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(locationSuccess);
		} else {
			console.log("지오 로케이션 없음")
		}
	};
	// getLocation
	var latitude, longitude;
	function locationSuccess(position) {
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
		initMap(lat,lng);
	}

	function initMap(lat,lng) {

		myLatLng = new naver.maps.LatLng(lat,lng); // 지도에서 가운데로 위치할 위도와 경도
		map = new naver.maps.Map(document.getElementById('map'), {
			center : myLatLng,
			zoom : 15,
			mapTypeId : naver.maps.MapTypeId.ROADMAP
		});
		var size_x = 24; // 마커로 사용할 이미지의 가로 크기
		var size_y = 24; // 마커로 사용할 이미지의 세로 크기

		// 마커로 사용할 이미지 주소
		var image = new naver.maps.MarkerImage('./img/coffee-icon.png',
				new naver.maps.Size(size_x, size_y), '', '',
				new naver.maps.Size(size_x, size_y));

        var markerOptions = {
            position: myLatLng,
            map: map,
            icon: {
                url: './img/coffee-icon.png',
                size: new naver.maps.Size(size_x, size_x),
                origin: new naver.maps.Point(0, 0),
                anchor: new naver.maps.Point(25, 26)
            }
        };
		var marker;

		marker = new naver.maps.Marker({
			position : myLatLng, // 마커가 위치할 위도와 경도(변수)
			map : map,
			title : '내위치' // 마커에 마우스 포인트를 갖다댔을 때 뜨는 타이틀
		});
		var content = "내위치!"; // 말풍선 안에 들어갈 내용

		// 마커를 클릭했을 때의 이벤트. 말풍선 뿅~
		var infowindow = new naver.maps.InfoWindow({
			content : content
		});

		naver.maps.Event.addListener(marker, "click", function() {
			infowindow.open(map, marker);
			//클릭시 상세화면이동
			alert('내위치');
		});

		//시작부터 모든 카페 위치 뜨게끔
		dataList();

	}
	////////////////////////////////////////////////////////////////////////////////////////////////

	function dataList() {
		markers = [];
		map = new naver.maps.Map(document.getElementById('map'), {
			center : myLatLng,
			zoom : 15
		});
		$.ajax({
					url : 'storelist',
					dataType : "json",

					success : function(data) {

						var tag = '';
						var option_smoking = '';
						var option_parking = '';
						var option_pet = '';

						var xy = '';
						$.each(
										data,
										function(key, value) {
											if (value.smoke == ('O')) {
												option_smoking = './img/smoking.png';
											} else {
												option_smoking = './img/smokingx.png';
											}

											if (value.parking == ('O')) {
												option_parking = './img/parking.png';
											} else {
												option_parking = './img/parkingx.png';
											}

											if (value.pet == ('O')) {
												option_pet = './img/pet.png';
											} else {
												option_pet = './img/petx.png';
											}
											xy = "'" + value.id + "', '"
													+ value.storename + "','"
													+ value.address + "','"
													+ value.tel + "','"
													+ value.lat + "','"
													+ value.lng + "','"
													+ value.close + "'";
											tag += '<div id="info" onclick="search_click('
													+ xy
													+ ')">'
													+ '<input type="hidden" name="id" value="'+value.id +'">'
													+ '<div>'
													+ value.storename
													+ '</div>'
													+ '<div>'
													+ value.address
													+ '</div>'
													+ '</div>';
											tag += '<div class= "option_info" id="option_info">'
													+ '<div id="option_smoking"><img src="'+option_smoking+'" width="35px" height="35px"/></div>'
													+ '<div id="option_parking"><img src="'+option_parking+'" width="42px" height="42px"/></div>'
													+ '<div id="option_pet"><img src="'+option_pet+'" width="42px" height="42px"/></div>'
													+ '</div></div><br>';
											var marker = new naver.maps.Marker(
													{
														position : myLatLng,
														map : map
													});
											addMarker(value.id,
													value.storename,
													value.address, value.tel,
													value.lat, value.lng,
													value.close);

											$('#list').html(tag);

										});
						var markerClusterer = new MarkerClusterer(map, markers,
								{
									maxZoom : 16,
									gridSize : 50,
									imagePath : './img/coffee-icon'
								});

					},
					error : function(req, text) {
						alert(text + ":" + req.status);
					}
				});
	}

	function search_list() {
		markers = [];
		$('#list').html('');

		var searchText = $('#searchText').val().trim();
		if (searchText === '') {
			alert('가게명을 입력하세요!')
			$('#searchText').val('');
			$('#searchText').focus();
			return;
		}
		map = new naver.maps.Map(document.getElementById('map'), {
			center : myLatLng,
			zoom : 15
		});

		var marker = new naver.maps.Marker({
			position : myLatLng,
			map : map
		});

		$
				.ajax({
					data : {
						searchText : searchText
					},
					url : 'searchlist',
					success : function(data) {

						if (data.length == 0) {

							$('#list').html(
									'<h3 align="center">검색결과가 없습니다.</h3>');
						}

						var tag = '';
						var option_smoking = '';
						var option_parking = '';
						var option_pet = '';

						var xy = '';
						$
								.each(
										data,
										function(key, value) {
											if (value.smoke == ('O')) {
												option_smoking = './img/smoking.png';
											} else {
												option_smoking = './img/smokingx.png';
											}

											if (value.parking == ('O')) {
												option_parking = './img/parking.png';
											} else {
												option_parking = './img/parkingx.png';
											}

											if (value.pet == ('O')) {
												option_pet = './img/pet.png';
											} else {
												option_pet = './img/petx.png';
											}
											xy = "'" + value.id + "', '"
													+ value.storename + "','"
													+ value.address + "','"
													+ value.tel + "','"
													+ value.lat + "','"
													+ value.lng + "','"
													+ value.close + "'";
											tag += '<div id="info" onclick="search_click('
													+ xy
													+ ')">'
													+ '<input type="hidden" name="id" value="'+value.id +'">'
													+ '<div>'
													+ value.storename
													+ '</div>'
													+ '<div>'
													+ value.address
													+ '</div>'
													+ '</div>';
											tag += '<div class= "option_info" id="option_info">'
													+ '<div id="option_smoking"><img src="'+option_smoking+'" width="35px" height="35px"/></div>'
													+ '<div id="option_parking"><img src="'+option_parking+'" width="42px" height="42px"/></div>'
													+ '<div id="option_pet"><img src="'+option_pet+'" width="42px" height="42px"/></div>'
													+ '</div></div><br>';
											$('#list').html(tag);

											addMarker(value.id,
													value.storename,
													value.address, value.tel,
													value.lat, value.lng,
													value.close);
											$('#inout2').val('0');
											$('#option').css("display", "none");
											$('.option_info').css("visibility",
													"hidden")
										});

						var markerClusterer = new MarkerClusterer(map, markers,
								{
									maxZoom : 16,
									gridSize : 50,
									imagePath : './img/coffee-icon'
								});
					},
					error : function(req, text) {
						alert(test + ":" + req.status);
					}
				});
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	function optionList() {
		var smoking = $('#smoking').is(':checked');
		var pet = $('#pet').is(':checked');
		var parking = $('#parking').is(':checked');
		var searchText = $('#searchText').val();

		if (smoking == false) {
			smoking = 'X'
		} else {
			smoking = 'O'
		}

		if (pet == false) {
			pet = 'X'
		} else {
			pet = 'O'
		}
		if (parking == false) {
			parking = 'X'
		} else {
			parking = 'O'
		}

		markers = [];
		$('#list').html('');
		map = new naver.maps.Map(document.getElementById('map'), {
			center : myLatLng,
			zoom : 15
		});

		var marker = new naver.maps.Marker({
			position : myLatLng,
			map : map
		});

		if (searchText == '') {
			$
					.ajax({

						data : {
							smoking : smoking,
							pet : pet,
							parking : parking
						},
						url : 'optionList',
						success : function(data) {

							var tag = '';
							var option_smoking = '';
							var option_parking = '';
							var option_pet = '';

							var xy = '';
							$
									.each(
											data,
											function(key, value) {
												if (value.smoke == ('O')) {
													option_smoking = './img/smoking.png';
												} else {
													option_smoking = './img/smokingx.png';
												}

												if (value.parking == ('O')) {
													option_parking = './img/parking.png';
												} else {
													option_parking = './img/parkingx.png';
												}

												if (value.pet == ('O')) {
													option_pet = './img/pet.png';
												} else {
													option_pet = './img/petx.png';
												}
												xy = "'" + value.id + "', '"
														+ value.storename
														+ "','" + value.address
														+ "','" + value.tel
														+ "','" + value.lat
														+ "','" + value.lng
														+ "','" + value.close
														+ "'";
												tag += '<div id="info" onclick="search_click('
														+ xy
														+ ')">'
														+ '<input type="hidden" name="id" value="'+value.id +'">'
														+ '<div>'
														+ value.storename
														+ '</div>'
														+ '<div>'
														+ value.address
														+ '</div>' + '</div>';
												tag += '<div class= "option_info" id="option_info" style="visibility:visible">'
														+ '<div id="option_smoking"><img src="'+option_smoking+'" width="35px" height="35px"/></div>'
														+ '<div id="option_parking"><img src="'+option_parking+'" width="42px" height="42px"/></div>'
														+ '<div id="option_pet"><img src="'+option_pet+'" width="42px" height="42px"/></div>'
														+ '</div></div><br>';
												$('#list').html(tag);

												addMarker(value.id,
														value.storename,
														value.address,
														value.tel, value.lat,
														value.lng, value.close);

											});
							$('#list').html(tag);
							var markerClusterer = new MarkerClusterer(map,
									markers, {
										maxZoom : 16,
										gridSize : 50,
										imagePath : './img/coffee-icon'
									});
						},
						error : function(req, text) {
							alert(test + ":" + req.status);
						}
					});

		} else {

			$
					.ajax({

						data : {
							smoking : smoking,
							pet : pet,
							parking : parking,
							searchText : searchText
						},
						url : 'optionList',
						success : function(data) {

							var tag = '';
							var option_smoking = '';
							var option_parking = '';
							var option_pet = '';

							var xy = '';
							$
									.each(
											data,
											function(key, value) {
												if (value.smoke == ('O')) {
													option_smoking = './img/smoking.png';
												} else {
													option_smoking = './img/smokingx.png';
												}

												if (value.parking == ('O')) {
													option_parking = './img/parking.png';
												} else {
													option_parking = './img/parkingx.png';
												}

												if (value.pet == ('O')) {
													option_pet = './img/pet.png';
												} else {
													option_pet = './img/petx.png';
												}
												xy = "'" + value.id + "', '"
														+ value.storename
														+ "','" + value.address
														+ "','" + value.tel
														+ "','" + value.lat
														+ "','" + value.lng
														+ "','" + value.close
														+ "'";
												tag += '<div id="info" onclick="search_click('
														+ xy
														+ ')">'
														+ '<input type="hidden" name="id" value="'+value.id +'">'
														+ '<div>'
														+ value.storename
														+ '</div>'
														+ '<div>'
														+ value.address
														+ '</div>' + '</div>';
												tag += '<div class= "option_info" id="option_info" style="visibility:visible">'
														+ '<div id="option_smoking"><img src="'+option_smoking+'" width="35px" height="35px"/></div>'
														+ '<div id="option_parking"><img src="'+option_parking+'" width="42px" height="42px"/></div>'
														+ '<div id="option_pet"><img src="'+option_pet+'" width="42px" height="42px"/></div>'
														+ '</div></div><br>';
												$('#list').html(tag);

												addMarker(value.id,
														value.storename,
														value.address,
														value.tel, value.lat,
														value.lng, value.close);

											});
							$('#list').html(tag);
							var markerClusterer = new MarkerClusterer(map,
									markers, {
										maxZoom : 16,
										gridSize : 50,
										imagePath : './img/coffee-icon'
									});
						},
						error : function(req, text) {
							alert(test + ":" + req.status);
						}
					});

		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	function smokingList() {
		markers = [];
		$('#list').html('');
		map = new naver.maps.Map(document.getElementById('map'), {
			center : myLatLng,
			zoom : 15
		});

		var marker = new naver.maps.Marker({
			position : myLatLng,
			map : map
		});

		$
				.ajax({
					url : 'smokingList',
					success : function(data) {

						var tag = '';
						var option_smoking = '';
						var option_parking = '';
						var option_pet = '';

						var xy = '';
						$
								.each(
										data,
										function(key, value) {
											if (value.smoke == ('O')) {
												option_smoking = './img/smoking.png';
											} else {
												option_smoking = './img/smokingx.png';
											}

											if (value.parking == ('O')) {
												option_parking = './img/parking.png';
											} else {
												option_parking = './img/parkingx.png';
											}

											if (value.pet == ('O')) {
												option_pet = './img/pet.png';
											} else {
												option_pet = './img/petx.png';
											}
											xy = "'" + value.id + "', '"
													+ value.storename + "','"
													+ value.address + "','"
													+ value.tel + "','"
													+ value.lat + "','"
													+ value.lng + "','"
													+ value.close + "'";
											tag += '<div id="info" onclick="search_click('
													+ xy
													+ ')">'
													+ '<input type="hidden" name="id" value="'+value.id +'">'
													+ '<div>'
													+ value.storename
													+ '</div>'
													+ '<div>'
													+ value.address
													+ '</div>'
													+ '</div>';
											tag += '<div class= "option_info" id="option_info">'
													+ '<div id="option_smoking"><img src="'+option_smoking+'" width="35px" height="35px"/></div>'
													+ '<div id="option_parking"><img src="'+option_parking+'" width="42px" height="42px"/></div>'
													+ '<div id="option_pet"><img src="'+option_pet+'" width="42px" height="42px"/></div>'
													+ '</div></div><br>';
											$('#list').html(tag);

											addMarker(value.id,
													value.storename,
													value.address, value.tel,
													value.lat, value.lng,
													value.close);

										});
						$('#list').html(tag);
						var markerClusterer = new MarkerClusterer(map, markers,
								{
									maxZoom : 16,
									gridSize : 50,
									imagePath : './img/coffee-icon'
								});
					},
					error : function(req, text) {
						alert(test + ":" + req.status);
					}
				});
	}

	function animalList() {
		markers = [];
		$('#list').html('');
		map = new naver.maps.Map(document.getElementById('map'), {
			center : myLatLng,
			zoom : 15
		});

		var marker = new naver.maps.Marker({
			position : myLatLng,
			map : map
		});
		$
				.ajax({
					url : 'animalList',
					success : function(data) {

						var tag = '';
						var option_smoking = '';
						var option_parking = '';
						var option_pet = '';
						var xy = '';
						$
								.each(
										data,
										function(key, value) {

											if (value.smoke == ('O')) {
												option_smoking = './img/smoking.png';
											} else {
												option_smoking = './img/smokingx.png';
											}

											if (value.parking == ('O')) {
												option_parking = './img/parking.png';
											} else {
												option_parking = './img/parkingx.png';
											}

											if (value.pet == ('O')) {
												option_pet = './img/pet.png';
											} else {
												option_pet = './img/petx.png';
											}

											xy = "'" + value.id + "', '"
													+ value.storename + "','"
													+ value.address + "','"
													+ value.tel + "','"
													+ value.lat + "','"
													+ value.lng + "','"
													+ value.close + "'";
											tag += '<div id="info" onclick="search_click('
													+ xy
													+ ')">'
													+ '<input type="hidden" name="id" value="'+value.id +'">'
													+ '<div>'
													+ value.storename
													+ '</div>'
													+ '<div>'
													+ value.address
													+ '</div>'
													+ '</div>';
											tag += '<div class= "option_info" id="option_info">'
													+ '<div id="option_smoking"><img src="'+option_smoking+'" width="35px" height="35px"/></div>'
													+ '<div id="option_parking"><img src="'+option_parking+'" width="42px" height="42px"/></div>'
													+ '<div id="option_pet"><img src="'+option_pet+'" width="42px" height="42px"/></div>'
													+ '</div></div><br>';
											$('#list').html(tag);

											addMarker(value.id,
													value.storename,
													value.address, value.tel,
													value.lat, value.lng,
													value.close);

										});
						var markerClusterer = new MarkerClusterer(map, markers,
								{
									maxZoom : 16,
									gridSize : 50,
									imagePath : './img/coffee-icon'
								});
					},
					error : function(req, text) {
						alert(test + ":" + req.status);
					}
				});
	}

	function parkingList() {
		markers = [];
		$('#list').html('');
		map = new naver.maps.Map(document.getElementById('map'), {
			center : myLatLng,
			zoom : 15
		});

		var marker = new naver.maps.Marker({
			position : myLatLng,
			map : map
		});

		$
				.ajax({
					url : 'parkingList',
					success : function(data) {

						var tag = '';
						var option_smoking = '';
						var option_parking = '';
						var option_pet = '';

						var xy = '';
						$
								.each(
										data,
										function(key, value) {
											if (value.smoke == ('O')) {
												option_smoking = './img/smoking.png';
											} else {
												option_smoking = './img/smokingx.png';
											}

											if (value.parking == ('O')) {
												option_parking = './img/parking.png';
											} else {
												option_parking = './img/parkingx.png';
											}

											if (value.pet == ('O')) {
												option_pet = './img/pet.png';
											} else {
												option_pet = './img/petx.png';
											}
											xy = "'" + value.id + "', '"
													+ value.storename + "','"
													+ value.address + "','"
													+ value.tel + "','"
													+ value.lat + "','"
													+ value.lng + "','"
													+ value.close + "'";
											tag += '<div id="info" onclick="search_click('
													+ xy
													+ ')">'
													+ '<input type="hidden" name="id" value="'+value.id +'">'
													+ '<div>'
													+ value.storename
													+ '</div>'
													+ '<div>'
													+ value.address
													+ '</div>'
													+ '</div>';
											tag += '<div class= "option_info" id="option_info">'
													+ '<div id="option_smoking"><img src="'+option_smoking+'" width="35px" height="35px"/></div>'
													+ '<div id="option_parking"><img src="'+option_parking+'" width="42px" height="42px"/></div>'
													+ '<div id="option_pet"><img src="'+option_pet+'" width="42px" height="42px"/></div>'
													+ '</div></div><br>';
											$('#list').html(tag);

											addMarker(value.id,
													value.storename,
													value.address, value.tel,
													value.lat, value.lng,
													value.close);

										});
						var markerClusterer = new MarkerClusterer(map, markers,
								{
									maxZoom : 16,
									gridSize : 50,
									imagePath : './img/coffee-icon'
								});
					},
					error : function(req, text) {
						alert(test + ":" + req.status);
					}
				});
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	function addMarker(id1, storename, address, tel, lat, lng, close) {
		var image = 'http://192.168.0.31/practice/resources';
		var location = {
			lat : parseFloat(lat),
			lng : parseFloat(lng)
		};
		var filepath = '';

		var xy = "'" + id1 + "', '" + storename + "','" + address + "','" + tel
				+ "','" + lat + "','" + lng + "','" + close + "'";

		var marker = new naver.maps.Marker({
			position : location,
			map : map,
			icon : './img/coffee-icon.png',
			title : storename
		});

		$.ajax({

			url : 'filepath',
			success : function(data) {
				$.each(data, function(key, value) {
					if (id1 == value.id) {
						filepath = value.filepath;
					}

				});
			},
			error : function(req, text) {
				alert(":" + req.status);
			}
		});

		if (filepath == '') {
			image = ''
			filepath = './img/cafe1.png';
		}

		var infowindow = new naver.maps.InfoWindow({
			content : ""
		});
		naver.maps.Event
				.addListener(
						marker,
						"click",
						function() {
							infowindow
									.setContent('<img id = "img" src="' + image + filepath + '" width = "70px" height="70px "/><div  id ="store" style="height : 70px; width : 250px; margin : 3px;"  onclick = "opendetail('
											+ id1
											+ ')"> <div id="store2">&nbsp&nbsp'
											+ storename
											+ '<br>&nbsp&nbsp'
											+ address
											+ '<br>&nbsp&nbsptel   '
											+ tel + '<br></div></div>')
							infowindow.open(map, marker);

						});

		// 마커가 지도 위에 표시되도록 설정
		marker.setMap(map);
		// 생성된 마커를 배열에 추가
		markers.push(marker);

	}

	////////////////////////////////////////////////////////////////////////////지도

	function search_click(id1, storename, address, tel, lat, lng, close) {
		var image = 'http://192.168.0.31/practice/resources';
		var location = {
			lat : parseFloat(lat),
			lng : parseFloat(lng)
		};
		map = new naver.maps.Map(document.getElementById('map'), {
			zoom : 15,
			center : location
		});
		var marker = new naver.maps.Marker({
			position : location,
			map : map,
			title : name
		});
		var xy = "'" + id1 + "', '" + storename + "','" + address + "','" + tel
				+ "','" + lat + "','" + lng + "','" + close + "'";
		var filepath = '';

		$.ajax({

			url : 'filepath',
			success : function(data) {
				$.each(data, function(key, value) {
					if (id1 == value.id) {
						filepath = value.filepath;
					}

				});
			},
			error : function(req, text) {
				alert(":" + req.status);
			}
		});

		if (filepath == '') {

			image = ''
			filepath = './img/cafe1.png';
		}

		var infowindow = new naver.maps.InfoWindow({
			content : ""
		});
		naver.maps.Event
				.addListener(
						marker,
						"click",
						function() {
							infowindow
									.setContent('<img id = "img" src="' + image + filepath + '" width = "70px" height="70px "/><div  id ="store" style="height : 70px; width : 250px; margin : 3px;"  onclick = "opendetail('
											+ id1
											+ ')"> <div id="store2">&nbsp&nbsp'
											+ storename
											+ '<br>&nbsp&nbsp'
											+ address
											+ '<br>&nbsp&nbsptel   '
											+ tel + '<br></div></div>')
							infowindow.open(map, marker);
							//새창띄우기

						});

	}

	$(function() {
		var inout = $('#inout1').val();
		$('#open').click(function() {
			if (inout == '0') {
				inout = 1;
				$('#open').attr('src', './img/left.png');
				$('#search_list').css("display", "none");

			} else if (inout == '1') {
				inout = 0;
				$('#open').attr('src', './img/right.png');
				$('#search_list').css("display", "block");
			}

		})
	})

	function option_inout2() {
		var inout = $('#inout2').val();

		if (inout == '0') {
			$('#inout2').val('1');
			$('#option').css("display", "block");
			$('.option_info').css("visibility", "visible")
		} else if (inout == '1') {
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

		$('#popup, #popup-background').css('display', 'block');

		$
				.ajax({
					data : {
						id : id
					},
					url : 'imgList',
					dataType : 'json',
					success : function(data) {
						var i = '';
						var image = 'http://192.168.0.31/practice/resources';
						$
								.each(
										data,
										function(index, value) {
											$(
													'<div><img src="'
															+ image
															+ value.filepath
															+ '" width="100%" height="700px;"/></div>')
													.addClass('slides')
													.appendTo('.storeimg');
											$(
													'<img src="'
															+ image
															+ value.filepath
															+ '" width= "50px", height ="50px";" style="border-radius: 47px;")/>')
													.addClass('cafeimg').hide()
													.appendTo('.cafeprofile');
										});

						$('.slides').first().addClass('active');
						$('.slides').width('100%');
						$('.slides').first().show();
						$('.cafeimg').first().show();
						$('<a class= "prev" >&#10094; </a>').appendTo(
								'.storeimg');
						$('<a class="next" >&#10095;</a>')
								.appendTo('.storeimg');

						$('.next').on("click", function() {
							plusSlides();
						})
						$('.prev').on("click", function() {
							minusSlides();
						})

					},

					error : function(req, text) {
						alert(test + ":" + req.status);
					}
				});
		$.ajax({
			data : {
				id : id
			},
			url : 'reviewList',
			dataType : 'json',
			success : function(data) {
				var tag = '';
				$.each(data, function(index, value) {
					$('<h1>' + value.storename + '</h1>').addClass('name')
							.hide().appendTo('.storename');
					$('<div>' + value.address + '</div>').addClass(
							'store_address').hide().appendTo('.address');
					$('<div>' + value.tel + '</div>').addClass('store_tel')
							.hide().appendTo('.tel');
					$('<div>' + value.close + '<div>').addClass('store_close')
							.hide().appendTo('.close');
					//$('<ul><li><div><a><img src=".img/cafe7.png" width=30, height=30>')

				});

				$('.name').first().show();
				$('.store_address').first().show();
				$('.store_tel').first().show();
				$('.store_close').first().show();
			}
		});

	}

	var img_position = 1;
	function plusSlides() {

		var imgs = $('.slides');
		var img_count = imgs.length;

		$('.active').removeClass('active').addClass('oldActive');
		if (img_count > img_position) {
			if ($('oldActive').is(':last-child')) {
				$('.storeimg').first().addClass('active');
			} else {
				$('.oldActive').next().addClass('active');
			}
			$('.oldActive').removeClass('oldActive');
			$('.slides').hide();
			$('.active').show();
			img_position += 1;
		}
	}

	function minusSlides() {

		var imgs = $('.slides');
		var img_count = imgs.length;

		$('.active').removeClass('active').addClass('oldActive');
		if (1 < img_position) {
			if ($('oldActive').is(':first-child')) {
				$('.storeimg').last().addClass('active');
			} else {
				$('.oldActive').prev().addClass('active');
			}
			$('.oldActive').removeClass('oldActive');
			$('.slides').hide();
			$('.active').show();
			img_position -= 1;
		}
	}