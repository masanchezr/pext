<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="register" />
			</div>
			<!-- /.panel-heading -->
			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="employee" /></th>
								<th><spring:message code="date" /></th>
								<th><spring:message code="inout" /></th>
								<th><spring:message code="ipaddress" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${registers}" var="register">
								<tr>
									<td><c:out value="${register.employee.alias}" /></td>
									<td><fmt:formatDate value="${register.date}" type="time" /></td>
									<td><c:choose>
											<c:when test="${register.outin}">
												<spring:message code="in" />
											</c:when>
											<c:otherwise>
												<spring:message code="out" />
											</c:otherwise>
										</c:choose></td>
									<td><c:out value="${register.ipaddress}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>