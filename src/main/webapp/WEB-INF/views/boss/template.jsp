<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<tiles:insertAttribute name="header" />
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
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
						class="fas fa-sign-out-alt fa-fw"></i> <spring:message
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
						class="fa fa-eur fa-fw"></i> <span class="nav-link-text"><spring:message
								code="recharges" /></span></a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="<spring:message
							code="openmachines" />"><spring:url
						value="/searchopenmachines" var="searchopenmachines" /><a
					href="${searchopenmachines}" class="nav-link"><i
						class="fas fa-lock-open"></i> <span class="nav-link-text"><spring:message
								code="openmachines" /></span></a></li>
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
</body>
</html>