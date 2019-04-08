<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="operations" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="operationstpv" /></li>
</ol>
<form:form action="resulttpv" modelAttribute="searchDateForm"
	autocomplete="off">
	<div class="row">
		<div class="card-body">
			<div class="col-lg-6">
				<div class="form-group">
					<spring:message code="selectmonth" var="selectmonth" />
					<div id="sandbox-container">
						<form:input class="form-control" type="text" path="datefrom"
							placeholder="${selectmonth}" />
					</div>
				</div>
				<div class="form-group">
					<form:button class="btn btn-primary" value="submit">
						<spring:message code="search" />
					</form:button>
				</div>
			</div>
		</div>
	</div>
</form:form>