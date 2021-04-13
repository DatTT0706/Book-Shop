<%-- 
    Document   : user-profile
    Created on : Mar 25, 2021, 6:45:21 PM
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
        <link rel="stylesheet" href="assets/css/user-profile.css">


        <title>Your profile</title>
    </head>

    <body class="d-flex flex-column min-vh-100">
        <c:if test="${sessionScope.authSuccess eq null}">
            <c:redirect url="Products"></c:redirect>
        </c:if>
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
                                    Account Information                                   
                                </h6>
                                <p class="border-bottom"></p>
                            </div>
                        </div>
                        <div class="row mt-1">
                            <div class="col-md-12">
                                <div class="m-info">
                                    <table>
                                        <tr>
                                            <td>Email:</td>
                                            <td><b>${sessionScope.authSuccess.email}</b></td>
                                        </tr>
                                        <tr>
                                            <td>Password:</td>
                                            <td>
                                                <a href="javascript:void(0)" data-bs-toggle="collapse" data-bs-target="#changePassword" aria-expanded="false" aria-controls="collapseExample">Change Password</a>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Account role:</td>
                                            <td>${sessionScope.authSuccess.roleId == 1 ? "Admin" : "Guest"}</td>
                                        </tr>
                                        <tr>
                                            <td>Status:</td>
                                            <td>
                                                <c:if test="${sessionScope.authSuccess.status == 1}">
                                                    <span class="active">Active</span>
                                                </c:if>
                                                <c:if test="${sessionScope.authSuccess.status != 1}">
                                                    <span class="deactive">Locked</span>
                                                </c:if>
                                            </td>
                                        </tr>

                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="row title mt-3">
                            <div class="col-md-12">
                                <h6>
                                    Coupon
                                </h6>
                                <p class="border-bottom"></p>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${requestScope.lsCoupon.size() eq 0}">
                                <p>You have no coupon</p>
                            </c:when>
                            <c:otherwise>
                                <div class="coupon m-info">'
                                    <div class="row">
                                        <c:forEach items="${requestScope.lsCoupon}" var="c">                                                                     <div class="col-12">
                                                <div class="card border-0" style="width: 100%;">
                                                    <div class="card-body">
                                                        <h5 class="card-title">${c.code}</h5>                                                
                                                        <p class="card-text">A 10% discount coupon just for you!</p>                                               
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>  
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>                       
                        <div class="row mt-3">
                            <div class="col-md-12">
                                <div class="collapse mb-2" id="changePassword">
                                    <div class="card">
                                        <div class="card-body">
                                            <form action="changepassword" method="post">
                                                <div class="form-group col-md-6 mt-3">
                                                    <input type="password" class="form-control" name="password" placeholder="Your current password">
                                                </div>
                                                <div class="form-group col-md-6 mt-3">
                                                    <input type="password" class="form-control" name="newPassword" placeholder="Your new password">
                                                </div>
                                                <div class="form-group col-md-6 mt-3">
                                                    <input type="password" class="form-control" name="newPasswordCf" placeholder="Confirm password">
                                                </div>
                                                <input type="submit" class="btn btn-success btn-sm ml-3 mr-5 mt-3" name="btnChangePassword" value="Change password">

                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row title">
                            <div class="col-md-12">
                                <h6>
                                    Personal Information
                                </h6>
                                <p class="border-bottom"></p>
                                <span style="color: #00bc90">${requestScope.message}</span>
                            </div>
                        </div> 
                        <div class="row mt-1">
                            <div class="col-md-12">
                                <div class="m-info">
                                    <table>
                                        <tr>
                                            <td>Full name:</td>
                                            <td><b>${accountDetailInfo.name}</b></td>
                                        </tr>
                                        <tr>
                                            <td>Phone number:</td>
                                            <td>${accountDetailInfo.phone}</td>
                                        </tr>
                                        <tr>
                                            <td>Sex:</td>
                                            <td>${accountDetailInfo.gender == true ? "Male" : "Female"}</td>
                                        </tr>
                                        <tr>
                                            <td>Address:</td>
                                            <td>${accountDetailInfo.address}</td>
                                        </tr>
                                    </table>
                                    <button type="button" class="btn btn-success btn-sm mt-2" data-bs-toggle="collapse" data-bs-target="#updateInfo" aria-expanded="false" aria-controls="collapseExample">
                                        Edit personal information
                                    </button>
                                </div>
                            </div>
                        </div>

                        <div class="row mt-3">
                            <div class="col-md-12">
                                <div class="collapse mb-2" id="updateInfo">
                                    <div class="card">
                                        <div class="card-body">
                                            <form action="update-info" method="post">
                                                <div class="form-group col-md-6 mb-2">
                                                    <input type="text" class="form-control" name="name" placeholder="Full name: ">
                                                </div>
                                                <div class="form-group col-md-6 mb-2">
                                                    <input type="text" class="form-control" name="phone" placeholder="Phone number: ">
                                                </div>
                                                <div class="form-group col-md-6 mb-2">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" name="gender" value="1">
                                                        <label class="form-check-label" for="inlineRadio1">Male</label>
                                                    </div>
                                                    <div class="form-check form-check-inline mb-2">
                                                        <input class="form-check-input" type="radio" name="gender" value="0">
                                                        <label class="form-check-label" for="inlineRadio2">Female</label>
                                                    </div>
                                                </div>
                                                <div class="form-group col-md-12 mb-2">
                                                    <textarea class="form-control" rows="3" name="address" placeholder="Address"></textarea>
                                                </div>
                                                <input type="submit" class="btn btn-success btn-sm ml-3" name="btnUpload" value="Confirm">
                                            </form>
                                        </div>
                                    </div>
                                </div>
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
