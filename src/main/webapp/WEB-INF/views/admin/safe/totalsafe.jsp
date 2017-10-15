<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="row">
	<div class="col-xl-3 col-sm-6 mb-3">
		<div class="card text-white bg-primary o-hidden">
			<div class="card-body">
				<div class="card-body-icon">
					<i class="fa fa-fw fa-euro"></i>
				</div>
				<div class="mr-5">
					<spring:message code="safe" />
					<c:out value="${totalamount}" />
				</div>
			</div>
		</div>
	</div>
</div>