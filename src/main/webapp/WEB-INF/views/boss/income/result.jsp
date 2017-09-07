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
								<th><spring:message code="income" /></th>
								<th><spring:message code="amount" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><spring:message code="bardrinks" /></td>
								<td><c:out value="${bardrinks}" /><i class="fa fa-euro"></i></td>
							</tr>
							<tr>
								<td><spring:message code="incomeluckia" /></td>
								<td><c:out value="${luckia}" /><i class="fa fa-euro"></i></td>
							</tr>
							<tr>
								<td><spring:message code="incomemachine" /></td>
								<td><c:out value="${incomemachines}" /><i
									class="fa fa-euro"></i></td>
							</tr>
							<tr>
								<td><spring:message code="returnmoneyemployee" /></td>
								<td><c:out value="${returns}" /><i class="fa fa-euro"></i></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
					<spring:message code="total" />
					<c:out value="${total}" />
					<i class="fa fa-euro"></i>
				</div>
			</div>
		</div>
	</div>
</div>