<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="income" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="returnmoneyemployee" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty moneyadvance}">
				<div class="table-responsive">
					<form:form action="savereturn" modelAttribute="incomeForm">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="amount" /></th>
									<th><spring:message code="date" /></th>
									<th><spring:message code="description" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${moneyadvance}" var="money">
									<tr>
										<td><form:radiobutton path="idreturnmoneyemployee"
												value="${money.idreturnmoneyemployee}" /></td>
										<td><c:out value="${money.amount}" /></td>
										<td><c:out value="${money.creationdate}" /></td>
										<td><c:out value="${money.description}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<form:button class="btn btn-primary" value="submit">
							<spring:message code="returnmoney" />
						</form:button>
					</form:form>
				</div>
			</c:if>
			<c:if test="${empty moneyadvance}">
				<spring:message code="nomoneyadvance" />
			</c:if>
		</div>
	</div>
</div>