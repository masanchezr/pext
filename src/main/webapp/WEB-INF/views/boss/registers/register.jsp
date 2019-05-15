<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="goldenusera" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="register" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="dni" /></th>
							<th><spring:message code="name" /></th>
							<th><spring:message code="date" /></th>
							<th><spring:message code="timefrom" /></th>
							<th><spring:message code="timeuntil" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${registers}" var="r">
							<tr>
								<td><c:out value="${r.employee.dni}" /></td>
								<td><c:out value="${r.employee.name}" /></td>
								<td><fmt:formatDate value="${r.date}" type="date" /></td>
								<td><fmt:formatDate value="${r.timein}" type="time" /></td>
								<td><fmt:formatDate value="${r.timeout}" type="time" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>