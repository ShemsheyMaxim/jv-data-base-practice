<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Car</title>
</head>
<body>
    <h1>Please enter more details about car and its manufacturer</h1>
    <h2 style="color: darkred">${message}</h2>
    <form method="post" action="${pageContext.request.contextPath}/cars/registration">
        Car model <input type="text" name="model">
        Manufacturer id <input type="text" name="manufacturer_id">
        <button type="submit">Registration</button>
    </form>
</body>
</html>
