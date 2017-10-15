<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="recharges" /></a></li>
</ol>
<div class="row">
	<div class="col-lg-6">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="date" /></th>
							<th><spring:message code="machine" /></th>
							<th><spring:message code="amount" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${recharges}" var="recharge">
							<tr>
								<td><fmt:formatDate value="${recharge.creationdate}"
										type="both" dateStyle="short" timeStyle="short" /></td>
								<td><c:out value="${recharge.machine.name}" /></td>
								<td><c:out value="${recharge.amount}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>