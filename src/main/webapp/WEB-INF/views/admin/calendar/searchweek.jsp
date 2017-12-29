<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="schedule" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="selectweek" /></li>
</ol>
<form:form action="calendar" modelAttribute="weekForm">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="week" var="weekmessage" />
							<div class="input-append date">
								<form:input type="week" path="week" placeholder="${weekmessage}" />
							</div>
							<p class="text-danger">
								<form:errors path="week" />
							</p>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="search" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>