<%-- 
    Document   : product-detail
    Created on : Jan 28, 2021, 4:36:52 PM
    Author     : TRANTATDAT
--%>
<%@page import="entity.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <script src="https://kit.fontawesome.com/9507fb7f76.js" crossorigin="anonymous"></script>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <!-- External CSS -->        
        <link rel="stylesheet" href="assets/css/common.css">
        <link rel="stylesheet" href="assets/css/navbar.css">
        <link rel="stylesheet" href="assets/css/navbar-categories.css">
        <link rel="stylesheet" href="assets/css/product-detail.css">

        <title>BookDetails</title>

    </head>
    <body class="d-flex flex-column min-vh-100">
        <%@include file="component/navbar.jsp" %>

        <div class="container mb-5">
            <!--Product image and name, price, quantity, add to cart-->
            <div class="row">
                <!--Product image-->
                <div class="col-md-6 mt-5">
                    <div class="container-img">                       
                        <div class="mySlides">
                            <div class="numbertext">1 / ${requestScope.lsImage.size()+1} </div>                           
                            <img src="assets/images/products/${product.imgName}" style="width:100%">                                                    
                        </div>
                        <c:forEach items="${requestScope.lsImage}" var="image" varStatus="status">
                            <div class="mySlides">
                                <div class="numbertext">${status.count+1} / ${requestScope.lsImage.size()+1}</div>                               
                                <img src="assets/images/products/${image.name}" style="width:100%;object-fit: cover">                               
                            </div>
                        </c:forEach>

                        <a class="prev" onclick="plusSlides(-1)"> ← </a>
                        <a class="next" onclick="plusSlides(1)"> → </a>

                        <div class="row mt-2">

                            <div class="column">
                                <img class="demo cursor" src="assets/images/products/${product.imgName}" style="width:100%" onclick="currentSlide(${1})">
                            </div>
                            <c:forEach items="${requestScope.lsImage}" var="image" varStatus="status">
                                <div class="column">
                                    <img class="demo cursor" src="assets/images/products/${image.name}" style="width:100%" onclick="currentSlide(${status.count + 1})">
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <!--Product name, price, quantity, add to cart-->
                <div class="col-md-1"></div>
                <div class="col-md-5 ">
                    <h1 class="mt-5">${requestScope.product.name}</h1>
                    <h3>
                        <fmt:formatNumber type="number" maxFractionDigits="3" value="${requestScope.product.price}"/><sup>đ</sup>
                    </h3>
                    <p>Số lượng: ${requestScope.product.quantity}</p>
                    <a href="cart?id=${product.id}&inCart=${true}" class="btn btn-danger mb-4">Thêm vào giỏ hàng</a>
                    <p>${requestScope.product.description}</p>
                </div>

            </div>
            <!--Product details-->
            <div class="row mt-5">
                <h4>About this product</h4>
                <table class="table table-striped">
                    <tbody>
                        <tr>
                            <th scope="row" style="padding: 40px; background-color: #ccc">Type</th>
                            <td style="padding: 40px; ">${requestScope.productType.name}</td>                           
                        </tr>
                        <tr>
                            <th scope="row" style="padding: 40px; background-color: #ccc">Author</th>
                            <td style="padding: 40px">${requestScope.productAuthor.name}</td>                            
                        </tr>
                        <tr>
                            <th scope="row" style="padding: 40px; background-color: #ccc">Publisher</th>
                            <td style="padding: 40px">${requestScope.productPublisher.name}</td>                           
                        </tr>
                        <tr>
                            <th scope="row" style="padding: 40px; background-color: #ccc">Category</th>
                            <td style="padding: 40px;">${requestScope.productCategory.name}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!--From this author and publisher-->
            <div class="container-fluid">
                <!--From this author-->
                <div class="row mt-5">                   
                    <h4>From this author</h4>
                    <div id="carouselExampleControls1" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <div class="row">
                                    <c:forEach items="${requestScope.sameAuthor}" begin="0" end="0" var="product">
                                        <div class="card">
                                            <div class="row no-gutters">
                                                <div class="col-md-1"></div>
                                                <div class="col-auto">
                                                    <c:if test="${product.status == 3}">
                                                        <div class="status">
                                                            <img src="assets/images/icon-sale.png" alt="icon-sale">
                                                        </div>
                                                    </c:if>
                                                    <img src="assets/images/products/${product.imgName}" width="400" height="400" alt="">
                                                </div>
                                                <div class="col">
                                                    <div class="card-block px-2 mt-2">
                                                        <h5 class="card-title">                                            
                                                            <a href="product-detail?id=${product.id}&author_id=${product.authorId}&publisher_id=${product.publisherId}" style="text-decoration: none; color: #000;">
                                                                ${product.name}
                                                            </a>
                                                        </h5>
                                                        <p>
                                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price}"/><sup>đ</sup>

                                                        </p>
                                                        <a href="#" class="btn btn-primary mt-auto">Add to cart</a>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                            </div>                                       
                                        </div>
                                    </c:forEach> 
                                </div>
                            </div>
                            <c:forEach items="${requestScope.sameAuthor}" begin="1" var="product">
                                <div class="carousel-item">
                                    <div class="row">
                                        <div class="card">
                                            <div class="row no-gutters">
                                                <div class="col-md-1"></div>
                                                <div class="col-auto">
                                                    <c:if test="${product.status == 3}">
                                                        <div class="status">
                                                            <img src="assets/images/icon-sale.png" alt="icon-sale">
                                                        </div>
                                                    </c:if>
                                                    <img src="assets/images/products/${product.imgName}" width="400" height="400" alt="">
                                                </div>
                                                <div class="col">
                                                    <div class="card-block px-2 mt-2">
                                                        <h5 class="card-title">                                            
                                                            <a href="product-detail?id=${product.id}&author_id=${product.authorId}&publisher_id=${product.publisherId}" style="text-decoration: none; color: #000;">
                                                                ${product.name}
                                                            </a>
                                                        </h5>
                                                        <p>
                                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price}"/><sup>đ</sup>
                                                        </p>
                                                        <a href="#" class="btn btn-primary mt-auto">Add to cart</a>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                            </div>                                       
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>                       
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls1" role="button" data-bs-slide="prev">
                            <span class="btn-outline-dark"><i class="fas fa-arrow-left"></i></span>
                            <span class="visually-hidden">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls1" role="button" data-bs-slide="next">
                            <span class="btn-outline-dark"><i class="fas fa-arrow-right"></i></span>
                            <span class="visually-hidden">Next</span>
                        </a>
                    </div>
                </div>
                <!--From this publisher-->
                <div class="row mt-5">                
                    <h4>From this publisher</h4>
                    <div id="carouselExampleControls2" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <div class="row">
                                    <c:forEach items="${requestScope.samePublisher}" begin="0" end="0" var="product">
                                        <div class="card">
                                            <div class="row no-gutters">
                                                <div class="col-md-1"></div>
                                                <div class="col-auto">
                                                    <c:if test="${product.status == 3}">
                                                        <div class="status">
                                                            <img src="assets/images/icon-sale.png" alt="icon-sale">
                                                        </div>
                                                    </c:if>
                                                    <img src="assets/images/products/${product.imgName}" width="400" height="400" alt="">
                                                </div>
                                                <div class="col">
                                                    <div class="card-block px-2 mt-2">
                                                        <h5 class="card-title">                                            
                                                            <a href="product-detail?id=${product.id}&author_id=${product.authorId}&publisher_id=${product.publisherId}" style="text-decoration: none; color: #000;">
                                                                ${product.name}
                                                            </a>
                                                        </h5>
                                                        <p>
                                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price}"/><sup>đ</sup>
                                                        </p>
                                                        <a href="#" class="btn btn-primary mt-auto">Add to cart</a>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                            </div>                                       
                                        </div>
                                    </c:forEach> 
                                </div>
                            </div>
                            <c:forEach items="${requestScope.samePublisher}" begin="1" var="product">
                                <div class="carousel-item">
                                    <div class="row">
                                        <div class="card">
                                            <div class="row no-gutters">
                                                <div class="col-md-1"></div>
                                                <div class="col-auto">
                                                    <c:if test="${product.status == 3}">
                                                        <div class="status">
                                                            <img src="assets/images/icon-sale.png" alt="icon-sale">
                                                        </div>
                                                    </c:if>
                                                    <img src="assets/images/products/${product.imgName}" width="400" height="400" alt="">
                                                </div>
                                                <div class="col">
                                                    <div class="card-block px-2 mt-2">
                                                        <h5 class="card-title">                                            
                                                            <a href="product-detail?id=${product.id}&author_id=${product.authorId}&publisher_id=${product.publisherId}" style="text-decoration: none; color: #000;">
                                                                ${product.name}
                                                            </a>
                                                        </h5>
                                                        <p>
                                                            <fmt:formatNumber type="number" maxFractionDigits="3" value="${product.price}"/><sup>đ</sup>
                                                        </p>
                                                        <a href="#" class="btn btn-primary mt-auto">Add to cart</a>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                            </div>                                       
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>                       
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleControls2" role="button" data-bs-slide="prev">
                            <span class="btn-outline-dark"><i class="fas fa-arrow-left"></i></span>
                            <span class="visually-hidden">Previous</span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleControls2" role="button" data-bs-slide="next">
                            <span class="btn-outline-dark"><i class="fas fa-arrow-right"></i></span>
                            <span class="visually-hidden">Next</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="component/footer.jsp" %>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <script src="assets/js/product-detail.js"></script>
    </body>
</html>
