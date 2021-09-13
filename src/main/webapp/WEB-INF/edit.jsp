<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-889-1">
<title>New one</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css" />
</head>
	<body>
	<form:form action="/task/${task.id}/edit" method="post" modelAttribute="task">
	<form:hidden value="${currUser.id}" path="user"/>
	<h2>Edit ${task.iBody}</h2>
	<div class="form-group">
				        <form:label path="iBody">Content: </form:label>
				        <form:errors path="iBody"/>
				        <form:input class="form-control" path="iBody" placeholder="${thisTask.iBody }"/>
	</div>
	<%-- <div class="form-group">
				        <form:label path="assignee">Creator</form:label>
				        <form:errors path="assignee"/>
				        <form:select path="assignee">
				        	<c:forEach items = "${user}" var="currUser">
				        		<option value="${user.firstName}"> ${user.firstName}</option>
				        	</c:forEach>
				        </form:select>
				        <!-- how to just show the current user.  -->
	</div> --%>
	<%-- <div class = "form-group">
		<form:label path = "likers">Likers: </form:label>
		<form:errors path="likers"/>
		<form:input class="form-control" path="likers"/>
	</div> --%>
	
	<c:forEach items="${likers}" var = "liker">
		<p>${liker.user.firstName} Says: </p>
	</c:forEach>
	
	<button>Update</button>
	<button><a href="/task/deleteTask/${task.id}">Delete</a></button>
	
	</form:form>

</body>
</html>