<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="common/header.jsp">
	<c:param name="pageTitle">Home Page</c:param>
	<c:param name="activeNav" value="home" />
</c:import>

<div id="allParks">
	<c:forEach items="${parks}" var="park">
		<div class="park mx-auto">
		<c:url var="viewDetailsURL" value="/parkDetails">
			<c:param name="id" value="${park.parkCode}"/> 
		</c:url>
			<div class="parkImage mx-auto">
				<c:url value="/img/parks/${park.imageName}" var="parkImage" />
				<a title="Click here to show details" href="${viewDetailsURL}"><img src="${parkImage}"/></a>
			</div>
			<div class="parkSummary mx-auto">
				<h3>${park.parkName}</h3>
				<h5>${park.state}</h5>
				<p>${park.parkDescription}</p>
			</div>
		</div>
	</c:forEach>
</div>


<c:import url="common/footer.jsp" />