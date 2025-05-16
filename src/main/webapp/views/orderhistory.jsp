<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Users' Order History</title>
</head>
<body>
<h2>All Order History</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Order ID</th>
        <th>User ID</th>
        <th>Payment Method</th>
        <th>Timestamp</th>
        <th>Products</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${allOrders}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.userId}</td>
            <td>${order.paymentMethod}</td>
            <td>${order.timestamp}</td>
            <td>
                <c:forEach var="item" items="${order.prodList}">
                    ${item.name} (x${item.qty}) - ₹${item.cost} each<br/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
