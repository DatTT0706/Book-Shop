<%-- 
    Document   : admin-coupon
    Created on : Mar 31, 2021, 10:29:19 PM
    Author     : TRANTATDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="right-side mb-5">
    <div class="row title">
        <div class="col-md-12">
            <h6>
                Coupon management
            </h6>
            <p class="border-bottom"></p>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-md-12">
            <table id="coupon" table class="table table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Coupon</th>
                        <th>Account ID</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.lsCoupon}" var="i" varStatus="no">
                    <tr>
                        <td>${no.index+1}</td>
                        <td>${i.code}</td>
                        <td>${i.accountId}</td>
                        <td>${i.used?"New":"Used"}</td>
                        <td>
                            <a href="remove-coupon?id=${i.id}" class="btn btn-danger">Delete</a>                            
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
