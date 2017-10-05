function initMap() {

	var marker;
	var allowCreateMarker = true;

	var center = {
		lat : 50.0379308,
		lng : 22.0023498
	};
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 15,
		center : center,
		minZoom : 4
	});

	google.maps.event.addListener(map, 'click', function(event) {
		if (allowCreateMarker) {
			marker = placeMarker(event.latLng, map);
			google.maps.event.addListener(marker, 'dragend', function(event) {
				$('#longitude').val(event.latLng.lng().toFixed(6));
				$('#latitude').val(event.latLng.lat().toFixed(6));
			});
			allowCreateMarker = false;
		}
	});
}

function placeMarker(location, map) {
	$('#longitude').val(location.lng().toFixed(6));
	$('#latitude').val(location.lat().toFixed(6));
	
	return new google.maps.Marker({
		position : location,
		map : map,
		draggable : true,
		animation: google.maps.Animation.BOUNCE
	});
}
