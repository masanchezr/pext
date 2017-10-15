<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="operations" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="newoperation" /></li>
</ol>
<form:form action="saveoperation" commandName="operation">
	<form:hidden path="idoperation" />
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="payment" />
							<form:select class="form-control" path="pay.idpayment"
								id="payments">
								<c:forEach items="${payments}" var="pay">
									<form:option value="${pay.idpayment}" label="${pay.name}"
										style="background-color: ${pay.color}" />
								</c:forEach>
							</form:select>
						</div>
						<div class="form-group">
							<spring:message code="amount" var="amountmessage" />
							<form:input class="form-control" path="amount"
								placeholder="${amountmessage}" />
							<p class="text-danger">
								<form:errors path="amount" />
							</p>
						</div>
						<div class="form-group">
							<spring:message code="description" var="descmessage" />
							<form:input class="form-control" path="description"
								placeholder="${descmessage}" />
							<p class="text-danger">
								<form:errors path="description" />
							</p>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit" id="button">
								<spring:message code="save" />
							</form:button>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="machine" />
							<form:select class="form-control" path="machine.idmachine"
								id="machines">
								<form:options items="${machines}" itemValue="idmachine"
									itemLabel="name" />
							</form:select>
						</div>
						<div class="form-group">
							<spring:message code="award" />
							<form:select class="form-control" path="award.idaward"
								id="awards">
								<c:forEach items="${awards}" var="award">
									<form:option value="${award.idaward}" label="${award.name}"
										style="background-color: ${award.color}" />
								</c:forEach>
							</form:select>
						</div>
						<div class="invisible" id="divemployee">
							<form:select class="form-control" path="employee.idemployee">
								<form:options items="${employees}" itemValue="idemployee"
									itemLabel="alias" />
							</form:select>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>