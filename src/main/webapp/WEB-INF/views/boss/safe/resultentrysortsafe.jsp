<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-8">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="summary" />
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="date" /></th>
								<th><spring:message code="amount" /></th>
								<th><spring:message code="total" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${resultsearch}" var="result">
								<tr>
									<td><c:out value="${result.creationdate}" /></td>
									<td><c:out value="${result.amount}" /><i class="fa fa-euro"></i></td>
									<td><c:out value="${result.total}" /><i class="fa fa-euro"></i></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>