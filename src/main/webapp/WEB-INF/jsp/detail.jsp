<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:import url="common/header.jsp">
	<c:param name="pageTitle">${park.parkName} Details</c:param>
	<c:param name="activeNav" value="details" />
</c:import>

<c:url value="/img/parks/${park.imageName}" var="parkImage" />
<img src="${parkImage}" class="rounded mx-auto d-block parkDetailImage"
	id="detailImage" />

<h1 class="text-center">${park.parkName}</h1>
<h6 class="detailAbout">${park.parkDescription}</h6>
<div class="parkDataTable mx-auto">
	<table class="mx-auto parkData">
		<tr>
			<th>State:</th>
			<td>${park.state}</td>
		</tr>
		<tr>
			<th>Acreage:</th>
			<td>${park.acreage}</td>
		</tr>
		<tr>
			<th>Elevation:</th>
			<td>${park.elevation}ft.</td>
		</tr>
		<tr>
			<th>Trail Length:</th>
			<td>${park.milesOfTrail}miles</td>
		</tr>
		<tr>
			<th>Total Campsites:</th>
			<td>${park.numberOfCampsites}</td>
		</tr>
	</table>
	<table class="mx-auto parkData">
		<tr>
			<th>Climate:</th>
			<td>${park.climate}</td>
		</tr>
		<tr>
			<th>Year Founded:</th>
			<td>${park.yearFounded}</td>
		</tr>
		<tr>
			<th>Annual Visitors:</th>
			<td>${park.annualVisitorCount}</td>
		</tr>
		<tr>
			<th>Entry Fee:</th>
			<td>$${park.entryFee}</td>
		</tr>
		<tr>
			<th>Animal Species:</th>
			<td>${park.numberOfAnimalSpecies}</td>
		</tr>
	</table>
</div>

<div class="weatherTable">
	<c:forEach items="${weather}" var="dayWeather">
		<c:if test="${dayWeather.fiveDayForcastValue == 1}">
			<div class="todaysWeather  mx-auto">
				<h5 class="text-center">Today's Forecast</h5>
		</c:if>
		<c:if test="${dayWeather.fiveDayForcastValue != 1}">
			<div class="dailyWeather">
		</c:if>
		<c:url value="/img/weather/${dayWeather.getImageName()}"
			var="weatherImageURL" />
		<img src="${weatherImageURL}" class="weatherImage" />
		<c:set value="celcius" var="celcius" />
		<c:set value="farenheit" var="farenheit" />
		<c:if test="${temperature == farenheit or temperature == null}">
			<p class="text-center high">High: ${dayWeather.high}&#176 F</p>
			<p class="text-center low">Low: ${dayWeather.low}&#176 F</p>
		</c:if>
		<c:if test="${temperature == celcius}">
			<p class="text-center high">High: ${dayWeather.highCelcius}&#176
				C</p>
			<p class="text-center low">Low: ${dayWeather.lowCelcius}&#176 C</p>
		</c:if>

		<c:if test="${dayWeather.fiveDayForcastValue == 1}">
			<ul>
				<c:forEach items="${dayWeather.reccomendations}"
					var="reccomendation">
					<li class="text-Left">${reccomendation}</li>
				</c:forEach>
			</ul>

		</c:if>
	</div>
	<c:if test="${dayWeather.fiveDayForcastValue == 1}">
	<div class="restOfWeek mx-auto">
	</c:if>
	</c:forEach>
</div>
</div>
<c:url value="/parkDetails" var="parkDetail" />

<form:form action="${parkDetail}" mathod="POST">
	<div class="input-group input-group-sm mb-3">
	
		<c:if test="${temperature == farenheit or temperature == null}">
			<input type="radio" id="farenheitInput" name="temp" value="farenheit"
				onchange='this.form.submit();' checked> <label for="farenheitInput">Fahreneit</label>
			<input type="radio" id="celciusInput" name="temp" value="celcius"
		     	onchange='this.form.submit();'> <label for="celciusInput">Celcius</label>
		</c:if>	
		
		<c:if test="${temperature == celcius}">
			<input type="radio" id="farenheitInput" name="temp" value="farenheit"
				onchange='this.form.submit();' > <label for="farenheitInput">Fahreneit</label>
			<input type="radio" id="celciusInput" name="temp" value="celcius"
		     	onchange='this.form.submit();' checked> <label for="celciusInput">Celcius</label>
		</c:if>		
	
	</div>
	<input type="hidden" name="id" value="${park.parkCode}" />
</form:form>

<c:import url="common/footer.jsp" />