<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,300i,400" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/grid.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/normalize.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
    <title>Welcome!</title>
</head>
<body>
<header>
    <nav>
        <div class="row">
            <ul class="main-nav">
                <li><a href="login">Login</a></li>
                <li><a href="registration">Sign up</a></li>
            </ul>
        </div>
    </nav>
    <div class="hero-text-box">
        <h1>Startup platform - Know what you own,<br>and know why you own it.</h1>
        <a class="btn btn-ghost" href="allstartups">Show me more</a>
    </div>
</header>
</body>
</html>