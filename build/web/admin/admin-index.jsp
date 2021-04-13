<%-- 
    Document   : admin-management
    Created on : Mar 25, 2021, 9:47:00 AM
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
        <link rel="stylesheet" href="assets/css/admin-index.css">

        <title>Management</title>
    </head>
    <body class="d-flex flex-column min-vh-100">

        <%@include file="../component/admin-navbar.jsp"%>

        <div class="container mb-5 mt-5">            
            <div class="row">
                <div class="col-md-3">
                    <div class="left-side">
                        <div class="row title">
                            <div class="col-md-12">
                                <h6>Management List</h6>
                            </div>
                            <p class="border-bottom"></p>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <span style="color: #00bc90">${message}</span>
                            </div>
                            <div class="col-md-12">
                                <div class="side-nav">
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item">
                                            <a class="nav-link" href="redirectpage?page=1">
                                                <i class="mr-2"></i> Dashboard
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="redirectpage?page=2">
                                                <i class="mr-2"></i> Manage Account
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="redirectpage?page=3">
                                                <i class="mr-2"></i> Manage Product
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="redirectpage?page=4">
                                                <i class="mr-2"></i> Manage Orders
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="redirectpage?page=5">
                                                <i class="mr-2"></i> Manage Coupon
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">                   
                    <c:if test="${page == 1 || page eq null}">
                        <%@include file="admin-dashboard.jsp"%>
                    </c:if>
                    <c:if test="${page == 2}">
                        <%@include file="admin-accounts.jsp"%>
                    </c:if>
                    <c:if test="${page == 3}">
                        <%@include file="admin-products.jsp"%>
                    </c:if>
                    <c:if test="${page == 4}">
                        <%@include file="admin-orders.jsp"%>
                    </c:if>
                    <c:if test="${page == 5}">
                        <%@include file="admin-coupon.jsp"%>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <%@include file="../component/footer.jsp" %>
    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>
