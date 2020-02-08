<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Add user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <c:if test="${not empty errors}">
                <c:forEach items="${errors}" var="error">
                    <c:out value="${error}"/>
                </c:forEach>
            </c:if>
            <form method="post" action="${pageContext.request.contextPath}/users/add">
                <div class="form-group">
                    <label for="username_input">Username</label>
                    <input type="text" name="username" maxlength="30" class="form-control" id="username_input">
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" name="password" maxlength="30" class="form-control" id="exampleInputPassword1">
                </div>
                <div class="form-group form-check">
                    <input type="checkbox" name="active" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Is active?</label>
                </div>
                <div class="form-group">
                    <label for="age_input">Age</label>
                    <input type="number" name="age" max="99" class="form-control" id="age_input">
                </div>
                <div class="form-group">
                    <label for="telephone_input">Telephone</label>
                    <input type="text" name="telephone" class="form-control" maxlength="17"
                           pattern="\+375\-[0-9]{2}\-[0-9]{3}\-[0-9]{2}\-[0-9]{2}" id="telephone_input">
                </div>
                <div class="form-group">
                    <label for="address_input">Address</label>
                    <input type="text" name="address" class="form-control" id="address_input">
                </div>
                <button type="submit" class="btn btn-primary">Add</button>
            </form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/pages/js.jsp" %>
</body>
</html>
