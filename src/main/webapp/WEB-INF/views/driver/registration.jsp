<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Driver</title>
</head>
<body>
    <h1>Please enter more details about driver</h1>
    <form method="post" action="${pageContext.request.contextPath}/driver/add">
        Your name: <input type="text" name="name_driver" required>
        Your licence number: <input type="text" name="licence_number" required>
        <button type="submit">Register</button>
    </form>
</body>
</html>
