<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-889-1">
<title>Main Page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css" />
</head>
<body>
	<p>Hi ${user.firstName } - <a href="/logout">Log out</a>
	
	<form:form method = "POST" action = "/task/addIdea" modelAttribute = "idea">
	<form:label path = "iBody">Post Idea</form:label>
	<form:errors path = "iBody"/>
	<form:textarea path = "iBody"/>
	<form:input type = "hidden" value = "${user.id }" path="user"/>
	<button> Add Idea! </button>
	</form:form>
	<h4>All Ideas</h4>
	<c:forEach items="${ideas}" var = "idea">
	<p>${idea.user.firstName} Says: </p>
	<p style = "border: 1px solid black"><a href = "/task/${idea.id}">${idea.iBody}</a></p>
	<c:choose>
	<c:when test = "${idea.likers.contains(user) }">
	<p> Liked </p>
	</c:when>
	<c:otherwise>
	<p><a href="/task/like/${idea.id }">Like</a> - ${idea.likers.size() }</p>
	</c:otherwise>
	</c:choose>
	</c:forEach>
	<p>Logged In User has Posted: ${user.createdIdeas.size()} ideas </p>
	
</body>
</html>