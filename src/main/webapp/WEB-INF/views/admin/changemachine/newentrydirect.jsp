<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html;charset=UTF-8"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><spring:message
				code="changemachine" /></a></li>
	<li class="breadcrumb-item active"><spring:message
			code="newentrydirect" /></li>
</ol>
<form:form action="saveentrydirect" modelAttribute="safeForm"
	role="form">
	<div class="row">
		<div class="col-lg-6">
			<div class="card-body">
				<div class="row">
					<div class="col-lg-3">
						<div class="form-group">
							<spring:message code="amount" />
							<form:input class="form-control" path="amount" />
							<p class="text-danger">
								<form:errors path="amount" />
							</p>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary" value="submit">
								<spring:message code="save" />
							</form:button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>