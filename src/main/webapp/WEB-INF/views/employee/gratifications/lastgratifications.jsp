<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="lastgratifications" />
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="number" /></th>
								<th><spring:message code="date" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${gratifications}" var="operation">
								<tr>
									<td><spring:message code="gratification" /> <c:out
											value="${operation.idgratification}" /></td>
									<td><fmt:formatDate value="${operation.creationdate}"
											type="both" dateStyle="short" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>