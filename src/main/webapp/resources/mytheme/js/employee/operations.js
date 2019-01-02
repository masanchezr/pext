$('#awards').change(function() {
	var selected = $(this).find("option:selected").val();
	if (selected == 9 || selected == 14 || selected == 15) {
		$('#divemployee').removeClass('invisible');
		$('#divemployee').addClass('form-group');
		$('#textstaple').removeClass('form-group');
		$('#textstaple').addClass('invisible');
	} else if (selected == 7) {
		$('#divemployee').removeClass('form-group');
		$('#divemployee').addClass('invisible');
		$('#textstaple').removeClass('invisible');
		$('#textstaple').addClass('form-group');
	} else {
		$('#divemployee').removeClass('form-group');
		$('#divemployee').addClass('invisible');
		$('#textstaple').removeClass('form-group');
		$('#textstaple').addClass('invisible');
	}
});
$("#button").click(function() {
	$('#button').addClass('invisible');
});