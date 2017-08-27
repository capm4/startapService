<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>StartUp: Registration</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">


    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

</head>
<body>
<div class="modal-dialog">
    <form:form method="post" modelAttribute="userForm" class="loginmodal-container">
        <h1>Create Your Account</h1>
        <spring:bind path="login">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="login"/>
                <form:input path="login" type="text" class="form-control"
                            placeholder="Login" autofocus="true"/>
            </div>
        </spring:bind>
        <spring:bind path="password">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="password"/>
                <form:input path="password" type="password" class="form-control"
                            placeholder="Password"/>
            </div>
        </spring:bind>
        <spring:bind path="confirmPassword">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="confirmPassword"/>
                <form:input path="confirmPassword" type="password" class="form-control"
                            placeholder="Confirm your password"/>
            </div>
        </spring:bind>
        <spring:bind path="firstName">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="firstName"/>
                <form:input path="firstName" type="text" class="form-control"
                            placeholder="First Name" autofocus="true"/>
            </div>
        </spring:bind>
        <spring:bind path="lastName">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="lastName"/>
                <form:input path="lastName" type="text" class="form-control"
                            placeholder="Last Name" autofocus="true"/>
            </div>
        </spring:bind>
        <spring:bind path="email">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="email"/>
                <form:input path="email" type="text" class="form-control"
                            placeholder="Email" autofocus="true"/>
            </div>
        </spring:bind>
        <spring:bind path="phoneNumber">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="phoneNumber"/>
                <form:input path="phoneNumber" type="text" class="form-control"
                            placeholder="Phone Number" autofocus="true"/>
            </div>
        </spring:bind>
        <spring:bind path="country">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="country"/>
                <form:input path="country" type="text" class="form-control"
                            placeholder="Country" autofocus="true"/>
            </div>
        </spring:bind>
        <spring:bind path="city">
            <div class="${status.error ? 'has-error' : ''}">
                <form:errors path="city"/>
                <form:input path="city" type="text" class="form-control"
                            placeholder="City" autofocus="true"/>
            </div>
        </spring:bind>

        <div class="btn-group form-group" data-toggle="buttons">
            <label class="btn btn-primary">
                <input type="radio" name="roleId" value="4" id="option1" checked>
                Founder
            </label>
            <label class="btn btn-primary">
                <input type="radio" name="roleId" value="2" id="option2">
                Investor
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        <input onclick="location.href='home'" class="btn btn-lg btn-primary btn-block" type="button" value="Cancel"/>

    </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
