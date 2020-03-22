<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="common/header.jsp">
	<c:param name="pageTitle">Survey</c:param>
	<c:param name="activeNav" value="survey" />
</c:import>

<h2>Please vote for your favorite National park.</h2>

<body>
	<c:url var="surveyUrl" value="/survey" />
	<form:form id="survey" action="${surveyUrl}" method="POST"
		modelAttribute="surveyData">

		<p>All fields are required.</p>

		<div class="inputElement">
			<label class="fnt-bold" for="parkCodeInput">National Park</label>

			<form:select class="form-control" name="parkCode" path="parkCode"
				required="required">
				<option value="">Select a Park</option>
				<c:forEach items="${parks}" var="park">
					<c:if test="${surveyData.parkCode == park.parkCode}">
						<option value="${park.parkCode}" selected="selected">${park.parkName}</option>
					</c:if>
					<c:if test="${surveyData.state != park.parkCode}">
						<option value="${park.parkCode}">${park.parkName}</option>
					</c:if>
				</c:forEach>
			</form:select>
			<form:errors path="parkCode" class="error" />
			<c:if test="${parkError != null}">
				<p class="error">${parkError}</p>
			</c:if>
		</div>

		<div class="inputElement">
			<label class="fnt-bold" for="emailInput">Your Email</label>
			<form:input class="form-control" id="emailInput" type="email"
				name="emailAddress" path="emailAddress" placeholder="enter email"
				required="required" />
			<form:errors path="emailAddress" class="error" />
		</div>

		<div class="inputElement">
			<label class="fnt-bold" for="stateInput">Your State</label>
			<form:select class="form-control" name="state" path="state"
				required="required">
				<option value="">Select a State</option>
				<c:forEach items="${states}" var="state">
					<c:if test="${surveyData.state == state.name}">
						<option selected="selected">${state.name}</option>
					</c:if>
					<c:if test="${surveyData.state != state.name}">
						<option>${state.name}</option>
					</c:if>
				</c:forEach>
			</form:select>
			<form:errors path="state" class="error" />
			<form:errors path="validState" class="error" />
		</div>

		<div class="inputElement">
			<label class="fnt-bold" for="activityLevelInput">Your Activity
				Level</label>
			<div class="activityOptions">
				<c:forEach items="${activities}" var="activity">
					<div class="activityChoice">
						<form:radiobutton id="${activity}" name="activityLevel"
							path="activityLevel" value="${activity.name}" required="required" />
						<label for="${activity.name}" class="activityChoice">${activity.name}</label>
					</div>
				</c:forEach>
			</div>
			<form:errors path="activityLevel" class="error" />
			<form:errors path="validActivityLevel" class="error" />
		</div>

		<div class="inputElement">
			<div></div>
			<input class="btn btn-primary" name="submit" value="Submit"
				type="submit" />
		</div>
	</form:form>
</body>


<c:import url="common/footer.jsp" />