<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="common/header.jsp">
	<c:param name="pageTitle">Favorites</c:param>
	<c:param name="activeNav" value="favorites" />
</c:import>

<div id="favoriteParks">
<h1 class="text-center">Favorite Parks</h1>
<c:url value="/survey" var="surveyLink"/>
<p>These are our top parks based on visitor survey feedback. We are very proud of our National Parks and always want to hear about your experience.
Go fill out a survey <a href="${surveyLink}">here</a> for your favorite park for a chance to win a free stay at one of our fantastic parks!</p>
	<div class="favoritesTable mx-auto">
	<table class="table table-striped table-bordered">
		<thead class="thead-dark">
			<tr>
				<th> </th>
				<th>Name</th>
				<th>Surveys</th>
			</tr>
		</thead>
		<c:forEach items="${parks}" var="park">
			<tr>
				<c:url var="viewDetailsURL" value="/parkDetails">
					<c:param name="id" value="${park.parkCode}" />
				</c:url>
				<td><c:url value="/img/parks/${park.imageName}" var="parkImage" />
					<a href="${viewDetailsURL}"><img src="${parkImage}" /></a></td>
				<td>
					<h5>${park.parkName}</h5>
				</td>
				<td class="text-center">${park.numberOfSurveys}</td>
			</tr>
		</c:forEach>
	</table>
	</div>
</div>


<c:import url="common/footer.jsp" />