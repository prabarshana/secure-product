<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up!</title>
</head>
<body>

	<h2>User sign up page..</h2>
	<form action="/api/save" method="post">
		<table>
			<tr>
				<td>User Name</td>
				<td><input name="userName" type="text" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input name="password" type="password" /></td>
			</tr>
			<tr>
				<td>Company</td>
				<td><input name="company" type="text" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input name="name" type="text" /></td>
			</tr>
			<tr>
				<td>Save..</td>
				<td><input value="Submit" type="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>