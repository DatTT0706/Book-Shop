<%-- 
    Document   : admin-products
    Created on : Oct 21, 2018, 11:36:18 PM
    Author     : Shado
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="right-side mb-5">
    <div class="row title">
        <div class="col-md-12">
            <h6>
                Add a product
            </h6>
            <p class="border-bottom"></p>
        </div>
    </div>
    <div class="row mt-2">
        <div class="col-md-6">
            <button class="btn btn-success" type="button" data-bs-toggle="collapse" data-bs-target="#addProduct" aria-expanded="false" aria-controls="collapseExample">
                Add new product
            </button>
            <p class="mt-3">Click the button to add a new product.</p>
        </div>
        <div class="col-md-6">
            <span style="color: #ed1d27">${message}</span>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="collapse mb-2" id="addProduct">
                <div class="card">
                    <div class="card-body">
                        <form action="addproduct" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
                            <div class="row px-3">
                                <div class="form-group col-md-6 mb-5">
                                    Product name:
                                    <input type="text" class="form-control" name="name" id="name" placeholder="Name of book">
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
                                    <input type="text" class="form-control" name="price" placeholder="Book price(VND)">
                                </div>
                                <div class="form-group col-md-6 mb-5">
                                    Quantity:
                                    <input type="text" class="form-control" name="quantity" placeholder="Book quantity">
                                </div>
                                <div class="form-group col-md-6 mb-5">
                                    Status
                                    <select class="form-control" name="status">
                                        <option value="1">In stock</option>
                                        <option value="2">Out of stock</option>
                                        <option value="3">Sale</option>
                                        <option value="4">Not Availible</option>
                                    </select>
                                </div>
                                <div class="form-group col-md-6 mb-5">
                                    Choose Images: 
                                    <br>
                                    <input type="file" class="form-control-file" name="fileUp" multiple required/>
                                </div>
                                <div class="form-group col-md-12 mb-5">
                                    <textarea class="form-control" rows="3" name="description" placeholder="Description"></textarea>
                                </div>
                                <input type="submit" class="btn btn-success ms-3" name="btnUpload" value="Add product">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row title">
        <div class="col-md-12">
            <h6>
                Product list
            </h6>
        </div>
    </div>  
    <div class="row mt-2">
        <div class="col-md-12">
            <table id="products" table class="table table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Book ID</th>
                        <th>Book Name</th>
                        <th>Book Quantity</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Update</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${products}" var="i" varStatus="no">
                    <tr>
                        <td>${no.index+1}</td>
                        <td>${i.id}</td>
                        <td>${i.name}</td>
                        <td>${i.quantity}</td>
                        <td><fmt:formatNumber type="number" maxFractionDigits="3" value="${i.price}"/><sup>đ</sup></td>
                    <td>${i.status == 1 ? "In stock" : i.status == 2 ? "Out of stock" : i.status==3 ? "Sale" :"Not Availible"}</td>
                    <td>
                        <a href="update-product?id=${i.id}">Update</a>
                    </td>
                    <td>
                        <a href="remove-product?id=${i.id}" onclick="return confirm('Are you sure you want to delete this product?')">Remove</a>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

