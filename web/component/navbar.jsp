<%-- 
    Document   : navbar
    Created on : Jan 14, 2021, 9:02:44 PM
    Author     : TRANTATDAT
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <!--Go back to index-->
        <a class="navbar-brand ms-2" href="Products">GoldenBook Shop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 flex-grow-1">
                <!--Go back to index-->                
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="Products">Home</a>
                    </li>                             
                <!--See all categories-->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Categories
                    </a>
                    <ul class="dropdown-menu multi-column columns-3" aria-labelledby="navbarDropdown">                                              
                        <div class="row">
                            <!--Foreign book categories, column 1-->
                            <div class="col-sm-3">
                                <ul class="multi-column-dropdown">
                                    <c:forEach items="${sessionScope.lsFrCat}" begin="1" end="${sessionScope.lsFrCat.size()/2}" var="frCat">
                                        <li>
                                            <a href="filter?fr_cat_id=${frCat.id}&type=2">${frCat.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <!--Foreign book categories, column 2-->
                            <div class="col-sm-3">
                                <ul class="multi-column-dropdown">
                                    <c:forEach items="${sessionScope.lsFrCat}" begin="${(sessionScope.lsFrCat.size()/2)+1}" end="${sessionScope.lsFrCat.size()}" var="frCat">
                                        <li>
                                            <a href="filter?fr_cat_id=${frCat.id}&type=2">${frCat.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <!--Vietnamese book categories, column 1-->
                            <div class="col-sm-3">
                                <ul class="multi-column-dropdown">
                                    <c:forEach items="${sessionScope.lsVnCat}" begin="1" end="${sessionScope.lsVnCat.size()/2}" var="vnCat">
                                        <li>
                                            <a href="filter?vn_cat_id=${vnCat.id}&type=3">${vnCat.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <!--Vietnamese book categories, column 2-->
                            <div class="col-sm-3">
                                <ul class="multi-column-dropdown">
                                    <c:forEach items="${sessionScope.lsVnCat}" begin="${(sessionScope.lsVnCat.size()/2)+1}" end="${sessionScope.lsVnCat.size()}" var="vnCat">
                                        <li>
                                            <a href="filter?vn_cat_id=${vnCat.id}&type=3">${vnCat.name}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>                                          
                    </ul>
                </li>
                <!--Cart dropdown-->                                
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Your cart
                        <c:if test="${sessionScope.listCart ne null && sessionScope.listCart.size() ne 0}">
                            <span class="badge rounded-pill bg-info"> ${sessionScope.totalProduct} </span> 
                        </c:if>
                    </a>
                <c:choose>
                    <c:when test="${sessionScope.listCart eq null || sessionScope.listCart.size() eq 0 }">
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <li><p class="dropdown-item">No product in cart</p></li>
                        </ul>
                    </c:when>
                    <c:otherwise>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:forEach items="${sessionScope.listCart}" begin="0" end="2" var="cartProduct">
                                <li>
                                    <div class="dropdown-item">
                                        <div class="card mb-3" style="max-width: 100%;">
                                            <div class="row g-0">
                                                <div class="col-md-4">
                                                    <img class="card-img" style="object-fit: cover" src="assets/images/products/${cartProduct.imgName}" alt="Product image">
                                                </div>
                                                <div class="col-md-8">
                                                    <div class="card-body" >
                                                        <p class="card-text text-wrap">                                           
                                                            <a class="product-name" href="product-detail?id=${cartProduct.id}" style="text-decoration: none; color: #000;">
                                                                ${cartProduct.name}
                                                            </a>                                                        
                                                        </p>
                                                        <p class="card-text text-wrap">In cart: ${cartProduct.quantity}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>                                     
                            </c:forEach>                                            
                            <li><a class="dropdown-item" href="cart.jsp">See all products in cart</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose>
                </li>           
            </ul>
            <c:if test="${sessionScope.authSuccess eq null}">
                <div class="ms-2 me-5">
                    <a class="link-light login-link" href="login.jsp">Login</a>
                </div>
            </c:if>    
            <c:if test="${sessionScope.authSuccess ne null}">
                <div class="dropdown ms-2 me-5">
                    <a class="link-light fw-normal dropdown-toggle account-dropdown" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                        ${sessionScope.authSuccess.getEmail()}
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">


                        <li><a class="dropdown-item" href="user-info">Your Account</a></li>
                        <li><a class="dropdown-item" href="user-order">Your Orders</a></li>

                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </div>
            </c:if>
            <!--Search box-->
            <form class="d-flex mb-2" action="search" method="get">
                <input class="form-control me-2 mt-3" name="name" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success mt-3" type="submit"><i class="fas fa-search"></i></button>
            </form>
        </div>        
    </div>
</nav>
