<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="schedule" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="calendar" /></li>
</ol>
<form:form action="saveschedule" modelAttribute="scheduleForm" role="form">
	<div class="row">
		<div class="col-lg-12">
			<div class="card-body">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th><spring:message code="week" /> <c:out value="${week}" /></th>
								<th><spring:message code="monday" /></th>
								<th><spring:message code="tuesday" /></th>
								<th><spring:message code="wednesday" /></th>
								<th><spring:message code="thursday" /></th>
								<th><spring:message code="friday" /></th>
								<th><spring:message code="saturday" /></th>
								<th><spring:message code="sunday" /></th>
							</tr>
							<tr>
								<td></td>
								<c:forEach items="${dates}" var="date">
									<td><fmt:formatDate value="${date}" type="date" /></td>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${times}" var="time" varStatus="status">
								<tr>
									<td><c:out value="${time.name}" /></td>
									<c:forEach items="${dates}" var="date" varStatus="statusdate">
										<td><form:select class="form-control"
												path="schedule[${statusdate.index+(status.index*7)}].employees[0].idemployee">
												<form:options items="${employees}" itemValue="idemployee"
													itemLabel="alias" />
											</form:select> <form:select class="form-control"
												path="schedule[${statusdate.index+(status.index*7)}].employees[1].idemployee">
												<form:options items="${employees}" itemValue="idemployee"
													itemLabel="alias" />
											</form:select> <form:select class="form-control"
												path="schedule[${statusdate.index+(status.index*7)}].employees[2].idemployee">
												<form:options items="${employees}" itemValue="idemployee"
													itemLabel="alias" />
											</form:select> <form:hidden
												path="schedule[${statusdate.index+(status.index*7)}].time.idtime"
												value="${time.idtime}" /> <form:hidden
												path="schedule[${statusdate.index+(status.index*7)}].dateschedule"
												value="${date}" /></td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="save" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>