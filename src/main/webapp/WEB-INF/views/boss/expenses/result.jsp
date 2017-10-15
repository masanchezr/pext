<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="expenses" /></a></li>
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
							<th><spring:message code="award" /></th>
							<th><spring:message code="total" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${expenses}" var="s">
							<tr>
								<td><c:out value="${s.award.name}" /></td>
								<td><c:out value="${s.amount}" /><i class="fa fa-euro"></i></td>
							</tr>
						</c:forEach>
						<tr>
							<td><spring:message code="gratifications" /></td>
							<td><c:out value="${gratifications}" /><i
								class="fa fa-euro"></i></td>
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