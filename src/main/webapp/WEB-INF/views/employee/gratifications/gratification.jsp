<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<form:form action="savegratification" commandName="gratification"
	role="form">
	<div class="row">
		<div class="col-lg-8">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="gratification" />
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<spring:message code="number" />
								<form:input class="form-control" path="idgratification" />
								<div class="form-group has-error">
									<label class="control-label" for="inputSuccess"><form:errors
											path="idgratification" /></label>
								</div>
							</div>
							<div class="form-group">
								<spring:message code="machine" />
								<form:select class="form-control" path="machine.idmachine">
									<form:options items="${machines}" itemValue="idmachine"
										itemLabel="name" />
								</form:select>
							</div>
							<div class="form-group">
								<form:button class="btn btn-success" value="submit">
									<spring:message code="save" />
								</form:button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>