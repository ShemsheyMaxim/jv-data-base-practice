<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Manufacturer</title>
</head>
<body>
    <h1>Please enter more details about manufacturer</h1>
    <h2 style="color: darkred">${message}</h2>
    <form method="post" action="${pageContext.request.contextPath}/manufacturers/registration">
        Manufacturer name <input type="text" name="manufacturer_name">
        Manufacturer country <input type="text" name="country">
        <button type="submit">Register</button>
    </form>
</body>
</html>
