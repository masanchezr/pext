<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
	<li class="breadcrumb-item"><a href="#"><fmt:formatDate
				value="${daily.date}" type="date" /></a></li>
	<li class="breadcrumb-item active"><c:out
			value="${daily.numoperations}" /> <spring:message code="operations" /></li>
</ol>
<div class="row">
	<div class="col-lg-12">
		<div class="card-body">
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
							<tr>
								<td><c:if test="${operation.machine!=null}">
										<c:out value="${operation.machine.name}" />
									</c:if> <c:if test="${operation.award!=null}">
										<c:out value="${operation.award.name}" />
									</c:if></td>
								<td><fmt:formatDate value="${operation.creationdate}"
										type="time" /></td>
								<td><spring:url value="/admin/updateoperation"
										var="updateoperation" /><a
									href="${updateoperation}<c:out value="${operation.idoperation}" />"><c:out
											value="${operation.amount}" /><i class="fa fa-euro-sign"></i></a></td>
								<td><c:out value="${operation.pay.name}" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.gratifications}" var="operation">
							<tr>
								<td><spring:message code="gratification" /> <c:out
										value="${operation.idgratification}" /> <c:out
										value="${operation.machine.name}" /></td>
								<td><fmt:formatDate value="${operation.paydate}"
										type="time" /></td>
								<td><c:out value="${operation.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><spring:message code="comun" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.entriesMoney}" var="entryMoney">
							<tr>
								<td><spring:message code="entrymoney" /></td>
								<td><fmt:formatDate value="${entryMoney.creationdate}"
										type="time" /></td>
								<td><c:out value="${entryMoney.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.income}" var="income">
							<tr>
								<td><c:out value="${income.description}" /></td>
								<td><fmt:formatDate value="${income.creationdate}"
										type="time" /></td>
								<td><c:out value="${income.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.returns}" var="income">
							<tr>
								<td><spring:message code="returnmoneyemployee" /> <c:out
										value="${income.employee.alias}" /></td>
								<td><fmt:formatDate value="${income.returndate}"
										type="time" /></td>
								<td><c:out value="${income.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.moneyadvance}" var="money">
							<tr>
								<td><spring:message code="moneyadvance" /> <c:out
										value="${money.employee.alias}" /></td>
								<td><fmt:formatDate value="${money.creationdate}"
										type="time" /></td>
								<td><c:out value="${money.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><spring:message code="comun" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.incomeluckia}" var="income">
							<tr>
								<td><spring:message code="incomeluckia" /></td>
								<td><fmt:formatDate value="${income.creationdate}"
										type="time" /></td>
								<td><c:out value="${income.amount}" /><i
									class="fa fa-euro-sign"></i></td>
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
									class="fa fa-euro-sign"></i></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.tpvs}" var="tpv">
							<tr>
								<td><spring:message code="tpv" /> <c:out
										value="${tpv.idtpv}" /></td>
								<td><fmt:formatDate value="${tpv.creationdate}" type="time" /></td>
								<td><c:out value="${tpv.amount}" /><i
									class="fa fa-euro-sign"></i></td>
								<td><c:out value="${tpv.pay.name}" /></td>
							</tr>
						</c:forEach>
						<c:forEach items="${daily.listchangemachine}" var="lcm">
							<tr>
								<td><c:out value="${lcm.idchangemachine}" /> <c:out
										value="${lcm.machine.name}" /></td>
								<td><fmt:formatDate value="${lcm.creationdate}" type="time" /></td>
								<td><spring:url value="/admin/updatechangemachine"
										var="updatechangemachine" /><a
									href="${updatechangemachine}<c:out value="${lcm.idchangemachine}" />"><c:out
											value="${lcm.amount}" /><i class="fa fa-euro-sign"></i></a></td>
								<td><spring:message code="changemachine" /></td>
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
						<i class="fa fa-euro-sign"></i>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6">
					<div class="form-group">
						<spring:url value="/admin/beforeday" var="beforeday"></spring:url>
						<a
							href="${beforeday}<fmt:formatDate value="${datedaily}" pattern="yyyyMMdd" />"><button
								class="btn btn-primary" type="button">
								<i class="fa fa-arrow-circle-left"></i>
								<spring:message code="beforeday" />
							</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>