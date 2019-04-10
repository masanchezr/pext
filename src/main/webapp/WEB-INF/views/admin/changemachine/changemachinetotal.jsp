<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-primary o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-euro-sign"></i>
				</div>
				<div class="mr-5">
					<spring:message code="awards" />
					<c:out value="${awards}" />
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-success o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-euro-sign"></i>
				</div>
				<div class="mr-5">
					<spring:message code="changemachinetotalmonth" />
					<c:out value="${totalmonth}" />
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-danger o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-euro-sign"></i>
				</div>
				<div class="mr-5">
					<spring:message code="visible" />
					<c:out value="${total.visible}" />
				</div>
			</div>
		</div>
	</div>
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-warning o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-euro-sign"></i>
				</div>
				<div class="mr-5">
					<spring:message code="deposit" />
					<c:out value="${total.deposit}" />
				</div>
			</div>
		</div>
	</div>
</div>