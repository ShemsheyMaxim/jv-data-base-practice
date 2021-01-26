<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Driver</title>
</head>
<body>
    <h1>Please enter more details about driver</h1>
    <form method="post" action="${pageContext.request.contextPath}/drivers/add">
        Your name: <input type="text" name="name_driver" required>
        Your licence number: <input type="text" name="licence_number" required>
        Your login: <input type="text" name="login" required>
        Your password: <input type="password" name="password" required>

        <button type="submit">Register</button>
    </form>
</body>
</html>
