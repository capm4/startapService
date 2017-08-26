<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/><html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>STARTUP PLATFORM</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <style type="text/css">
        h1 {
            margin: 30px 0;
            padding: 0 200px 15px 0;
            border-bottom: 1px solid #E5E5E5;
        }
        .bs-example {
            margin: 20px;
        }
    </style>
</head>
<body>
<!--navbar navbar-inverse navbar-fixed-top-->
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
            </button>
            <a class="navbar-brand" href="<c:url value="/allstartups"/>">STARTUP PLATFORM</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/addstartup"/>">Add Startup</a>
        </ul>
        <ul class="nav navbar-nav">
        </ul>
        <ul class="nav navbar-nav">
            <li><a href="<c:url value="/mystartups"/>">My Startups</a> </li>
        </ul>
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <div id="navbar" class="navbar-collapse collapse">
            <form class="navbar-form navbar-rigth">
                <c:if test="${!empty pageContext.request.getRemoteUser()}">
                    <a onclick="document.forms['logoutForm'].submit()" class="btn btn-primary">Log out</a>
                    <a href="<c:url value="/userdetails"/>" type="submit" class="btn btn-primary">My info</a>
                </c:if>
                <c:if test="${empty pageContext.request.getRemoteUser()}">
                    <a href="<c:url value="/login"/>" class="btn btn-primary">Log in</a>
                </c:if>
                <c:choose>
                    <c:when test="${pageContext.request.isUserInRole('ROLE_ADMINE')}">
                        <<a href="<c:url value="/admin"/>" class="btn btn-primary">Admin</a>
                    </c:when>
                </c:choose>
            </form>
        </div>
    </div>
</nav>

</body>
</html>
