<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="openmachines" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="newopenmachine" /></li>
</ol>
<form:form action="saveopenmachine" modelAttribute="openmachine">
	<div class="row">
		<div class="col-lg-8">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-4">
						<div class="form-group">
							<spring:message code="machine" />
							<form:select class="form-control" path="machine.idmachine"
								id="machines">
								<form:options items="${machines}" itemValue="idmachine"
									itemLabel="name" />
							</form:select>
						</div>
						<div class="form-group">
							<spring:message code="cause" />
							<form:select class="form-control" path="cause.idcause"
								id="causes">
								<c:forEach items="${causes}" var="cause">
									<form:option value="${cause.idcause}" label="${cause.cause}" />
								</c:forEach>
							</form:select>
						</div>
						<div class="form-group">
							<spring:message code="descriptionph" var="description" />
							<form:input class="form-control" placeholder="${description}"
								path="description" />
						</div>
						<div class="form-group">
							<spring:message code="amount" var="amount" />
							<form:input class="form-control" placeholder="${amount}"
								path="amount" />
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit" id="button">
								<spring:message code="save" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>