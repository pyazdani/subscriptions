<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>

<h1>Welcome <c:out value="${currentUser.firstName}"></c:out>!</h1>


<form id="logoutForm" method="POST" action="/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout!" />
</form>		

<c:forEach items= "${subscriptions}" var= "subscription">
<table border="1">
    <tr> 
        <td>Current Package: </td>
        <td> <c:out value="${subscription.subscriptionName}"></c:out> </td>
    </tr>
    <tr>
        <td>Next Due Date:</td>
        <td><fmt:formatDate pattern = "MMMM" value="${currentUser.createdAt}"/> <c:out value="${subscription.due}"></c:out>, <fmt:formatDate pattern = "yyyy" value="${currentUser.createdAt}"/> </td>
    </tr>
    <tr>
        <td>Amount Due:</td>
        <td>$<c:out value="${subscription.price}"></c:out></td>
    </tr>
    <tr>
        <td>User Since:</td>
        <td><fmt:formatDate pattern = "MMMM dd, yyyy" value="${currentUser.createdAt}"/></td>
    </tr>

</table><br>
</c:forEach>



</body>
</html>