<%-- 
    Document   : admin-navbar
    Created on : Mar 25, 2021, 5:00:48 PM
    Author     : TRANTATDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <!--Go back to index-->
        <a class="navbar-brand ms-2" href="redirectpage">GoldenBook Shop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse float-end justify-content-end" id="navbarSupportedContent">                                  
            <div class="dropdown ms-2 me-5">
                <a class="link-light fw-normal dropdown-toggle account-dropdown" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    ${sessionScope.authSuccess.getEmail()}
                </a>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">                       
                    <li><a class="dropdown-item" href="../logout">Logout</a></li>
                </ul>
            </div>         
        </div>        
    </div>
</nav>
