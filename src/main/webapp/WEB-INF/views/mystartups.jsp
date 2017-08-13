<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/myStartupsStyle.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>My Startups</title>
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <c:if test="${!empty allUserStartups}">
        <div class="row">
            <h2>My Startups</h2>
            <c:forEach items="${allUserStartups}" var="startup">
                <div class="col-md-3">
                    <a style="text-decoration: none;" href="<c:url value='/startupdetails/${startup.id}'/>">
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
                                <p>${startup.currnet_investment}</p>
                            </h4>
                            <h4>
                                <small>Status:</small>
                                <p>${startup.status}</p>
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
