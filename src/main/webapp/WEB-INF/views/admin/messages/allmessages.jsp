<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="messages" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="allmessages" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
			<c:if test="${not empty messages}">
				<div class="table-responsive">
					<form:form action="updatemessage" modelAttribute="message">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th></th>
									<th><spring:message code="message" /></th>
									<th><spring:message code="active" /></th>
									<th><spring:message code="datefrom" /></th>
									<th><spring:message code="dateuntil" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${messages}" var="message">
									<tr>
										<td><form:radiobutton path="idmessage"
												value="${message.idmessage}" /></td>
										<td><c:out value="${message.message}" /></td>
										<td><c:out value="${message.active}" /></td>
										<td><c:out value="${message.datefrom}" /></td>
										<td><c:out value="${message.dateuntil}" /></td>
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
			<c:if test="${empty messages}">
				<spring:message code="noresults" />
			</c:if>
		</div>
	</div>
</div>