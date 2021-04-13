<%-- 
    Document   : filter-tags
    Created on : Feb 17, 2021, 12:19:50 PM
    Author     : TRANTATDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row title ">
    <div class="col-md-12">
        <h4>Filter Products</h4>
        <p class="border-bottom"></p>
    </div>
</div>
<!--Foreign or Vietnamese books-->
<div class="col-md-12">
    <h6>Languages</h6>
</div>
<div class="filter tags">
    <c:forEach items="${sessionScope.lsType}" var="type">
        <a class="filter tags mt-1" href="filter?type_id=${type.id}&type=1">${type.name}</a>
    </c:forEach>
</div>
<p class="border-bottom mt-3"></p>

<!--Foreign Books tags-->
<div class="col-md-12">
    <h6>Foreigns Categories</h6>
</div>
<div class="filter tags">
    <c:forEach items="${sessionScope.lsFrCat}" begin="1" var="frCat">
        <a class="filter tags mt-1" href="filter?cat_id=${frCat.id}&type=2">${frCat.name}</a>
    </c:forEach>
</div>
<p class="border-bottom mt-3"></p>

<!--Vietnamese Books tags-->
<div class="col-md-12">
    <h6>Vietnamese Categories</h6>
</div>
<div class="filter tags">
    <c:forEach items="${sessionScope.lsVnCat}" begin="1" var="vnCat">
        <a class="filter tags mt-1" href="filter?cat_id=${vnCat.id}&type=3">${vnCat.name}</a>
    </c:forEach>
</div>

