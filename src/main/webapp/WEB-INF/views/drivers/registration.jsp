<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Driver</title>
</head>
<body>
    <h1>Please enter more details about driver</h1>
    <h2 style="color:darkred">${message}</h2>
    <form method="post" action="${pageContext.request.contextPath}/drivers/registration">
        Your name: <input type="text" name="name_driver">
        Your licence number: <input type="text" name="licence_number">
        <button type="submit">Register</button>
    </form>
</body>
</html>
