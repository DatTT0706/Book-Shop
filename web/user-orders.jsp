<%-- 
    Document   : user-orders
    Created on : Mar 26, 2021, 8:04:41 AM
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


        <title>Your Orders</title>
    </head>
    <body class="d-flex flex-column min-vh-100">

        <%@include file="../component/navbar.jsp"%>

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
                                <div class="side-nav">
                                    <ul class="navbar-nav ml-auto">
                                        <li class="nav-item">
                                            <a class="nav-link" href="user-redirect?page=1">
                                                <i class="mr-2"></i> Account Information
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="user-redirect?page=2">
                                                <i class="mr-2"></i> Orders
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-9">
                    <div class="right-side">
                        <div class="row title">
                            <div class="col-md-12">
                                <h6>
                                    Orders
                                </h6>
                                <c:if test="${requestScope.message ne null}">
                                    <p class="mt-2 text-success"></p>
                                </c:if>                                
                            </div>
                        </div>

                        <div class="row mt-2">
                            <div class="col-md-12">
                                <table id="orders" table class="table table-bordered" cellspacing="0" width="100%">
                                    <thead>
                                        <tr>
                                            <th>Index</th>
                                            <th>OrderID</th>
                                            <th>Price</th>
                                            <th>Status</th>
                                            <th>Note</th>
                                            <th>Detail</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.lsOrder}" var="i" varStatus="no">
                                            <tr>
                                                <td>${no.index+1}</td>
                                                <td>${i.id}</td>                                                           
                                                <td><fmt:formatNumber type="number" maxFractionDigits="3" value="${i.totalMoney}"/><sup>đ</sup></td>
                                                <td class="text-center">
                                                    <c:if test="${i.status == 1}">
                                                        <button type="button" class="btn btn-primary br" data-toggle="tooltip" data-placement="right" title="Processing"></button>
                                                    </c:if>
                                                    <c:if test="${i.status == 2}">
                                                        <button type="button" class="btn btn-warning br" data-toggle="tooltip" data-placement="right" title="Delivering"></button>
                                                    </c:if>
                                                    <c:if test="${i.status == 3}">
                                                        <button type="button" class="btn btn-warning br" data-toggle="tooltip" data-placement="right" title="Delivered"></button>
                                                    </c:if>
                                                    <c:if test="${i.status == 4}">
                                                        <button type="button" class="btn btn-danger br" data-toggle="tooltip" data-placement="right" title="Cancelled"></button>
                                                    </c:if>   
                                                </td>
                                                <td>${i.note == null ? "--" : i.note}</td>
                                                <td><a href="order-detail?id=${i.id}">Details</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../component/footer.jsp" %>
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
