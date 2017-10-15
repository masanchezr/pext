<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<form:form action="/admin/resolvedincident" commandName="incident">
	<!-- Breadcrumbs-->
	<ol class="breadcrumb">
		<li class="breadcrumb-item"><a href="#"><spring:message
					code="incidents" /></a></li>
		<li class="breadcrumb-item active"><spring:message
				code="incident" /> <form:label path="idincident" /></li>
	</ol>
	<form:hidden path="idincident" />
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<form:label path="state" />
						</div>
						<div class="form-group">
							<spring:message code="description" />
							<form:textarea path="description" />
							<p class="text-danger">
								<form:errors path="description" />
							</p>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="solve" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>