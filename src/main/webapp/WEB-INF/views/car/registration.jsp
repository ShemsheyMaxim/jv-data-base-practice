<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Car</title>
</head>
<body>
    <h1>Please enter more details about car and its manufacturer</h1>
    <form method="post" action="${pageContext.request.contextPath}/car/add">
        Car model <input type="text" name="model" required>
        Manufacturer id <input type="number" name="manufacturer_id" required>
        <button type="submit">Registration</button>
    </form>
</body>
</html>
