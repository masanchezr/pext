<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<!-- Bootstrap Core CSS -->
<link href="<spring:url value="/resources/styles/bootstrap.min.css"/>"
	rel="stylesheet">
<link href="<spring:url value="/resources/styles/sb-admin.css"/>"
	rel="stylesheet">
<link
	href="<spring:url value="/resources/styles/font-awesome-5.0.10/css/fontawesome-all.min.css"/>"
	rel="stylesheet">
<title><spring:message code="titleAdmin" /></title>
</head>
<body class="bg-dark">
	<tiles:insertAttribute name="body" />
</body>
</html>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.0.3.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<spring:url value="/resources/js/popper.min.js"/>" /></script>
<script src="<spring:url value="/resources/js/bootstrap.min.js"/>" /></script>
<script src="<spring:url value="/resources/js/jquery.easing.min.js"/>" /></script>