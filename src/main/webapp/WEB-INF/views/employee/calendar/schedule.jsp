<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item active"><spring:message code="schedule" /></li>
</ol>
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
								<c:forEach items="${schedule}" var="s" varStatus="statusdate"
									begin="${statusdate.index+(status.index*7)}"
									end="${statusdate.index+(status.index*7)+6}">
									<td><c:forEach items="${s.employees}" var="employee">
											<c:out value="${employee.alias}" />
										</c:forEach></td>
								</c:forEach>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>