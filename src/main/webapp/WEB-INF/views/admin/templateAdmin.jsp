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
<link href="<spring:url value="/resources/styles/admin/morris.css"/>"
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
<body>
	<c:url value="/admin/j_spring_security_logout" var="logoutUrl" />
	<!-- csrt support -->
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
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
				<a class="navbar-brand" href="index.html"><spring:message
						code="titleAdmin" /></a>
			</div>
			<ul class="nav navbar-top-links navbar-right">
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="javascript:formSubmit()"><i
								class="fa fa-sign-out fa-fw"></i> <spring:message code="logout" /></a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-header -->
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><spring:url value="/admin/daily" var="dailies" /><a
							href="${dailies}"><i class="fa fa-calendar fa-fw"></i> <spring:message
									code="dailies" /></a></li>
						<li><spring:url value="/admin/newentrymoney" var="entrymoney"></spring:url>
							<a href="${entrymoney}"><i class="fa fa-euro fa-fw"></i> <spring:message
									code="entrymoney" /></a></li>
						<li><a href="#"><i class="fa fa-inbox fa-fw"></i> <spring:message
									code="safe" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/admin/newentrysafe"
										var="newentrysafe"></spring:url> <a href="${newentrysafe}"><spring:message
											code="newentrysafe" /></a></li>
								<li><spring:url value="/admin/totalsafe" var="totalsafe"></spring:url>
									<a href="${totalsafe}"><spring:message code="totalsafe" /></a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-euro fa-fw"></i> <spring:message
									code="providing" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/admin/newproviding" var="providing"></spring:url>
									<a href="${providing}"><spring:message
											code="entryproviding" /></a></li>
								<li><spring:url value="/admin/providingtotal"
										var="providingtotal"></spring:url> <a href="${providingtotal}"><spring:message
											code="providingtotal" /></a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-money fa-fw"></i> <spring:message
									code="changemachine" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/admin/newentrymachine"
										var="entrychangemachine"></spring:url> <a
									href="${entrychangemachine}"><spring:message
											code="entrychangemachine" /></a></li>
								<li><spring:url value="/admin/changemachinetotal"
										var="changemachinetotal"></spring:url> <a
									href="${changemachinetotal}"><spring:message
											code="changemachinetotal" /></a></li>
								<li><spring:url value="/admin/luckiaAward"
										var="luckiaAward"></spring:url> <a href="${luckiaAward}"><spring:message
											code="luckiaAward" /></a></li>
								<li><spring:url value="/admin/searchticketsByDay"
										var="ticketsbyday"></spring:url> <a href="${ticketsbyday}"><spring:message
											code="ticketsbyday" /></a></li>
								<li><spring:url value="/admin/resetcm" var="reset"></spring:url>
									<a href="${reset}"><spring:message code="resetmonthend" /></a></li>
							</ul></li>
						<li><spring:url value="/admin/searchregister" var="register"></spring:url>
							<a href="${register}"><i class="fa fa-user fa-fw"></i> <spring:message
									code="register" /></a></li>
						<li><a href="#"><i class="fa fa-calendar fa-fw"></i> <spring:message
									code="schedule" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/admin/newcalendar"
										var="newcalendar" /><a href="${newcalendar}"><spring:message
											code="newschedule" /></a></li>
								<li><spring:url value="/admin/searchschedule"
										var="searchschedule" /><a href="${searchschedule}"><spring:message
											code="searchschedule" /></a></li>
							</ul></li>
						<li><a href="#"><i
								class="fa fa-exclamation-triangle fa-fw"></i> <spring:message
									code="incidents" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/admin/allincidents"
										var="allincidents" /> <a href="${allincidents}"><spring:message
											code="allincidents" /></a></li>
								<li><spring:url value="/admin/pendingissues"
										var="pendingissues" /> <a href="${pendingissues}"><spring:message
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