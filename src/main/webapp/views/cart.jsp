<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">
    <script>
        function updateQty(itemId, change) {
            let qtySpan = document.getElementById("qty_" + itemId);
            let totalSpan = document.getElementById("total_" + itemId);
            let totalCostSpan = document.getElementById("totalCost");

            let currentQty = parseInt(qtySpan.innerText);
            let newQty = currentQty + change;

            if (newQty < 1) return; // Prevent quantity from going below 1

            fetch('/updateCartQty?itemId=' + itemId + '&change=' + change, {
                method: 'POST'
            })
                .then(response => response.text())
                .then(updatedTotalCost => {
                    qtySpan.innerText = newQty;
                    let costPerItem = parseInt(totalSpan.innerText) / currentQty;
                    totalSpan.innerText = (costPerItem * newQty).toFixed(2);
                    totalCostSpan.innerText = updatedTotalCost;
                })
                .catch(error => console.error('Error:', error));
        }
    </script>
</head>
<body>
<header>
    <h1>Your Shopping Cart</h1>
    <div class="action-buttons">
        <a href="/homepage"><button>Back to Home</button></a>
    </div>
</header>

<div class="cart-container">
    <c:if test="${not empty cartItems}">
        <table border="1">
            <tr>
                <th>Name</th>
                <th>Cost</th>
                <th>Quantity</th>
                <th>Total cost</th>
            </tr>
            <c:forEach items="${cartItems}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td>${item.cost}</td>
                    <td>
                        <button class="qty-btn" onclick="updateQty('${item.id}', -1)">-</button>
                        <span id="qty_${item.id}">${item.qty}</span>
                        <button class="qty-btn" onclick="updateQty('${item.id}', 1)">+</button>
                    </td>
                    <td id="total_${item.id}">${item.cost * item.qty}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <form action="/clearCart" method="post">
            <button type="submit">Clear Cart</button>
        </form>
        <h3>Total Cost: Rs. <span id="totalCost">${totalCost}</span></h3>
        <a href="orders"><button>Place Order</button></a>

    </c:if>
    <c:if test="${empty cartItems}">
        <p>Your cart is empty.</p>
    </c:if>
</div>



</body>
</html>
