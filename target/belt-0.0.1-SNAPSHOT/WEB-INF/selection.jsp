<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Package Selection!</title>
</head>
<body>
<h1>Welcome to Dojoscriptions, <c:out value= "${currentUser.firstName}"/> </h1>

<form id="logoutForm" method="POST" action="/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout!" />
</form>	

<h3>Please choose a subscription and a start date</h3>



<form:form method="POST" action="/signup/${currentUser.id}" modelAttribute="subscription">
    <p>
        <form:label path="due" id="due">Due Day:
            <form:input path="due" type="number" id="due" min="1" max="31" step="1"/>
        </form:label>
    </p>
    <p> 

        <form:label path="subscriptionName">Package:
            <form:select path= "subscriptionName">
                <c:forEach items= "${subscriptions}" var = "subscription">
                    <form:option value = "${subscription.id}"> <c:out value= "${subscription.subscriptionName} ${subscription.price}"/></form:option>
                </c:forEach>
            </form:select>
        </form:label>
    </p>    
 
    <input type="submit" value="Sign Up"/>
</form:form>


</body>
</html>