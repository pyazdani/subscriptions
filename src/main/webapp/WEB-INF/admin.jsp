<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Dashboard</title>
</head>
<body>
<h1>Admin Dashboard</h1><br>
<h1>Welcome <c:out value="${currentUser.firstName}"></c:out></h1>

  <form id="logoutForm" method="POST" action="/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Logout!" />
</form>	<br>	


<h3>Customers</h3>
<table border="1">
<tr>
    <th> Name </th>
    <th> Packages </th>
</tr>
<tbody>
<c:forEach items="${allUsers}" var="user" varStatus="loop">
    <c:choose>
        <c:when test="${user.hasSubscription()}">
            <tr> 
                <td>
                    <c:out value="${user.firstName} ${user.lastName}"/></a>
                </td>
                <td>
                    <table border= "1">
                    <tr>
                        <th> Next Due Date </th>
                        <th> Amount Due </th>
                        <th> Package Type </th> 
                    </tr>
                    
                    <c:forEach items="${user.subscriptions}" var= "sub">
                           <tr>
                            <td><fmt:formatDate pattern = "MMMM" value="${currentUser.createdAt}"/> <c:out value="${sub.due}"></c:out>, <fmt:formatDate pattern = "yyyy" value="${currentUser.createdAt}"/></td>
                            <td>$<c:out value="${sub.price}"/>0</td>
                            <td><c:out value="${sub.subscriptionName}"/></td>
                        </tr>
                    </c:forEach>
                    </table>
                </td>
                
            </tr>
        </c:when>
    </c:choose>
 </c:forEach>
 </tbody>

</table>


<h3>Packages</h3>
<table border="1">
    <th> Package Name </th>
       <th> Package Cost </th>
    <th> Available </th>
    <th> Users </th>
    <th> Actions </th>

<c:forEach items="${subscriptions}" var="subscription" varStatus="loop">

<tr> 
    <td><c:out value="${subscription.subscriptionName}"/></td>
    
    
    <td>$<c:out value="${subscription.price}"/>0</td>
    <td>
        <c:choose>
            <c:when test = "${subscription.isStatus() }">
                Available
            </c:when>
            <c:otherwise>
                 Unavailable
            </c:otherwise>
        </c:choose>
    </td>
    <td><c:out value= "${subscription.users.size()}"/></td>
    

     <td>
        <c:choose>
            <c:when test = "${subscription.isStatus() }">
                <a href="/admin/deactivate/${subscription.id}"> Deactivate</a>	    			
            </c:when>
            <c:otherwise>
                <a href= "/admin/activate/${subscription.id}">Activate</a>
            </c:otherwise>
        </c:choose>
        <c:if test="${subscription.getUsers().size() < 1}">
            <button><a href="/admin/delete/${subscription.id}">Delete</a></button>
        </c:if>
    </td>
    
    
    
</tr>

 </c:forEach>

</table>

 <h3>Create Package</h3>
 <form:form method="POST" action="/addPackage" modelAttribute="subscription">
  <p>
        <form:label path="subscriptionName">Package Name:</form:label>
        <form:input path="subscriptionName"/>
    </p>
    <p>
        <form:label path="price">Cost:</form:label>
        <form:input path="price"/>
    </p>    


    <input type="submit" value="Create New Package"/>
</form:form>




</body>
</html>