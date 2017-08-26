<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title><c:out value="${startup.name}"/></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container">
    <h2>${startup.name} detailed information</h2>
    <br/>
    <form class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2">Category:</label>
            <div class="col-sm-10">
                <p class="form-control-static">${startup.category.name}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Total required amount:</label>
            <div class="col-sm-10">
                <p class="form-control-static">${startup.needed_investment}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Investments received:</label>
            <div class="col-sm-10">
                <p class="form-control-static">${startup.current_investment}</p>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Average Startup rating:</label>
            <div class="col-sm-10">
                <p class="form-control-static">${average_rating} (votes: ${votes_count})</p>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2">Description:</label>
            <div class="col-sm-10">
                <p class="form-control-static">${startup.description}</p>
            </div>
        </div>
    </form>
</div>
<div class="container">
    <c:if test="${startup.status == 'Approved'}">
        <h2>Investor's Block:</h2>
        <br/>
        <div class="form-horizontal">
            <form action="${pageContext.request.contextPath}/startupdetails/${id}" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2">Enter your  amount:</label>
                    <div class="col-sm-3">
                        <input class="form-control" type="text" name="investment"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-5">
                        <button type="submit" class="btn btn-primary">Make an investment!</button>
                    </div>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </c:if>
</div>
<div class="container">
    <c:if test="${is_owner}">
        <h2>Startup Owner's Block:</h2>
        <br/>
        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2">Startup status:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${startup.status}</p>
                </div>
            </div>
        </form>
        <c:if test="${startup.status == 'Draft' || startup.status == 'Rejected'}">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2">
                        <a class="btn btn-success"
                           href="<c:url value="/startupdetails/sendforapprove/${startup.id}"/>">Send for approve</a>
                    </label>
                </div>
            </form>
        </c:if>
        <c:if test="${startup.status != 'Closed'}">
            <form class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-sm-2">
                        <a href="<c:url value="/startupedit/${startup.id}"/>">Edit startup</a>
                    </label>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">
                        <a href="<c:url value="/startupdetails/close/${startup.id}"/>">Close startup</a>
                    </label>
                </div>
            </form>
        </c:if>
    </c:if>
</div>
<div class="container">
    <c:if test="${is_admin}">
        <h2>Admin's Block:</h2>
        <br/>
        <form class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2">Startup status:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${startup.status}</p>
                </div>
            </div>
        </form>
        <c:choose>
            <c:when test="${startup.status == 'Ready for approve'}">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2">
                            <div class="btn btn-success"><a
                                    href="<c:url value="/startupdetails/approve/${startup.id}"/>">Approve startup</a>
                            </div>
                        </label>
                    </div>
                </form>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2">
                            <div class="btn btn-warning"><a
                                    href="<c:url value="/startupdetails/reject/${startup.id}"/>">Reject startup</a>
                            </div>
                        </label>
                    </div>
                </form>
            </c:when>
            <c:when test="${startup.status == 'Rejected' || startup.status == 'Closed'}">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-sm-2">
                            <div class="btn btn-danger"><a href="<c:url value="/startupdetails/delete/${startup.id}"/>">Delete
                                startup</a></div>
                        </label>
                    </div>
                </form>
            </c:when>
        </c:choose>
    </c:if>
</div>
</body>
</html>
