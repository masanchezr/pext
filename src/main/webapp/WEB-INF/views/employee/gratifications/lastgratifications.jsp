<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="gratifications" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="lastgratifications" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<div class="table-responsive">
				<table class="table table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th><spring:message code="number" /></th>
							<th><spring:message code="date" /></th>
							<th><spring:message code="client" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${gratifications}" var="operation">
							<tr>
								<td><spring:message code="gratification" /> <c:out
										value="${operation.idgratification}" /></td>
								<td><fmt:formatDate value="${operation.creationdate}"
										type="both" dateStyle="short" /></td>
								<td><spring:message code="client" /> <c:out
										value="${operation.client}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>