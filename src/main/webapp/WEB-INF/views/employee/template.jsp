<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<!-- Bootstrap Core CSS -->
<link
	href="<spring:url value="/resources/styles/admin/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- MetisMenu CSS -->
<link
	href="<spring:url value="/resources/styles/admin/metisMenu.min.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link
	href="<spring:url value="/resources/styles/admin/sb-admin-2.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<spring:url value="/resources/styles/admin/font-awesome-4.5.0/css/font-awesome.min.css"/>"
	rel="stylesheet" type="text/css">
<!-- Timeline CSS -->
<link href="<spring:url value="/resources/styles/admin/timeline.css"/>"
	rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="<spring:url value="/resources/styles/admin/morris.css"/>"
	rel="stylesheet">
<link
	href="<spring:url value="/resources/styles/bootstrap-datepicker.css"/>"
	rel="stylesheet">
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/admin/favicon.png"/>"
	type="image/png">
<title><spring:message code="goldusera" /></title>
</head>
<body>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href=""><spring:message code="goldusera" /></a>
			</div>
			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="<c:url value="/employee/logout" />"><i
								class="fa fa-sign-out fa-fw"></i> <spring:message code="logout" /></a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-header -->
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><spring:url value="/employee/newoperation"
								var="operations">
							</spring:url> <a href="${operations}"> <i class="fa fa-euro fa-fw"></i> <spring:message
									code="expenses" /></a></li>
						<li><spring:url value="/employee/newincome" var="income">
							</spring:url> <a href="${income}"> <i class="fa fa-beer fa-fw"></i> <spring:message
									code="income" /></a></li>
						<li><spring:url value="/employee/searchschedule"
								var="searchschedule" /><a href="${searchschedule}"> <i
								class="fa fa-calendar fa-fw"></i> <spring:message
									code="searchschedule" /></a></li>
						<li><spring:url value="/employee/entrymoney"
								var="newentrymoney">
							</spring:url> <a href="${newentrymoney}"> <i class="fa fa-tags fa-fw"></i>
								<spring:message code="newentrymoney" /></a></li>
						<li><spring:url value="/employee/entrymoneychangemachine"
								var="entrymoneychangemachine">
							</spring:url> <a href="${entrymoneychangemachine}"> <i
								class="fa fa-database fa-fw"></i> <spring:message
									code="entrymoneychangemachine" /></a></li>
						<li><spring:url value="/employee/daily" var="daily" /><a
							href="${daily}"> <i class="fa fa-calendar-o fa-fw"></i> <spring:message
									code="daily" /></a></li>
						<li><a href="#"><i
								class="fa fa-exclamation-triangle fa-fw"></i> <spring:message
									code="incidents" /><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/employee/newincident"
										var="newincident"></spring:url> <a href="${newincident}"><spring:message
											code="newincident" /></a></li>
								<li><spring:url value="/employee/myincidents"
										var="myincidents"></spring:url> <a href="${myincidents}"><spring:message
											code="pendingissues" /></a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>
		<div id="page-wrapper">
			<tiles:insertAttribute name="body" />
		</div>
	</div>
	<!-- jQuery -->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-2.0.3.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<spring:url value="/resources/js/admin/bootstrap.min.js"/>"></script>
	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<spring:url value="/resources/js/admin/metisMenu.min.js"/>"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<spring:url value="/resources/js/admin/sb-admin-2.js"/>"></script>
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