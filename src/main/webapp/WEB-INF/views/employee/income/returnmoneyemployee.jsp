<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="income" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="returnmoneyemployee" /></li>
</ol>
<form:form action="savereturn" commandName="incomeForm" role="form">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="amount" var="amount" />
							<form:input class="form-control" path="amount"
								placeholder="${amount}" />
							<p class="text-danger">
								<form:errors path="amount" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="description" var="description" />
							<form:input class="form-control" path="description"
								placeholder="${description}" />
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<form:select class="form-control" path="employee.idemployee">
								<form:options items="${employees}" itemValue="idemployee"
									itemLabel="alias" />
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