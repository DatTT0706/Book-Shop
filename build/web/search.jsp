

<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page errorPage="error.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="https://kit.fontawesome.com/9507fb7f76.js" crossorigin="anonymous"></script>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <!-- External CSS -->
        <link rel="stylesheet" href="assets/css/common.css">
        <link rel="stylesheet" href="assets/css/navbar.css">
        <link rel="stylesheet" href="assets/css/navbar-categories.css">
        <link rel="stylesheet" href="assets/css/filter.css">

        <title>SearchProduct</title>


    </head>
    <body class="d-flex flex-column min-vh-100">
        <%@include file="component/navbar.jsp" %>
        <div class="container mt-5 mb-5">
            <div class="row">
                <div class="col-md-12 col-lg-4 col-xl-2 filter-tags">
                    <!--Filter for products-->
                    <%@include file="component/filter-tags.jsp" %>
                </div>
                <div class="col-md-12 col-lg-8 col-xl-10">
                    <h1>Search result for '${requestScope.name}'</h1>
                    <c:choose>           
                        <c:when test = "${requestScope.lsProduct.size() eq 0}">
                            <!--No product found-->
                            No products found with name '${requestScope.name}'
                        </c:when>
                        <c:otherwise>
                            <!--Display found product-->
                            <div class="row">
                                <c:forEach items="${requestScope.lsProduct}" var="product">
                                    <div class="col-sm-12 col-md-6 col-lg-6 col-xl-4 mb-4 d-flex align-items-stretch">
                                        <div class="card mx-auto flex-fill">
                                            <c:if test="${product.status == 3}">
                                                <div class="status">
                                                    <img src="assets/images/icon-sale.png" alt="icon-sale">
                                                </div>
                                            </c:if>
                                            <img src="assets/images/products/${product.imgName}" class="card-img-top" alt="Product image" width="400" height="400">
                                            <div class="card-body d-flex flex-column">
                                                <h5 class="card-title">
                                                    <!-- Khi lam viec voi link truyen vao ?ten_bien -->
                                                    <!-- Truyen nhieu bien ?id=1 & name="xxx" &... -->
                                                    <!-- truyen bao nhieu bien get bay nhieu lan ben servlet-->
                                                    <a href="product-detail?id=${product.id}&author_id=${product.authorId}&publisher_id=${product.publisherId}" style="text-decoration: none; color: #000;">
                                                        ${product.name}
                                                    </a>
                                                </h5>
                                                <p>
                                                    <fmt:formatNumber value="${product.price}" groupingUsed="true" type="currency" currencySymbol="₫" maxFractionDigits="0"/>
                                                </p>
                                                <a href="cart?id=${product.id}" class="btn btn-outline-primary mt-auto">Add to cart</a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>        
        </div>

        <%@include file="component/footer.jsp" %>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>