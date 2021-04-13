<%-- 
    Document   : admin-orders
    Created on : Oct 21, 2018, 11:36:18 PM
    Author     : Shado
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="right-side mb-5">
    <div class="row title">
        <div class="col-md-12">
            <h6>
                Order Management
            </h6>
            <p class="border-bottom"></p>
        </div>
    </div>

    <div class="row mt-2">
        <div class="col-md-12">
            <table id="orders" table class="table table-bordered" cellspacing="0" width="100%">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Order ID</th>
                        <th>Status</th>
                        <th>Details</th>
                        <th>Change Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.lsOrder}" var="o" varStatus="l">
                        <tr>
                            <td>${l.index+1}</td>
                            <td> ${o.id}</td>
                            <td>
                                <c:if test="${o.status eq 1}">
                                    Packaging
                                </c:if>
                                <c:if test="${o.status eq 2}">
                                    Shipping
                                </c:if>
                                <c:if test="${o.status eq 3}">
                                    Shipped
                                </c:if>
                                <c:if test="${o.status eq 4}">
                                    Cancelled
                                </c:if>   
                            </td>
                            <td><a href="order-detail?id=${o.id}">Details</a></td>
                            <td>
                                <div class="dropdown">
                                    <button class="btn btn-primary dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                                        Change status
                                    </button>
                                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                                        <li><a class="dropdown-item" href="change-status?orderId=${o.id}&status=1">Packaging</a></li>
                                        <li><a class="dropdown-item" href="change-status?orderId=${o.id}&status=2">Shipping</a></li>
                                        <li><a class="dropdown-item" href="change-status?orderId=${o.id}&status=3">Shipped</a></li>
                                        <li><a class="dropdown-item" href="change-status?orderId=${o.id}&status=4">Cancelled</a></li>
                                    </ul>
                                </div>                               
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

