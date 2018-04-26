<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="openmachines" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="openmachinestoday" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty openmachines}">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="employee" /></th>
								<th><spring:message code="machine" /></th>
								<th><spring:message code="date" /></th>
								<th><spring:message code="cause" /></th>
								<th><spring:message code="description" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${openmachines}" var="message">
								<tr>
									<td><c:out value="${message.employee.alias}" /></td>
									<td><c:out value="${message.machine.name}" /></td>
									<td><c:out value="${message.creationdate}" /></td>
									<td><c:out value="${message.cause.cause}" /></td>
									<td><c:out value="${message.description}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
			<c:if test="${empty openmachines}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>