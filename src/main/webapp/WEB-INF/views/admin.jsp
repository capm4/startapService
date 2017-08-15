<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/myStartupsStyle.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Welcome Admin</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="row">
    <h2>Draft startups</h2>
    <c:forEach items="${allstatups}" var="startup">
        <c:if test="${startup.status=='Draft'}">
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
        </c:if>
    </c:forEach>
</div>
<div class="row">
    <h2>Ready for approval startups</h2>
    <c:forEach items="${allstatups}" var="startup">
        <c:if test="${startup.status =='Ready for approve'}">
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
        </c:if>
    </c:forEach>
</div>
<div class="row">
    <h2>Approved startups</h2>
    <c:forEach items="${allstatups}" var="startup">
        <c:if test="${startup.status == 'Approved'}">
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
        </c:if>
    </c:forEach>
</div>
<div class="row">
    <h2>Rejected startups</h2>
    <c:forEach items="${allstatups}" var="startup">
        <c:if test="${startup.status == 'Rejected'}">
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
        </c:if>
    </c:forEach>
</div>
<div class="row">
    <h2>Closed startups</h2>
    <c:forEach items="${allstatups}" var="startup">
        <c:if test="${startup.status == 'Closed'}">
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
                        <h4>
                            <small>Status:</small>
                            <p>${startup.status}</p>
                        </h4>
                    </div>
                </a>
            </div>
        </c:if>
    </c:forEach>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
