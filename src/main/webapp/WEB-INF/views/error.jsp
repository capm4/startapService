<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var = "contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <h3>Something went wrong</h3>
    <h4>${error}</h4>
</body>
</html>
