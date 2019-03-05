<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<!-- Bootstrap Core CSS -->
<link href="<spring:url value="/resources/styles/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<spring:url value="/resources/styles/sb-admin.css"/>"
	rel="stylesheet">
<link
	href="<spring:url value="/resources/styles/font-awesome-5.0.10/css/fontawesome-all.min.css"/>"
	rel="stylesheet" type="text/css">
<link
	href="<spring:url value="/resources/styles/dataTables.bootstrap4.min.css"/>"
	rel="stylesheet">
<link
	href="<spring:url value="/resources/styles/bootstrap-datepicker.min.css"/>"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/admin/favicon.png"/>"
	type="image/png">
<title><spring:message code="goldusera" /></title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top"
		id="mainNav">
		<a class="navbar-brand" href=""><spring:message code="goldusera" /></a>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a
					href="<c:url value="/employee/logout" />"><i
						class="fas fa-sign-out-alt fa-fw"></i> <spring:message
							code="logout" /></a></li>
			</ul>
			<ul class="navbar-nav navbar-sidenav" id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="expenses"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapsexpenses" data-parent="#exampleAccordion"><i
						class="fa fa-euro-sign fa-fw"></i> <span class="nav-link-text"><spring:message
								code="expenses" /></span></a>
					<ul class="sidenav-second-level collapse" id="collapsexpenses">
						<li><spring:url value="/employee/newmoneyadvance"
								var="moneyadvance">
							</spring:url> <a href="${moneyadvance}"><spring:message
									code="moneyadvance" /></a></li>
						<li><spring:url value="/employee/newoperation"
								var="operations">
							</spring:url> <a href="${operations}"><spring:message
									code="otheroperations" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="gratifications"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseGratifications" data-parent="#exampleAccordion"><i
						class="fa fa-money-bill-alt fa-fw"></i> <span
						class="nav-link-text"><spring:message code="gratifications" /></span></a>
					<ul class="sidenav-second-level collapse"
						id="collapseGratifications">
						<li><spring:url value="/employee/registernumber"
								var="registernumber">
							</spring:url> <a href="${registernumber}"> <spring:message
									code="registergratification" /></a></li>
						<li><spring:url value="/employee/newgratification"
								var="gratifications">
							</spring:url> <a href="${gratifications}"> <spring:message
									code="paygratification" /></a></li>
						<li><spring:url value="/employee/lastgratifications"
								var="lastgratifications">
							</spring:url> <a href="${lastgratifications}"> <spring:message
									code="lastgratifications" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="income"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseIncome" data-parent="#exampleAccordion"><i
						class="fa fa-beer fa-fw"></i> <span class="nav-link-text"><spring:message
								code="income" /></span></a>
					<ul class="sidenav-second-level collapse" id="collapseIncome">
						<li><spring:url value="/employee/newincome" var="income">
							</spring:url> <a href="${income}"><spring:message code="bardrinks" /></a></li>
						<li><spring:url value="/employee/newincomeluckia"
								var="incomeluckia">
							</spring:url> <a href="${incomeluckia}"><spring:message
									code="incomeluckia" /></a></li>
						<li><spring:url value="/employee/newincomemachine"
								var="incomemachine">
							</spring:url> <a href="${incomemachine}"><spring:message
									code="incomemachine" /></a></li>
						<li><spring:url value="/employee/newreturn" var="return">
							</spring:url> <a href="${return}"><spring:message
									code="returnmoneyemployee" /></a></li>
					</ul></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="openmachine"/>"><spring:url
						value="/employee/newopenmachine" var="newopenmachine" /><a
					href="${newopenmachine}" class="nav-link"> <i
						class="fas fa-lock-open"></i> <span class="nav-link-text"><spring:message
								code="openmachine" /></span></a></li>
				<!--li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="searchschedule" />"><spring:url
						value="/employee/searchschedule" var="searchschedule" /><a
					href="${searchschedule}" class="nav-link"> <i
						class="fa fa-calendar fa-fw"></i> <span class="nav-link-text"><spring:message
								code="searchschedule" /></span></a></li-->
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="newentrymoney" />"><spring:url
						value="/employee/entrymoney" var="newentrymoney">
					</spring:url> <a href="${newentrymoney}" class="nav-link"> <i
						class="fa fa-tags fa-fw"></i> <span class="nav-link-text"><spring:message
								code="newentrymoney" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
								code="entrymoneychangemachine" />"><spring:url
						value="/employee/entrymoneychangemachine"
						var="entrymoneychangemachine">
					</spring:url> <a href="${entrymoneychangemachine}" class="nav-link"> <i
						class="fa fa-database fa-fw"></i> <span class="nav-link-text"><spring:message
								code="entrymoneychangemachine" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="daily"/>"><spring:url
						value="/employee/daily" var="daily" /><a href="${daily}"
					class="nav-link"> <i class="fa fa-calendar-alt fa-fw"></i> <span
						class="nav-link-text"><spring:message code="daily" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="incidents"/>"><a
					class="nav-link nav-link-collapse collapsed" data-toggle="collapse"
					href="#collapseincidents" data-parent="#exampleAccordion"> <i
						class="fa fa-exclamation"></i> <span class="nav-link-text"><spring:message
								code="incidents" /></span>
				</a>
					<ul class="sidenav-second-level collapse" id="collapseincidents">
						<li><spring:url value="/employee/newincident"
								var="newincident"></spring:url> <a href="${newincident}"><spring:message
									code="newincident" /></a></li>
						<li><spring:url value="/employee/myincidents"
								var="myincidents"></spring:url> <a href="${myincidents}"><spring:message
									code="myincidents" /></a></li>
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
		src="<spring:url value="/resources/js/employee/operations.js"/>"></script>
	<script
		src="<spring:url value="/resources/js/bootstrap-datepicker.min.js"/>"></script>
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