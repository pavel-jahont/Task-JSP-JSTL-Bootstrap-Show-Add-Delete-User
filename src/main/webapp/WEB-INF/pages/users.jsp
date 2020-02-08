<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col">
            <c:if test="${not empty message}">
                User was removed successfully
            </c:if>
            <a href="${pageContext.request.contextPath}/users/add" class="btn btn-info">Add</a>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                <tr>
                    <td>Username</td>
                    <td>Password</td>
                    <td>Age</td>
                    <td>Description</td>
                    <td>Address</td>
                    <td>Telephone</td>
                    <td>Actions</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td><c:out value="${user.username}"/></td>
                        <td><c:out value="${user.password}"/></td>
                        <td><c:out value="${user.age}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.active}">
                                    I am a superman!
                                </c:when>
                                <c:otherwise>
                                    Staying at shadow
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${user.telephone}"/></td>
                        <td><c:out value="${user.address}"/></td>
                        <td>
                            <form method="post" action="${pageContext.request.contextPath}/users">
                                <input name="userId" type="hidden" value="${user.id}">
                                <button type="submit" class="btn btn-primary">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/pages/js.jsp" %>
</body>
</html>
