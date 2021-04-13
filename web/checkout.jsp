<%-- 
    Document   : base
    Created on : Mar 2, 2021, 8:20:31 PM
    Author     : TRANTATDAT
--%>

<%@page import="entity.Product"%>
<%@page import="java.util.List"%>

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
        <link rel="stylesheet" href="assets/css/navbar.css">
        <link rel="stylesheet" href="assets/css/navbar-categories.css">
        <link rel="stylesheet" href="assets/css/common.css">
        <title>Checkout</title>
    </head>
    <body class="d-flex flex-column min-vh-100">

        <c:if test="${sessionScope.authSuccess==null}">
            <c:redirect url="login.jsp"></c:redirect>
        </c:if>

        <%@include file="component/navbar.jsp"%>

        <div class="container mb-5">            
            <div class="row">
                <div class="col">
                    <h3 class="mt-5">List of product in your cart</h3>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th >Image</th>
                                <th >Product name</th>
                                <th >Price</th>
                                <th class="text-center">Order quantity</th>
                                <th >Total cost</th>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.listCart}" var="cart">
                                <tr>
                                    <td><img src="assets/images/products/${cart.imgName}" width="60"></td>
                                    <td>${cart.name}</td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="3" value="${cart.price}"/><sup>đ</sup></td>
                                    <td class="text-center">${cart.quantity}</td>
                                    <td><fmt:formatNumber type="number" maxFractionDigits="3" value="${cart.price * cart.quantity}"/><sup>đ</sup></td>        
                                </tr>
                            </c:forEach>                            
                        </tbody>
                    </table>

                    <div>
                        <span>
                            <b class="me-4 fs-4">Total sum:&nbsp;<fmt:formatNumber type="number" maxFractionDigits="3" value="${sessionScope.totalMoney}"/><sup>đ</sup></b>                      
                        </span>
                        <a href="remove-cart?id=0" class="btn btn-lg btn-danger me-2 float-end">Cancel</a>
                    </div>
                </div>
                <div class="col ps-5">
                    <h3 class="mt-5">Shipping information: </h3>
                    <div>
                        <div class="m-info">
                            <table>
                                <tr>
                                    <td>Full name:</td>
                                    <td><b>${requestScope.accountDetailInfo.name}</b></td>
                                </tr>
                                <tr>
                                    <td>Phone number:</td>
                                    <td>${requestScope.accountDetailInfo.phone}</td>
                                </tr>                               
                                <tr>
                                    <td>Address:</td>
                                    <td>${requestScope.accountDetailInfo.address}</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><span class="text-success">${requestScope.message}</span></td>
                                </tr>
                            </table>
                        </div>
                        <c:if test="${requestScope.couponUsed eq null}">
                            <div class="coupon mt-3">
                                <div class="row">
                                    <c:forEach items="${requestScope.lsCoupon}" var="c">
                                        <div class="col-12">
                                            <div class="card border-0" style="width: 100%;">
                                                <div class="card-body">
                                                    <h5 class="card-title">${c.code}</h5>                                                
                                                    <p class="card-text">A 10% discount coupon just for you!</p>                                               
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <form action="checkout" method="GET" class="row g-3">
                                        <div class="input-group mb-3">
                                            <input type="text" class="form-control" name="coupon" placeholder="Coupon" aria-label="Recipient's username" aria-describedby="button-addon2">
                                            <button class="btn btn-primary" type="submit" id="button-addon2">Submit</button>
                                        </div>
                                        <input type="text" name="coupon-use" value="true" style="display: none;" />
                                    </form>                                
                                </div>                            
                            </div>       
                        </c:if>
                        <div class="mt-3">                           
                            <div class="form-other-address" style="display: inline-block">
                                <form method="POST" action="checkout" style="display: inline-block">
                                    <input type="text" name="btnOtherAddress" value="false" style="display: none">
                                    <input type="submit" class="btn btn-primary" value="Confirm order to this address">
                                </form>
                            </div>
                            <a class="btn btn-primary ms-3" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapse">
                                Deliver to a different address
                            </a>
                        </div>
                        <div class="collapse" id="collapseExample">
                            <h3 class="mt-5">Enter your shipping information: </h3>
                            <form action="checkout" method="post">
                                <div class="mb-3">
                                    <label for="name" class="form-label">Name:</label>
                                    <input type="text" class="form-control" id="name" name="name" placeholder="e.g: Nguyen Thi A" required="true">
                                </div>
                                <div class="mb-3">
                                    <label for="mobile" class="form-label">Mobile:</label>
                                    <input type="text" class="form-control" id="mobile" name="mobile" placeholder="e.g: 0969123456" required="true">
                                </div>
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address:</label>
                                    <textarea class="form-control" id="address" rows="2" name="address" required="true"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="note" class="form-label">Note:</label>
                                    <textarea class="form-control" id="note" rows="2" name="note"></textarea>
                                </div>
                                <input type="text" name="btnOtherAddress" style="display: none;" value="true">
                                <button type="submit" class="btn btn-lg btn-primary me-2 mt-3">Confirm</button>
                            </form>
                        </div>
                    </div>                  
                </div>
            </div>
        </div>

        <%@include file="component/footer.jsp" %>
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>