<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml11.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cars currently in the shop</title>
</head>
<body>
	<c:url value="/logout" var="logoutUrl" />
	<form id="logout" action="${logoutUrl}" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<button type="submit">Logout</button>
	</form>
	<h1>Cars currently in the shop</h1>
	<table>
		<c:forEach var="car" items="${cars}">
			<tr>
				<td>${car.make}</td>
				<td>${car.model}</td>
				<td>${car.year}</td>
				<td>${car.color}</td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<td><a href="cars/${car.id}">edit</a></td>
				</sec:authorize>

			</tr>
		</c:forEach>
	</table>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<a href="addCar.jsp"> Add a Car</a>
	</sec:authorize>
</body>
</html>