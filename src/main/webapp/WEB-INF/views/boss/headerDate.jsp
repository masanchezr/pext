<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<head>
<!-- Bootstrap Core CSS -->
<link href="<spring:url value="/resources/styles/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<spring:url value="/resources/styles/font-awesome-5.0.10/css/fontawesome-all.min.css"/>"
	rel="stylesheet" type="text/css">
<link
	href="<spring:url value="/resources/styles/dataTables.bootstrap4.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<spring:url value="/resources/styles/sb-admin.css"/>"
	rel="stylesheet">
<link
	href="<spring:url value="/resources/styles/bootstrap-datepicker.css"/>"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/admin/favicon.png"/>"
	type="image/png">
<title><spring:message code="goldusera" /></title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<spring:url value="/resources/js/popper.min.js"/>"></script>
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- Custom Theme JavaScript -->
<script src="<spring:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script src="<spring:url value="/resources/js/jquery.dataTables.js"/>"></script>
<script
	src="<spring:url value="/resources/js/dataTables.bootstrap4.js"/>"></script>
<script src="<spring:url value="/resources/js/sb-admin.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/sb-admin-datatables.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-datepicker.js"/>"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-datepicker.es.min.js"/>"></script>
<script>
	$(function() {
		$("#sandbox-container input").datepicker({
			language : "es",
			autoclose : true,
			todayHighlight : true
		});
	});
</script>
</head>