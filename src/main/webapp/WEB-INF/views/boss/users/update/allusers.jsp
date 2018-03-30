<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="users" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="updateuser" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty users}">
				<div class="table-responsive">
					<form:form action="updateuser" modelAttribute="userForm">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="nameclient" /></th>
									<th><spring:message code="password" /></th>
									<th><spring:message code="enabled" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users}" var="jewel">
									<tr>
										<td><form:radiobutton path="username"
												value="${jewel.username}" /></td>
										<td><c:out value="${jewel.username}" /></td>
										<td><c:out value="${jewel.password}" /></td>
										<td><c:out value="${jewel.enabled}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<form:button class="btn btn-primary" value="submit">
							<spring:message code="update" />
						</form:button>
					</form:form>
				</div>
			</c:if>
			<c:if test="${empty users}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>