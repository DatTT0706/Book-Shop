<%-- 
    Document   : index
    Created on : Jan 11, 2021, 8:40:25 PM
    Author     : TRANTATDAT
--%>


<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="https://kit.fontawesome.com/9507fb7f76.js" crossorigin="anonymous"></script>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <!-- External CSS -->
        <link rel="stylesheet" type="text/css" href="assets/css/common.css">
        <link rel="stylesheet" type="text/css" href="assets/css/filter.css">
        <link rel="stylesheet" type="text/css" href="assets/css/navbar.css">
        <link rel="stylesheet" type="text/css" href="assets/css/navbar-categories.css">
        <link rel="stylesheet" type="text/css" href="assets/css/product.css">
        <title>BookShop</title>     
    </head>
    <body class="d-flex flex-column min-vh-100">
        <%@include file="component/navbar.jsp" %>

        <div class="container mt-5 mb-5">
            <!--New Arrivals-->
            <div class="row">
                <div class="col-md-12" style="flex-direction: column;">
                    <h2 class="new books" style="text-align: center">New Arrivals</h2>
                    <div class="card-group">
                        <c:forEach items="${requestScope.lsNewProduct}" var="newProduct">
                            <div class="card d-flex align-items-center">
                                <c:if test="${newProduct.status == 3}">
                                    <div class="status">
                                        <img src="assets/images/icon-sale.jpg" alt="icon-sale">
                                    </div>
                                </c:if>
                                <img src="assets/images/products/${newProduct.imgName}" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">
                                        <a class="product-name" href="product-detail?id=${newProduct.id}&author_id=${newProduct.authorId}&publisher_id=${newProduct.publisherId}" style="text-decoration: none; color: #000;">${newProduct.name}</a>
                                    </h5>                                   
                                    <p class="card-text">
                                        <fmt:formatNumber type="number" maxFractionDigits="3" value="${newProduct.price}"/><sup>đ</sup>
                                    </p>                       
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>           
            <div class="row mt-4">
                <!--Filter for products-->
                <div class="col-md-12 col-lg-2 filter-tags">
                    <%@include file="component/filter-tags.jsp" %>
                </div>
                <!--Display all products-->
                <div class="col-md-12 col-lg-10">
                    <!--Order product-->
                    <div class="order mt-2">
                        <h6> Order by:
                            <span class="float-end sort" style="font-size: 1rem;">                               
                                <a class="sorting" href="Products?flag=${1}">Ascending price</a>
                                <a class="sorting ms-3" href="Products?flag=${2}">Descending price</a>
                            </span>
                        </h6>                      
                    </div>
                    <div class="row">
                        <c:forEach items="${requestScope.lsProduct}" var="product">
                            <div class="col-sm-12 col-md-6 col-lg-4 mb-4 d-flex align-items-stretch">                          
                                <div class="card mx-auto flex-fill">
                                    <c:if test="${product.status == 3}">
                                        <div class="status">
                                            <img src="assets/images/icon-sale.png" alt="icon-sale">
                                        </div>
                                    </c:if>
                                    <img src="assets/images/products/${product.imgName}" class="card-img-top" alt="Product image" width="400" height="400">
                                    <div class="card-body d-flex flex-column">
                                        <h5 class="card-title">                                            
                                            <a class="product-name" href="product-detail?id=${product.id}&author_id=${product.authorId}&publisher_id=${product.publisherId}" style="text-decoration: none; color: #000;">
                                                ${product.name}
                                            </a>
                                        </h5>
                                        <p>
                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price}"/><sup>đ</sup>
                                        </p>
                                        <c:if test="${product.quantity ne 0}">
                                            <a href="cart?id=${product.id}" class="btn btn-outline-primary mt-auto">Add to cart</a>
                                        </c:if>
                                        <c:if test="${product.quantity eq 0}">
                                            <p class="text-muted">Not available</p>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>                       
                    </div>
                </div>
            </div>
        </div>

        <nav aria-label="paging" class="mb-5">
            <ul class="pagination justify-content-center">
                <c:forEach items="${requestScope.lsPage}" var="page">
                    <li class="page-item ${requestScope.page == page ? "active" : ""}">
                        <a class="page-link" href="Products?page=${page}&flag=${requestScope.flagOrder}">${page}</a>
                    </li>
                </c:forEach>
            </ul>
        </nav>

        <%@include file="component/footer.jsp" %>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
