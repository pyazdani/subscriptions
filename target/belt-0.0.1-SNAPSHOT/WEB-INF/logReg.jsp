<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login/Register</title>
</head>
<body>
<h1>DojoScriptions</h1>
<h3>A subscription platform</h3>
<c:if test="${errorMessage != null}">
    <c:out value="${errorMessage}"></c:out>
</c:if>

 <c:if test="${logoutMessage != null}">
    <c:out value="${logoutMessage}"></c:out>
</c:if>


<h1>Register</h1>

<p><form:errors path="user.*"/></p>
<form:form method="POST" action="/register" modelAttribute="user">
    <p>
        <form:label path="email">Email:</form:label>
        <form:input path="email"/>
    </p>
    <p>
        <form:label path="firstName">First Name:</form:label>
        <form:input path="firstName"/>
    </p>    
    <p>
        <form:label path="lastName">Last Name:</form:label>
        <form:input path="lastName"/>
    </p>      
    
    <p>
        <form:label path="password">Password:</form:label>
        <form:password path="password"/>
         <h6>*Password should be at least 8 characters</h6>
    </p>
    <p>
        <form:label path="passwordConfirmation">Password Confirmation:</form:label>
        <form:password path="passwordConfirmation"/>
    </p>
    <input type="submit" value="Register"/>
</form:form>



<h1>Login</h1>
    <form method="POST" action="/login">
<p>
    <label for="username">Email</label>
    <input type="text" id="username" name="username"/>
</p>
<p>
    <label for="password">Password</label>
    <input type="password" id="password" name="password"/>
</p>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="submit" value="Login"/>
</form>




</body>
</html>