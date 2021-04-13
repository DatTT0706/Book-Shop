<%-- 
    Document   : update-product
    Created on : Mar 30, 2021, 1:28:56 PM
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


        <title>Update product</title>
    </head>
    <body class="d-flex flex-column min-vh-100">

        <%@include file="../component/admin-navbar.jsp"%>

        <div class="container mb-5 mt-5">
            <div class="form-update">
                <form action="update-product" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
                    <div class="row px-3">
                        <div class="form-group col-md-6 mb-5">
                            Product name:
                            <input type="text" class="form-control" name="name" id="name" value="${requestScope.product.name}" placeholder="Name of book">
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Author name:
                            <select class="form-control" name="author">                                      
                                <c:forEach items="${requestScope.author}" var="i" varStatus="no">
                                    <option value="${i.id}">${i.name}</option>
                                </c:forEach>
                            </select>                                   
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Publisher name:
                            <select class="form-control" name="publisher">                                      
                                <c:forEach items="${requestScope.publisher}" var="i" varStatus="no">
                                    <option value="${i.id}">${i.name}</option>
                                </c:forEach>
                            </select>                                   
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Type:
                            <select class="form-control" name="type">                                      
                                <c:forEach items="${requestScope.type}" var="i" varStatus="no">
                                    <option value="${i.id}">${i.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Foreign Category (VN_book if type is Vietnamese book):
                            <select class="form-control" name="fr_category">                                     
                                <c:forEach items="${requestScope.fr_categories}" var="i" varStatus="no">
                                    <option value="${i.id}">${i.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Vietnamese Category (FR_book if type is Foreign book):
                            <select class="form-control" name="vn_category">
                                <c:forEach items="${requestScope.vn_categories}" var="i" varStatus="no">
                                    <option value="${i.id}">${i.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Price: 
                            <input type="text" class="form-control" value="${requestScope.product.price}" name="price" placeholder="Book price(VND)">
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Quantity:
                            <input type="text" class="form-control" name="quantity" value="${requestScope.product.quantity}" placeholder="Book quantity">
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Status
                            <select class="form-control" name="status">
                                <option value="1">In stock</option>
                                <option value="2">Out of stock</option>
                                <option value="3">Sale </option>
                                <option value="4">Not Availible </option>    
                            </select>
                        </div>
                        <div class="form-group col-md-6 mb-5">
                            Choose Images: 
                            <br>
                            <input type="file" class="form-control-file" name="fileUp" multiple required />

                        </div>
                        <div class="form-group col-md-12 mb-5">
                            <textarea class="form-control" rows="3" name="description" placeholder="Description"></textarea>
                        </div>
                        <div class="form-group col-md-12 mb-5">
                            <input type="text" id="oldId" name="oldId" value="${requestScope.product.id}" style="display: none" />
                        </div>   
                        <input type="submit" class="btn btn-success ms-3" name="btnUpload" value="Save product">
                    </div>  
                </form>
            </div>

        </div>

        <%@include file="../component/footer.jsp" %>
        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
