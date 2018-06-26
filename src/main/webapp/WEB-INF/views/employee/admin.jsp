<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-12">
		<h1>
			<c:forEach items="${messages}" var="message">
				<p>
					<c:out value="${message.message}" />
				</p>
			</c:forEach>
		</h1>
	</div>
</div>