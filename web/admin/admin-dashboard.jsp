<%-- 
    Document   : admin-dashboard
    Created on : Oct 21, 2018, 11:36:18 PM
    Author     : Shado
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="right-side mb-5">
    <div class="row title">
        <div class="col-md-12">
            <h6>
                Dashboard
            </h6>
            <p class="border-bottom"></p>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-md-4">
            <div class="card mb-3" style="max-width: 540px;">
                <div class="row g-0">
                    <div class="col-md-4 d-flex align-items-center">
                        <i class="fa fa-5x fa-book book-icon ms-2 me-2"></i>  
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">Product</h5>
                            <p class="card-text">There are currently ${requestScope.productQuantity} product in store</p>                           
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card mb-3" style="max-width: 540px;">
                <div class="row g-0">
                    <div class="col-md-4 d-flex align-items-center">                
                        <i class="fa fa-5x fa-boxes ms-2 me-2"></i>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">Order</h5>
                            <p class="card-text">There are currently ${requestScope.orderQuantity} orders in total</p>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card mb-3" style="max-width: 540px;">
                <div class="row g-0">
                    <div class="col-md-4 d-flex align-items-center">

                        <i class="fa fa-5x fa-user-alt ms-2 me-2"></i>
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">Account</h5>
                            <p class="card-text">There are currently ${requestScope.accountQuantity} users</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


