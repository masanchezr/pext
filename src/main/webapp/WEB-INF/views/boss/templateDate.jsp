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
	href="<spring:url value="/resources/styles/font-awesome-5.0.10/css/fontawesome-all.min.css"/>"
	rel="stylesheet" type="text/css">
<link
	href="<spring:url value="/resources/styles/font-awesome-5.0.10/css/font-awesome.min.css"/>"
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
</head>
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand" href=""><spring:message code="goldusera" /></a>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a href="javascript:formSubmit()"><i
						class="fa fa-sign-out-alt fa-fw"></i> <spring:message
							code="logout" /></a></li>
			</ul>
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="dailies" />"><spring:url
						value="/daily" var="daily"></spring:url> <a href="${daily}"
					class="nav-link"><i class="fa fa-calendar fa-fw"></i> <span
						class="nav-link-text"><spring:message code="dailies" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message code="operationstpv" />"><spring:url
						value="/searchtpv" var="searchtpv" /> <a href="${searchtpv}"
					class="nav-link"><i class="fa fa-credit-card fa-fw"></i> <span
						class="nav-link-text"><spring:message code="operationstpv" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="summaryexpenses" />"><spring:url
						value="/summaryexpenses" var="summaryexpenses" /><a
					href="${summaryexpenses}" class="nav-link"><i
						class="fa fa-euro-sign fa-fw"></i> <span class="nav-link-text"><spring:message
								code="summaryexpenses" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="summaryincome" />"><spring:url
						value="/summaryincome" var="summaryincome" /><a
					href="${summaryincome}" class="nav-link"><i
						class="fa fa-beer fa-fw"></i><span class="nav-link-text"> <spring:message
								code="summaryincome" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="recharges" />"><spring:url
						value="/searchrecharges" var="searchrecharges" /><a
					href="${searchrecharges}" class="nav-link"><i
						class="fa fa-money-bill-alt fa-fw"></i> <span
						class="nav-link-text"><spring:message code="recharges" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="safe" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseSafe" data-parent="#exampleAccordion"><i
						class="fa fa-inbox fa-fw"></i> <span class="nav-link-text"><spring:message
								code="safe" /></span> </a>
					<ul id="collapseSafe" class="sidenav-second-level collapse">
						<li><spring:url value="/safetotal" var="safetotal" /><a
							href="${safetotal}"><spring:message code="safetotal" /></a></li>
						<li><spring:url value="/searchentrysortsafe"
								var="searchentrysortsafe" /><a href="${searchentrysortsafe}"><spring:message
									code="entrysortsafe" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="register" />"><spring:url
						value="/searchregister" var="register"></spring:url> <a
					href="${register}" class="nav-link"><i
						class="fa fa-address-book fa-fw"></i> <span class="nav-link-text"><spring:message
								code="register" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="users" />"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseUsers" data-parent="#exampleAccordion"><i
						class="fa fa-users fa-fw"></i> <span class="nav-link-text"><spring:message
								code="users" /></span> </a>
					<ul id="collapseUsers" class="sidenav-second-level collapse">
						<li><spring:url value="/enabledisableuser"
								var="enabledisableuser" /> <a href="${enabledisableuser}">
								<spring:message code="updateuser" />
						</a></li>
						<li><spring:url value="/newuser" var="newuser" /> <a
							href="${newuser}"> <spring:message code="newuser" />
						</a></li>
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