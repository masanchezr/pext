<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="gratifications" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="gratification" /></li>
</ol>
<form:form action="savegratification" commandName="gratification"
	role="form">
	<div class="row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<spring:message code="number" />
							<form:input class="form-control" path="idgratification" />
							<p class="text-danger">
								<form:errors path="idgratification" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="machine" />
							<form:select class="form-control" path="machine.idmachine">
								<form:options items="${machines}" itemValue="idmachine"
									itemLabel="name" />
							</form:select>
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