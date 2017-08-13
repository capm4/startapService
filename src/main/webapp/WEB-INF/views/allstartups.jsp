<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/allStartupsStyle.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>All Startups</title>
</head>

<body>
<jsp:include page="navbar.jsp"/>

<c:if test="${!empty mobileStartups}">
<div class="row">
    <h2>Mobile applications</h2>


<c:forEach items="${mobileStartups}" var="startup">
    <div class="col-md-3">
        <a style="text-decoration: none;" href="<c:url value='/startupdetails/${startup.id}'/>" class="">
            <div class="table-startup">
                <h4>
                    <p>${startup.name}</p>
                </h4>
                <h4>
                    <small>Desired investments:</small>
                    <p>${startup.needed_investment}</p>
                </h4>
                <h4>
                    <small>Already gathered:</small>
                    <p>${startup.current_investment}</p>
                </h4>
            </div>
        </a>
    </div>
</c:forEach>
</div>
</c:if>

<c:if test="${!empty investmentStartups}">
<div class="row">
    <h2>Investment projects</h2>


<c:forEach items="${investmentStartups}" var="startup">
    <div class="col-md-3">
        <a style="text-decoration: none;" href="<c:url value='/startupdetails/${startup.id}'/>" class="">
            <div class="table-startup">
                <h4>
                    <p>${startup.name}</p>
                </h4>
                <h4>
                    <small>Desired investments:</small>
                    <p>${startup.needed_investment}</p>
                </h4>
                <h4>
                    <small>Already gathered:</small>
                    <p>${startup.current_investment}</p>
                </h4>
            </div>
        </a>
    </div>
</c:forEach>
</div>
</c:if>

<c:if test="${!empty businessStartups}">
<div class="row">
    <h2>Business (shares) for sale</h2>

<c:forEach items="${businessStartups}" var="startup">
    <div class="col-md-3">
        <a style="text-decoration: none;" href="<c:url value='/startupdetails/${startup.id}'/>" class="">
            <div class="table-startup">
                <h4>
                    <p>${startup.name}</p>
                </h4>
                <h4>
                    <small>Desired investments:</small>
                    <p>${startup.needed_investment}</p>
                </h4>
                <h4>
                    <small>Already gathered:</small>
                    <p>${startup.current_investment}</p>
                </h4>
            </div>
        </a>
    </div>
</c:forEach>
</div>
</c:if>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>