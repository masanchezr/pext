$('#awards').change(function() {
	var selected = $(this).find("option:selected").val();
	if (selected == 9 || selected == 14 || selected == 15) {
		$('#divemployee').removeClass('hidden');
		$('#divemployee').addClass('form-group');
	} else if (selected == 7) {
		alert("NO OLVIDE GRAPAR TICKET");
		$('#divemployee').removeClass('form-group');
		$('#divemployee').addClass('hidden');
	} else {
		$('#divemployee').removeClass('form-group');
		$('#divemployee').addClass('hidden');
	}
});
$("#button").click(function() {
	$('#button').addClass('hidden');
});