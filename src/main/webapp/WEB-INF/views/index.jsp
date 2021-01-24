<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Welcome in service taxi!</h1>
<a href="${pageContext.request.contextPath}/manufacturer/add">add manufacturer</a>
<a href="${pageContext.request.contextPath}/car/add">add car</a>
<a href="${pageContext.request.contextPath}/car/driver/add">add driver for car</a>
<a href="${pageContext.request.contextPath}/drivers">all drivers</a>
<a href="${pageContext.request.contextPath}/cars">all cars by driver</a>
</body>
</html>
