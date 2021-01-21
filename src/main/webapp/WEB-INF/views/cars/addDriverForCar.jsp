<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add driver for car</title>
</head>
<body>
    <h1>Please enter details about driver and car</h1>
    <h2 style="color: darkred">${message}</h2>
    <form method="post" action="${pageContext.request.contextPath}/cars/add-driver">
        Car id <input type="text" name="car_id">
        Driver id <input type="text" name="driver_id">
        <button type="submit">Added</button>
    </form>
</body>
</html>
