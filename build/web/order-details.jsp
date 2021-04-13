<%-- 
    Document   : user-order-details
    Created on : Mar 29, 2021, 8:47:23 PM
    Author     : TRANTATDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="https://kit.fontawesome.com/9507fb7f76.js" crossorigin="anonymous"></script>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="assets/css/common.css">
        <link rel="stylesheet" href="assets/css/navbar.css">
        <link rel="stylesheet" href="assets/css/navbar-categories.css">


        <title>Details</title>
    </head>
    <body class="d-flex flex-column min-vh-100">

       
            
        
        
            <%@include file="component/navbar.jsp"%>
          
        <div class="container mb-5 mt-5">            
            <h3 class="modal-title">Order Details</h3>
            <p class="border-bottom mb-2 mt-2"></p>
            <h4>Product List</h4>
            <div class="products-info mt-2">
                <table class="table mt-1 mb-1">
                    <thead>
                        <tr>
                            <th scope="col">Index</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Sum</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${listOrderDetail ne null}">
                            <c:forEach items="${listOrderDetail}" var="i" varStatus="no">
                                <tr>
                                    <th scope="row">${no.index+1}</th>
                                    <td>${i.productName}</td>
                                    <td>
                                        <fmt:formatNumber type="number" maxFractionDigits="3" value="${i.productPrice}"/><sup>đ</sup>
                                    </td>
                                    <td class="">
                                        ${i.quantity}
                                    </td>
                                    <td>
                                        <fmt:formatNumber type="number" maxFractionDigits="3" value="${i.productPrice * i.quantity}"/><sup>đ</sup>
                                    </td>
                                </tr>
                            </c:forEach> 
                        </c:if>
                    </tbody>
                </table>
            </div>
            <h4 class="mt-5">Shipping Information:</h4>
            <div class="address-info mt-2">
                <table>
                    <tr>
                        <td>Name:</td>
                        <td><b>${addressDetail.name}</b></td>
                    </tr>
                    <tr>
                        <td>Moblie:</td>
                        <td>${addressDetail.phoneNumber}</td>
                    </tr>
                    <tr>
                        <td>Address:</td>
                        <td>${addressDetail.address}</td>
                    </tr>
                </table>
            </div>

            <c:if test="${sessionScope.authSucccess.getRoleId() eq 1}">
                <a href="/admin/redirectpage?page=4" class="btn btn-outline-primary mt-2">Return</a>
            </c:if>
            <c:if test="${sessionScope.authSucccess.getRoleId() ne 1}">
                <a href="user-order" class="btn btn-outline-primary mt-2">Return</a>
                <a href="cancel-order?id=${requestScope.orderId}&status=4" class="btn btn-outline-danger float-end mt-2">Cancel order</a>
            </c:if>
        </div>

        <%@include file="component/footer.jsp" %>
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>