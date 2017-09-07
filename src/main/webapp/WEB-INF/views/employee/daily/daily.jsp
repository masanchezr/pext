<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading"></div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-bordered table-hover"
						id="dataTables-example">
						<thead>
							<tr>
								<th><spring:message code="concept" /></th>
								<th><spring:message code="date" /></th>
								<th><spring:message code="amount" /></th>
								<th><spring:message code="payment" /></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${daily.operations}" var="operation">
								<c:choose>
									<c:when test="${operation.pay.idpayment==2}">
										<tr class="success">
									</c:when>
									<c:otherwise>
										<tr>
									</c:otherwise>
								</c:choose>
								<td><c:if test="${operation.machine!=null}">
										<c:out value="${operation.machine.name}" />
									</c:if> <c:if test="${operation.award!=null}">
										<c:out value="${operation.award.name}" />
									</c:if></td>
								<td><fmt:formatDate value="${operation.creationdate}"
										type="time" /></td>
								<td><c:out value="${operation.amount}" /><i
									class="fa fa-euro"></i></td>
								<td><c:out value="${operation.pay.name}" /></td>
								</tr>
							</c:forEach>
							<c:forEach items="${daily.gratifications}" var="operation">
								<tr>
									<td><spring:message code="gratification" /> <c:out
											value="${operation.machine.name}" /></td>
									<td><fmt:formatDate value="${operation.paydate}"
											type="time" /></td>
									<td>10<i class="fa fa-euro"></i></td>
									<td></td>
								</tr>
							</c:forEach>
							<c:forEach items="${daily.entriesMoney}" var="entryMoney">
								<tr>
									<td><spring:message code="entrymoney" /></td>
									<td><fmt:formatDate value="${entryMoney.creationdate}"
											type="time" /></td>
									<td><c:out value="${entryMoney.amount}" /><i
										class="fa fa-euro"></i></td>
									<td></td>
								</tr>
							</c:forEach>
							<c:forEach items="${daily.income}" var="income">
								<tr>
									<td><c:out value="${income.description}" /></td>
									<td><fmt:formatDate value="${income.creationdate}"
											type="time" /></td>
									<td><c:out value="${income.amount}" /><i
										class="fa fa-euro"></i></td>
									<td></td>
								</tr>
							</c:forEach>
							<c:forEach items="${daily.returns}" var="income">
								<tr>
									<td><spring:message code="returnmoneyemployee" /> <c:out
											value="${income.employee.alias}" /></td>
									<td><fmt:formatDate value="${income.creationdate}"
											type="time" /></td>
									<td><c:out value="${income.amount}" /><i
										class="fa fa-euro"></i></td>
									<td></td>
								</tr>
							</c:forEach>
							<c:forEach items="${daily.incomeluckia}" var="income">
								<tr>
									<td><spring:message code="incomeluckia" /></td>
									<td><fmt:formatDate value="${income.creationdate}"
											type="time" /></td>
									<td><c:out value="${income.amount}" /><i
										class="fa fa-euro"></i></td>
									<td></td>
								</tr>
							</c:forEach>
							<c:forEach items="${daily.incomemachines}" var="income">
								<tr>
									<td><spring:message code="incomemachine" /> <c:out
											value="${income.machine.name}" /></td>
									<td><fmt:formatDate value="${income.creationdate}"
											type="time" /></td>
									<td><c:out value="${income.amount}" /><i
										class="fa fa-euro"></i></td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="form-group">
							<spring:message code="totalamount" />
							<c:out value="${daily.finalamount}" />
							<i class="fa fa-euro"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>