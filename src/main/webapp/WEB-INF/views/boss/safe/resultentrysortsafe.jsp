<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="safe" /></a></li>
	<li class="breadcrumb-item active"><spring:message code="summary" /></li>
</ol>
<div class="row">
	<div class="col-lg-8">
		<div class="card-body">
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
								<td><c:out value="${result.amount}" /><i
									class="fa fa-euro"></i></td>
								<td><c:out value="${result.total}" /><i class="fa fa-euro"></i></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>