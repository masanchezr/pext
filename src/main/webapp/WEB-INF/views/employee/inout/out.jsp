<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<spring:message code="employees" />
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form:form method="post" action="resultout" commandName="cb">
							<c:forEach items="${cb.employees}" var="role">
								<form:checkbox path="employees" value="${role}" />
								<c:out value="${role.name}" />
							</c:forEach>
							<form:errors path="employees" />
							<div class="form-group">
								<form:button class="btn btn-success" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>