function initMap() {
	var center = {
		lat : 50.0379308,
		lng : 22.0023498
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 15,
		center : center,
		minZoom : 4
	});

	ajaxSubmit(map);
}

function ajaxSubmit(map) {
	var message = "getAll";

	var id = window.location.href.split('/')[4];
	console.log(id);

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/main/place/" + id,
		data : JSON.stringify(message),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(data) {
			createMarkers(data, map);
			return data;
		},
		error : function(e) {
			console.log("FALSE : ", data);
			return data;
		}
	});
}

function createMarkers(markerData, map) {
	console.log(markerData);
	var marker = new google.maps.Marker({
		id : markerData['data']['id'],
		position : {
			lat : markerData['data']['latitude'],
			lng : markerData['data']['longitude']
		},
		map : map
	});

	message = markerData['data']['name'] + '<br>' + markerData['data']['description'];
	attachMessage(marker, message);
}

function attachMessage(marker, message) {
	var infoWindow = new google.maps.InfoWindow({
		content : message
	});

	marker.addListener('click', function() {
		infoWindow.open(marker.get('map'), marker);
	});
}

$('#showOpinionForm').click(function() {
	$(this).hide();
	$('#opinionForm').show("slow", function() {
		$('html', 'body').animate({
			scrollTop : 1000
		}, 2000);
	});
});
