<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="false" %>
<html lang="en">
<head>
    <title>Add new startup</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container">
    <h2>Add new startup</h2>
    <br/>

    <form:form method="post" modelAttribute="starrupForm" class="form-horizontal">

        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
                <spring:bind path="name">
                <div class=${status.error ? 'has-error': 'col-sm-10'}>
                    <form:input path="name" type="text" class="form-control"
                                id="name" placeholder="Enter new startups name"/>
                    <form:errors path="name"/>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="description">Description:</label>
            <spring:bind path="description">
                <div class=${status.error ? 'has-error' : 'col-sm-10'}>
                    <form:input type="text" class="form-control" id="description"
                                placeholder="Enter new startup description" path="description"/>
                    <form:errors path="description"/>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <spring:bind path="needed_investment">
                <label class="control-label col-sm-2" for="investment">Needed Investment:</label>
                <div class=${status.error ? 'has-error' : 'col-sm-10'}>
                    <form:input type="number" class="form-control" id="investment"
                                placeholder="Enter needed investment amount" path="needed_investment"/>
                    <form:errors path="needed_investment"/>
                </div>
            </spring:bind>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2">Select Category:</label>
            <div class="col-sm-10">
                <div class="radio">
                    <label><input type="radio" value="1" name="categoryId" checked/>Mobile applications</label>
                </div>
                <div class="radio">
                    <label><input type="radio" value="2" name="categoryId">Investment projects</label>
                </div>
                <div class="radio">
                    <label><input type="radio" value="3" name="categoryId">Business (shares) for sale</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="vol-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">Submit</button>
                <button onclick="location.href='home'" type="button" class="btn btn-primary">Cancel</button>
            </div>
        </div>
    </form:form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
