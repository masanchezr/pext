<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="operations" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="tpv" /></li>
</ol>
<form:form action="tpv" commandName="tpv">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-6">
						<div class="form-group">
							<spring:message code="id" var="identificator" />
							<form:input class="form-control" path="idtpv"
								placeholder="${identificator}" />
							<p class="text-danger">
								<form:errors path="idtpv" />
							</p>
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
							<form:button class="btn btn-primary" value="submit" id="button">
								<spring:message code="save" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>