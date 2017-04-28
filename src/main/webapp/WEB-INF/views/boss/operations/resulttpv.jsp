<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="operationstpv" />
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
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${operations}" var="recharge">
								<tr>
									<td><fmt:formatDate value="${recharge.creationdate}"
											type="both" dateStyle="short" timeStyle="short" /></td>
									<td><c:out value="${recharge.amount}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<spring:message code="totalamount" />
							<c:out value="${amount}" />
							<i class="fa fa-euro"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>