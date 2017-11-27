<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit User</title>
</head>
<body>
<h1>Edit User</h1>

<form id="logoutForm" method="POST" action="/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout!" />
</form>	

<h3>Email: <c:out value= "${user.email}"/> </h3>
<h3>Customer Since: <fmt:formatDate pattern = "MMMM-dd-YYYY" value="${user.createdAt}"/></h3>
<h3>
     <form:form method="POST" action="/edit/${user.id}" modelAttribute="user">

        <form:label path="firstName">First Name:
            <form:input path="firstName" id="firstName"/>
        </form:label><br>
        
        <form:label path="lastName">Last Name:
            <form:input path="lastName"/>
        </form:label><br>

        <form:label path="subscriptions">Due Day:
            <form:input path="subscriptions" type="number"/>
        </form:label><br>	        
        
        <form:label path="email">Package Type:
            <form:select path= "email">
                <c:forEach items= "${subscriptions}" var = "subscription">
                    <form:option value = "${subscription.id}"> <c:out value= "${subscription.subscriptionName} ${subscription.price}"/></form:option>
                </c:forEach>
            </form:select>
        </form:label>  <br>
        
        
     
        <input type="submit" value="Edit User"/>
    </form:form>
</h3>
</body>
</html>