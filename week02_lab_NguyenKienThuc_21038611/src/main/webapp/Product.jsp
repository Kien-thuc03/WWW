<%--
  Created by IntelliJ IDEA.
  User: FIL
  Date: 28/09/2024
  Time: 6:52 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.iuh.fit.week02_lab_nguyenkienthuc_21038611.models.Product" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Product List</h1>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>
                    <form action="editProduct" method="get" style="display:inline;">
                        <input type="hidden" name="id" value="${product.id}" />
                        <input type="submit" value="Edit" />
                    </form>
                    <form action="deleteProduct" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="${product.id}" />
                        <input type="submit" value="Delete" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<h2>Add New Product</h2>
<form action="addProduct" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required />
    <br />
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required />
    <br />
    <label for="price">Price:</label>
    <input type="number" id="price" name="price" required />
    <br />
    <input type="submit" value="Add Product" />
</form>
</body>
</html>
