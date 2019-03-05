<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<!-- Bootstrap Core CSS -->
<link href="<spring:url value="/resources/styles/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
<link
	href="<spring:url value="/resources/styles/dataTables.bootstrap4.min.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<spring:url value="/resources/styles/sb-admin.min.css"/>"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/admin/favicon.png"/>"
	type="image/png">
<title><spring:message code="titleAdmin" /></title>
<!-- html5
<meta charset="utf-8" />-->
<!-- html4
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />-->
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- Custom Theme JavaScript -->
<script src="<spring:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/dataTables.bootstrap4.min.js"/>"></script>
<script src="<spring:url value="/resources/js/sb-admin.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/sb-admin-datatables.min.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.min.js"/>"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>"></script>
<!-- Custom Theme JavaScript -->
<script src="<spring:url value="/resources/js/jquery.easing.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/dataTables.bootstrap4.min.js"/>"></script>
<script src="<spring:url value="/resources/js/sb-admin.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/sb-admin-datatables.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-datepicker.min.js"/>"></script>
<script
	src="<spring:url value="/resources/js/bootstrap-datepicker.es.min.js"/>"></script>
<script type="text/javascript">
	$(function() {
		$('#datetimefrom').datetimepicker({
			format : 'HH:mm:ss'
		});
	});
	$(function() {
		$('#datetimeuntil').datetimepicker({
			format : 'HH:mm:ss'
		});
	});
</script>
</head>