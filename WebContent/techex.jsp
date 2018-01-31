<%@ page language="java" contentType="text/html; charet=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 TRANSITIONAL//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body{background-color: #dce6f7;}
h1{text-align: center;}
h2{}
form{}
input{}
</style>
</head>
	<body>
		<h1>Welcome to Dr.Johnson's Patient Portal!</h1>
		<h2>Insert New Patient Data Here:</h2>
		<form action="insert" method="post">
			User Name:<br><input class="1" type="text" name="MYUSER"/><br/>
			E-mail:<br><input type="text" name="EMAIL"/><br/>
			Phone:<br><input type="text" name="PHONE"/><br/>
			First Name:<br><input type="text" name="FIRSTNAME"/><br/>
			Last Name:<br><input type="text" name="LASTNAME"/><br/>
			Address:<br><input type="text" name="ADDRESS"/><br/>
			Age:<br><input type="text" name="AGE"/><br/>
			Insurance:<br><input type="text" name="INSURANCE"/><br/>
			<input type="submit" value="Submit"/>
		</form>
		<h2>Search for Current Patients with Patient ID here:</h2>
		<form action="search" method="post">
			<input type="text" name="search"/>
			<input type="submit" value="search" name="Search"/>
		</form>
	</body>
</html>