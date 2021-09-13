<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Details</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css"/>
</head>
<body>
	<h2>${oneTask.iBody}</h2>
	<h3>Created By: ${oneTask.user.firstName }</h3>
	<c:forEach items="${oneTask.likers}" var = "liker">
		<p>${liker.firstName} </p>
	</c:forEach>
	<p>Amount of likers: ${oneTask.likers.size()}</p>
	<a href="/task/${oneTask.id}/edit"><button>Edit</button></a>
</body>
</html>