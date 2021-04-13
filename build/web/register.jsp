<%-- 
    Document   : register
    Created on : Mar 30, 2021, 10:55:41 AM
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
        <link rel="stylesheet" type="text/css" href="assets/css/form.css">
        <link rel="stylesheet" type="text/css" href="assets/css/animation.css">


        <title>Register</title>
    </head>
    <body>

        <!-- particles.js container -->
        <div id="particles-js"></div>      
        <!-- particles.js lib - https://github.com/VincentGarreau/particles.js -->
        <script src="http://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script> 
        <!-- stats.js lib -->
        <script src="http://threejs.org/examples/js/libs/stats.min.js"></script>


        <div id="intro">
            <div class="middle signin">
                <div class="login-panel">
                    <div class="logo text-center">
                        <a href="Products">Golden Book Shop</a>
                    </div>

                    <p class="text-center text-success fs-3">Register an account</p>
                    <form action="register" method="post">
                        <div class="form-group">
                            <input type="email" id="email" name="email" class="form-control" placeholder="Your Email" required="true">
                        </div>                       
                        <div class="form-group">
                            <input type="password" name="password" class="form-control" placeholder="Your Password" required="true">
                        </div>
                        <div class="form-group">
                            <input type="text" name="name" class="form-control" placeholder="Your Name" required="true">
                        </div> 
                        <div class="form-group">
                            <input type="text" name="phone" class="form-control" placeholder="Your Phone Number" required="true">
                        </div> 
                        <div class="form-check">            
                            <label class="form-check-label mt-1 ms-1" for="radio-male">
                                Male
                            </label>
                            <input class="form-check-input" type="radio" name="gender" id="radio-male" value="true" checked>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label mt-1 ms-1" for="radio-female">
                                Female
                            </label>
                            <input class="form-check-input" type="radio" name="gender" id="radio-female" value="false">                           
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" name="address" id="address" aria-label="With textarea" placeholder="Your Address" required="true"></textarea>
                        </div>
                        <div class="form-group">
                            <div class="col">
                                <button type="submit" class="btn btn-sm">
                                    <i class="fas fa-sign-in-alt mr-1"></i> Register
                                </button>
                            </div>
                        </div> 
                    </form>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="assets/js/jquery/jquery.min.js"></script>
        <script src="assets/js/bootstrap/bootstrap.min.js"></script>

        <script src="assets/js/animation.js"></script>

        <!-- Option 1: Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
    </body>
</html>
