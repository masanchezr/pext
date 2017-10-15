$('#awards').change(function() {
	var selected = $(this).find("option:selected").val();
	if (selected == 9 || selected == 14 || selected == 15) {
		$('#divemployee').removeClass('invisible');
		$('#divemployee').addClass('form-group');
	} else if (selected == 7) {
		alert("NO OLVIDE GRAPAR TICKET");
		$('#divemployee').removeClass('form-group');
		$('#divemployee').addClass('invisible');
	} else {
		$('#divemployee').removeClass('form-group');
		$('#divemployee').addClass('invisible');
	}
});
$("#button").click(function() {
	$('#button').addClass('invisible');
});