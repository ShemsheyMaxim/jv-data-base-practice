<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<h1>All cars page</h1>
<table border="1">
    <tr>
        <th>Car id</th>
        <th>Car model</th>
        <td>Manufacturer id</td>
        <td>Manufacturer name</td>
        <td>Manufacturer country</td>
        <th>Driver id</th>
        <th>Driver name</th>
        <th>Driver licence number</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>
                <c:out value="${car.id}"/>
            </td>
            <td>
                <c:out value="${car.model}"/>
            </td>
            <td>
                <c:out value="${car.manufacturer.id}"/>
            </td>
            <td>
                <c:out value="${car.manufacturer.name}"/>
            </td>
            <td>
                <c:out value="${car.manufacturer.country}"/>
            </td>
            <c:forEach var="driver" items="${car.drivers}">
                <td><c:out value="${driver.id}"/></td>
                <td><c:out value="${driver.name}"/></td>
                <td><c:out value="${driver.licenceNumber}"/></td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>
