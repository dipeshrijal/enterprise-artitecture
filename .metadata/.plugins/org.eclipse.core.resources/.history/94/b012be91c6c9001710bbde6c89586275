<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Login Demo</h1>
	<c:if test="$(error eq true)">
		<p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
	</c:if>

	<form method="post" action="<c:url value='j_spring_security_check' />">
			Username: <input name="j_username"
			value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' />
		<br /> Password: <input type="password" name="j_password" /> <br />

		<input type="submit" />
	</form>
</body>
</html>