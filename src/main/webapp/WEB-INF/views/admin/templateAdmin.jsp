<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<!-- Bootstrap Core CSS -->
<link href="<spring:url value="/resources/styles/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<spring:url value="/resources/styles/font-awesome-4.5.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">
<!-- MetisMenu CSS -->
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
<title><spring:message code="titleAdmin" /></title>
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<c:url value="/admin/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand" href=""><spring:message code="goldusera" /></a>
		<!-- Bot�n para dispositivos m�viles -->
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a href="javascript:formSubmit()"><i
						class="fa fa-sign-out fa-fw"></i> <spring:message code="logout" /></a></li>
			</ul>
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
									code="dailies" />"><spring:url
						value="/admin/daily" var="dailies" /><a href="${dailies}"
					class="nav-link"><i class="fa fa-calendar fa-fw"></i> <span
						class="nav-link-text"><spring:message code="dailies" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="entrymoney" />"><spring:url
						value="/admin/newentrymoney" var="entrymoney"></spring:url> <a
					href="${entrymoney}" class="nav-link"><i
						class="fa fa-euro fa-fw"></i> <span class="nav-link-text"><spring:message
								code="entrymoney" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="safe" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseSafe" data-parent="#exampleAccordion"><i
						class="fa fa-inbox fa-fw"></i> <span class="nav-link-text"><spring:message
								code="safe" /></span> </a>
					<ul id="collapseSafe" class="sidenav-second-level collapse">
						<li><spring:url value="/admin/newentrysafe"
								var="newentrysafe"></spring:url> <a href="${newentrysafe}"><spring:message
									code="newentrysafe" /></a></li>
						<li><spring:url value="/admin/totalsafe" var="totalsafe"></spring:url>
							<a href="${totalsafe}"><spring:message code="totalsafe" /></a></li>
					</ul></li>

				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="providing" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseProviding" data-parent="#exampleAccordion"><i
						class="fa fa-euro fa-fw"></i> <span class="nav-link-text"><spring:message
								code="providing" /></span> </a>
					<ul id="collapseProviding" class="sidenav-second-level collapse">
						<li><spring:url value="/admin/newproviding" var="providing"></spring:url>
							<a href="${providing}"><spring:message code="entryproviding" /></a></li>
						<li><spring:url value="/admin/providingtotal"
								var="providingtotal"></spring:url> <a href="${providingtotal}"><spring:message
									code="providingtotal" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="changemachine" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseChangeMachine" data-parent="#exampleAccordion"><i
						class="fa fa-money fa-fw"></i> <span class="nav-link-text"><spring:message
								code="changemachine" /></span> </a>
					<ul id="collapseChangeMachine"
						class="sidenav-second-level collapse">
						<li><spring:url value="/admin/newentrymachine"
								var="entrychangemachine"></spring:url> <a
							href="${entrychangemachine}"><spring:message
									code="entrychangemachine" /></a></li>
						<li><spring:url value="/admin/changemachinetotal"
								var="changemachinetotal"></spring:url> <a
							href="${changemachinetotal}"><spring:message
									code="changemachinetotal" /></a></li>
						<li><spring:url value="/admin/searchticketsByDay"
								var="ticketsbyday"></spring:url> <a href="${ticketsbyday}"><spring:message
									code="ticketsbyday" /></a></li>
						<li><spring:url value="/admin/resetcm" var="reset"></spring:url>
							<a href="${reset}"><spring:message code="resetmonthend" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="register" />"><spring:url
						value="/admin/searchregister" var="register"></spring:url> <a
					href="${register}" class="nav-link"><i class="fa fa-user fa-fw"></i><span
						class="nav-link-text"> <spring:message code="register" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="schedule" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseschedule" data-parent="#exampleAccordion"><i
						class="fa fa-calendar fa-fw"></i> <span class="nav-link-text"><spring:message
								code="schedule" /></span> </a>
					<ul id="collapseschedule" class="sidenav-second-level collapse">
						<li><spring:url value="/admin/newcalendar" var="newcalendar" /><a
							href="${newcalendar}"><spring:message code="newschedule" /></a></li>
						<li><spring:url value="/admin/searchschedule"
								var="searchschedule" /><a href="${searchschedule}"><spring:message
									code="searchschedule" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="pendingissues" />"><spring:url
						value="/admin/pendingissues" var="pendingissues" /> <a
					href="${pendingissues}" class="nav-link"><i
						class="fa fa-exclamation-triangle fa-fw"></i><span
						class="nav-link-text"> <spring:message code="pendingissues" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="messages"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapsemessages" data-parent="#exampleAccordion"> <i
						class="fa fa-file-text"></i> <span class="nav-link-text"><spring:message
								code="messages" /></span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapsemessages">
						<li><spring:url value="/admin/newmessage" var="newmessage"></spring:url>
							<a href="${newmessage}"><spring:message code="newmessage" /></a></li>
						<li><spring:url value="/admin/messages" var="messages"></spring:url>
							<a href="${messages}"><spring:message code="messages" /></a></li>
					</ul></li>
			</ul>
			<ul class="navbar-nav sidenav-toggler">
				<li class="nav-item"><a class="nav-link text-center"
					id="sidenavToggler"> <i class="fa fa-fw fa-angle-left"></i>
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="content-wrapper">
		<div class="container-fluid">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fa fa-angle-up"></i>
	</a>
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
</body>
</html>