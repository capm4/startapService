<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/grid.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/normalize.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
    <title>Title</title>
</head>
<body>
<div class="wrapper">
    <div style="margin: 100px">
        <table class="table table-bordered">
            <tr>
                <th>firstName</th>
                <th>lastName</th>
                <th>login</th>
                <th>password</th>
                <th>roles</th>
            </tr>
            <tr class="info">
                <th>${user.firstName}</th>
                <th>${user.lastName}</th>
                <th>${user.login}</th>
                <th>${user.password}</th>
                <th>${user.roles}</th>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
