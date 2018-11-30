<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<!-- Bootstrap Core CSS -->
<link href="<spring:url value="/resources/styles/bootstrap.min.css"/>"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<spring:url value="/resources/styles/sb-admin.css"/>"
	rel="stylesheet">
<!-- Custom Fonts -->
<link
	href="<spring:url value="/resources/styles/font-awesome-5.0.10/css/fontawesome-all.min.css"/>"
	rel="stylesheet" type="text/css">
<link rel="shortcut icon"
	href="<spring:url value="/resources/img/admin/favicon.png"/>"
	type="image/png">
<title><spring:message code="titleAdmin" /></title>
<!-- html5-->
<meta charset="utf-8" />
<!-- html4-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="bg-dark">
	<tiles:insertAttribute name="body" />
</body>
</html>