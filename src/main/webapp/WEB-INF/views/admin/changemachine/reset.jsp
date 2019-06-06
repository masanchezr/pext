<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="register" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="search" /></li>
</ol>
<form:form action="resetcm" modelAttribute="dateForm" autocomplete="off">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
					<div class="form-group">
					<p><spring:message code=""/></p>
					</div>
						<div class="form-group">
							<spring:message code="date" var="datemessage" />
							<form:input class="form-control" type="text" path="datefrom"
								placeholder="${datemessage}" />
							<p class="text-danger">
								<form:errors path="datefrom" />
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="reset" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>