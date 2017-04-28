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

<link rel="shortcut icon"
	href="<spring:url value="/resources/img/admin/favicon.png"/>"
	type="image/png">
<link
	href="<spring:url value="/resources/styles/bootstrap-datepicker.css"/>"
	rel="stylesheet">
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
						<li><a href="javascript:formSubmit()"><i
								class="fa fa-sign-out fa-fw"></i> <spring:message code="logout" /></a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-header -->
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<li><spring:url value="/daily" var="daily"></spring:url> <a
							href="${daily}"><i class="fa fa-calendar fa-fw"></i> <spring:message
									code="daily" /></a></li>
						<li><spring:url value="/searchtpv" var="searchtpv"></spring:url>
							<a href="${searchtpv}"><i class="fa fa-credit-card fa-fw"></i>
								<spring:message code="operationstpv" /></a></li>
						<li><spring:url value="/searchschedule" var="searchschedule" /><a
							href="${searchschedule}"><i class="fa fa-calendar fa-fw"></i>
								<spring:message code="searchschedule" /></a></li>
						<li><spring:url value="/summaryexpenses"
								var="summaryexpenses" /><a href="${summaryexpenses}"><i
								class="fa fa-euro fa-fw"></i> <spring:message
									code="summaryexpenses" /></a></li>
						<li><spring:url value="/summaryincome" var="summaryincome" /><a
							href="${summaryincome}"><i class="fa fa-beer fa-fw"></i> <spring:message
									code="summaryincome" /></a></li>
						<li><spring:url value="/searchrecharges"
								var="searchrecharges" /><a href="${searchrecharges}"><i
								class="fa fa-money fa-fw"></i> <spring:message code="recharges" /></a></li>
						<li><a href="#"><i class="fa fa-inbox fa-fw"></i> <spring:message
									code="safe" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/safetotal" var="safetotal" /><a
									href="${safetotal}"><spring:message code="safetotal" /></a></li>
								<li><spring:url value="/searchentrysortsafe"
										var="searchentrysortsafe" /><a href="${searchentrysortsafe}"><spring:message
											code="entrysortsafe" /></a></li>
							</ul></li>
						<li><spring:url value="/searchregister" var="register"></spring:url>
							<a href="${register}"><i class="fa fa-inbox fa-fw"></i> <spring:message
									code="register" /></a></li>
						<li><a href="#"><i class="fa fa-users fa-fw"></i> <spring:message
									code="users" /><span class="fa arrow"></span> </a>
							<ul class="nav nav-second-level">
								<li><spring:url value="/enabledisableuser"
										var="enabledisableuser" /> <a href="${enabledisableuser}">
										<spring:message code="enabledisable" />
								</a></li>
								<li><spring:url value="/newuser" var="newuser" /> <a
									href="${newuser}"> <spring:message code="newuser" />
								</a></li>
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
	<!-- jQuery Version 1.11.0 -->
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
				format : "mm/yyyy",
				startView : 2,
				minViewMode : 1,
				language : "es",
				autoclose : true
			});
		});
	</script>
</body>
</html>