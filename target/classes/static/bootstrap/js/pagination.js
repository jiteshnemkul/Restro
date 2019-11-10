$(function() {
	onPageSizeChange();
});

function onPageSizeChange() {
	$('#pageSizeSelect').change(function(event) {
		window.location.replace("/?pageSize=" + this.value + "&page=1&search=" + $('#search').val());
	});
}
