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

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/main/api",
		data : JSON.stringify(message),
		dataType : 'json',
		cache : false,
		timeout : 600000,
		success : function(result) {
			if (result.status == "DONE") {
				createMarkers(result, map);
			} else {
				console.log("ERROR");
			}
		},
		error : function(e) {
			console.log("Error  : ", e);
		}
	});
}

function createMarkers(data, map) {
	var markersNumber = data['data'].length;
	var markerData = data['data'];
	
	for (i = 0; i < markersNumber; i++) {
		var marker = new google.maps.Marker({
			id : markerData[i]['id'],
			position : {
				lat: markerData[i]['latitude'],
				lng: markerData[i]['longitude']
			},
			map : map
		});
		
		message = '<a href="/place/' + markerData[i]['id'] + '">' + markerData[i]['name'] + '</a><br>' + markerData[i]['description'];
		attachMessage(marker, message);
	}
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
		$('html', 'body').animate({ scrollTop: 1000 }, 2000);
	});
});
