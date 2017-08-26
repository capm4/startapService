<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${pageContext.request.getRemoteUser()} details</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<input type="hidden" name="userLogin" value="${pageContext.request.getRemoteUser()}"/>
<div class="container">
    <br/>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <form:form method="post" modelAttribute="userForm" class="form-horizontal" role="form">
                <fieldset>
                    <legend>Personal information:</legend>
                    <form:hidden path="id"/>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Login:</label>
                        <div class="col-sm-7">
                            <form:input path="login" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">First Name:</label>
                        <div class="col-sm-7">
                            <form:input path="firstName" class="form-control"/>
                        </div>
                    </div>
                    <input type="hidden" name="password" id="${userForm.password}">
                    <div class="form-group">
                        <label class="control-label col-sm-5">Last Name:</label>
                        <div class="col-sm-7">
                            <form:input path="lastName" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Phone number:</label>
                        <div class="col-sm-7">
                            <form:input path="phoneNumber" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Email:</label>
                        <div class="col-sm-7">
                            <form:input path="email" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">Country:</label>
                        <div class="col-sm-7">
                            <form:input path="country" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-5">City:</label>
                        <div class="col-sm-7">
                            <form:input path="city" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-5 col-sm-4">
                            <button type="submit" class="btn btn-primary">Edit profile</button>
                        </div>
                        <div class="col-sm-3">
                            <a href="<c:url value="/allstartups"/>" class="btn btn-ghost">Cancel</a>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
