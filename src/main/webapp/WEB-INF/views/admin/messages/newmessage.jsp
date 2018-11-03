<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="messages" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="newmessage" /></li>
</ol>
<form:form action="savemessage" modelAttribute="message">
	<form:hidden path="idmessage" />
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="description" var="desc" />
							<form:textarea class="form-control" path="message"
								placeholder="${desc}" />
							<p class="text-danger">
								<form:errors path="message" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="active" />
							<form:checkbox path="active" />
						</div>
						<div class="form-group">
							<div class="jt-configuration-demo-example-timepicker-container">
								<spring:message code="datefrom" var="datefrom" />
								<form:input type="text" path="datefrom"
									class="timepicker text-center" placeholder="${datefrom}" />
								<p class="text-danger">
									<form:errors path="datefrom" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<div class="jt-configuration-demo-example-timepicker-container">
								<spring:message code="dateuntil" var="dateuntil" />
								<form:input type="text" path="dateuntil"
									class="timepicker text-center" placeholder="${dateuntil}" />
								<p class="text-danger">
									<form:errors path="dateuntil" />
								</p>
							</div>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="save" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>