<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="tpv" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="newtpv" /></li>
</ol>
<form:form action="savetpv" modelAttribute="tpv">
	<div class="row">
		<div class="card-body">
			<div class="row">
				<div class="col-lg-6">
					<div class="form-group">
						<spring:message code="num" />
						<form:input class="form-control" path="idtpv" />
						<p class="text-danger">
							<form:errors path="idtpv" />
						</p>
					</div>
					<div class="form-group">
						<spring:message code="amount" />
						<form:input class="form-control" path="amount" />
						<p class="text-danger">
							<form:errors path="amount" />
						</p>
					</div>
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
						<form:button class="btn btn-primary" value="submit" id="button">
							<spring:message code="save" />
						</form:button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>