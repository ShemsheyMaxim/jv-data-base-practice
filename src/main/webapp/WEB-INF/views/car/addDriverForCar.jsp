<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver for car</title>
</head>
<body>
    <h1>Please enter details about driver and car</h1>
    <form method="post" action="${pageContext.request.contextPath}/car/driver/add">
        Car id <input type="number" name="car_id" required>
        Driver id <input type="number" name="driver_id" required>
        <button type="submit">Added</button>
    </form>
</body>
</html>
