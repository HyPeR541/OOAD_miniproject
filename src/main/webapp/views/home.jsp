<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <script>
        function addToCart(itemId, itemName, cost, description) {
            let qty = 1; // Default quantity set to 1
            let userid = "${userid}";

            if (!userid) {
                alert("User ID is missing. Please log in.");
                return;
            }

            $.ajax({
                type: "POST",
                url: "/addToCart",
                data: { itemId: itemId, userid: userid, itemName: itemName, cost: cost, description: description, qty: qty },
                success: function(response) {
                    alert(response);
                },
                error: function() {
                    alert("Failed to add item to cart.");
                }
            });

        }
        function searchItems(event) {
            // Prevent default form submission
            if (event) event.preventDefault();

            var query = document.getElementById("searchBox").value.trim();
            if (query === "") {
                // Reset all highlighting if search is empty
                resetHighlighting();
                return;
            }

            // Reset previous highlighting
            resetHighlighting();

            // Find and highlight matching items in the existing table
            var table = document.querySelector("table tbody");
            var rows = table.getElementsByTagName("tr");
            var found = false;

            for (var i = 0; i < rows.length; i++) {
                var nameCell = rows[i].getElementsByTagName("td")[0]; // First column contains the name
                var descCell = rows[i].getElementsByTagName("td")[3]; // Fourth column contains description
                var catCell = rows[i].getElementsByTagName("td")[4];  // Fifth column contains category

                if (nameCell) {
                    var nameText = nameCell.textContent || nameCell.innerText;
                    var descText = descCell.textContent || descCell.innerText;
                    var catText = catCell.textContent || catCell.innerText;

                    // Check if the search query matches any of the fields
                    if (nameText.toLowerCase().includes(query.toLowerCase()) ||
                        descText.toLowerCase().includes(query.toLowerCase()) ||
                        catText.toLowerCase().includes(query.toLowerCase())) {

                        // Highlight the matching row
                        rows[i].style.backgroundColor = "#ffffc0"; // Light yellow background

                        // Highlight the matching text in the name
                        if (nameText.toLowerCase().includes(query.toLowerCase())) {
                            nameCell.innerHTML = highlightText(nameText, query);
                        }

                        found = true;
                    }
                }
            }

            // Show a message if no matches found
            var resultsDiv = document.getElementById("searchResults");
            if (!found) {
                resultsDiv.innerHTML = "<p>No matching items found.</p>";
            } else {
                resultsDiv.innerHTML = ""; // Clear any previous "no results" message
            }
        }

        function resetHighlighting() {
            // Reset row highlighting
            var rows = document.querySelectorAll("table tbody tr");
            for (var i = 0; i < rows.length; i++) {
                rows[i].style.backgroundColor = "";
            }

            // Reset text highlighting in name cells
            var nameCells = document.querySelectorAll("table tbody tr td:first-child");
            for (var i = 0; i < nameCells.length; i++) {
                var cell = nameCells[i];
                cell.innerHTML = cell.innerText; // Remove any HTML formatting
            }

            // Clear search results message
            document.getElementById("searchResults").innerHTML = "";
        }

        function highlightText(text, query) {
            // Create a regex that matches the query text (case insensitive)
            var regex = new RegExp('(' + query + ')', 'gi');
            // Replace matching text with highlighted version
            return text.replace(regex, '<span style="background-color: yellow; font-weight: bold;">$1</span>');
        }

        function applySort() {
            var sortBy = document.getElementById("sortBy").value;
            var sortOrder = document.getElementById("sortOrder").value;
            window.location.href = "/homepage?sortBy=" + sortBy + "&sortOrder=" + sortOrder;
        }
    </script>
</head>
<body>
<header>
    <h1>Welcome to the SpringMart</h1>
    <div class="action-buttons">
        <h2>Hello, ${username}</h2>
        <a href="/cart"><button> Proceed to Cart </button></a>
        <form action="/logout" method="post">
            <button type="submit">Logout</button>
        </form>
    </div>
</header>
<form class="search-form" onsubmit="searchItems(event); return false;">
    <label><input type="text" id="searchBox" name="Search-bar" placeholder="Search here"></label>
    <button type="submit">Search</button>
    <div id="searchResults"></div>
    <br>
</form>
<br>

<div>
    <label for="sortBy">Sort By:</label>
    <select id="sortBy" name="sortBy" onchange="applySort()">
        <option value="">None</option>
        <option value="cost">Cost</option>
        <option value="stock">Stock</option>
    </select>

    <label for="sortOrder">Sort Order:</label>
    <select id="sortOrder" name="sortOrder" onchange="applySort()">
        <option value="asc">Ascending</option>
        <option value="desc">Descending</option>
    </select>
</div>
<br>

<c:if test="${not empty items}">
    <table border="1">
        <thead>
        <tr>
            <th>Name</th>
            <th>Price(in Rs.)</th>
            <th>Stock</th>
            <th>Description</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${items}" var="item">
            <tr>
                <td>${item.name}</td>
                <td>${item.cost}</td>
                <td>${item.stock}</td>
                <td>${item.description}</td>
                <td>${item.category}</td>
                <td>
                    <button onclick="addToCart('${item.id}', '${item.name}', '${item.cost}', '${item.description}')">
                        Add to Cart
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty items}">
    <p>No items found.</p>
</c:if>

<script>
    $(document).ready(function () {
        var userId = "${userid}";
        if (!userId) {
            window.location.href = "/signup";
        }
    });
</script>
</body>
</html>