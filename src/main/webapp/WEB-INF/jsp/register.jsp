<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="GB2312"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>зЂВс</title>
</head>
<body>
<div
		$hello
	/>
<div id="global">
	<form:form action="/user/register" method="post"
			   commandName="user11">
		<fieldset>
			<legend>Login</legend>
			<p>
				<label for="userName">UserName:</label>
				<form:input id="username" path="username" cssErrorClass="error" />
			</p>

			<p>
				<label for="password">Password:</label>
				<form:input id="password" path="password" cssErrorClass="error" />
			</p>
			<p id="buttons">
				<input id="reset" type="reset" tabindex="4">
				<input id="submit" type="submit" tabindex="5" value="зЂВс">
			</p>
		</fieldset>

	</form:form>
</div>

</body>
</html>