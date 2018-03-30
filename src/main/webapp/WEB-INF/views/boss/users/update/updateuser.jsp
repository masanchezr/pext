<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="users" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="updateuser" /></li>
</ol>
<form:form action="update" modelAttribute="user">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="form-group">
					<spring:message code="username" var="username" />
					<form:input class="form-control" path="username"
						placeholder="${username}" />
					<p class="text-danger">
						<form:errors path="username" />
					</p>
				</div>
				<div class="form-group">
					<spring:message code="password" var="password" />
					<form:input class="form-control" path="password"
						placeholder="${password}" />
					<p class="text-danger">
						<form:errors path="password" />
					</p>
				</div>
				<div class="form-group">
					<spring:message code="active" />
					<form:checkbox path="enabled" />
				</div>
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="save" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>