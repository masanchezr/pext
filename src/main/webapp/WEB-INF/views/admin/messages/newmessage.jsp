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
							<spring:message code="descriptionph" var="desc" />
							<form:textarea class="form-control" path="smessage"
								placeholder="${desc}" />
							<p class="text-danger">
								<form:errors path="smessage" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="active" />
							<form:checkbox path="active" />
						</div>
						<div class="form-group">
							<div class="input-group date" id="datetimefrom"
								data-target-input="nearest">
								<form:input type="text" path="datefrom"
									class="form-control datetimepicker-input"
									data-target="#datetimefrom" />
								<div class="input-group-append" data-target="#datetimefrom"
									data-toggle="datetimepicker">
									<div class="input-group-text">
										<i class="fa fa-clock"></i>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="input-group date" id="datetimeuntil"
								data-target-input="nearest">
								<form:input type="text" path="dateuntil"
									class="form-control datetimepicker-input"
									data-target="#datetimeuntil" />
								<div class="input-group-append" data-target="#datetimeuntil"
									data-toggle="datetimepicker">
									<div class="input-group-text">
										<i class="fa fa-clock"></i>
									</div>
								</div>
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