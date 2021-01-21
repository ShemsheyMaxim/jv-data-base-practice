<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Manufacturer</title>
</head>
<body>
    <h1>Please enter more details about manufacturer</h1>
    <form method="post" action="${pageContext.request.contextPath}/manufacturer/registration">
        Manufacturer name <input type="text" name="manufacturer_name" required>
        Manufacturer country <input type="text" name="country" required>
        <button type="submit">Register</button>
    </form>
</body>
</html>
