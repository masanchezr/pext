<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<h1 class="page-header">
		<spring:message code="accessdenied" />
	</h1>
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<spring:message code="login" />
					</h3>
				</div>
				<div class="panel-body">
					<form role="form" name='f'
						action='<c:url value='jspringsecuritycheck' />' method='POST'>
						<c:if test="${param.error != null}">
							<p>
								<spring:message code="invaliduser" />
							</p>
						</c:if>
						<c:if test="${param.logout != null}">
							<p>
								<spring:message code="sessionout" />
							</p>
						</c:if>
						<fieldset>
							<div class="form-group">
								<input class="form-control" type='text' name='username' value=''
									autofocus />
							</div>
							<div class="form-group">
								<input class="form-control" type='password' name='password' />
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" /> <input
								class="btn btn-lg btn-success btn-block" name="submit"
								type="submit" />
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>