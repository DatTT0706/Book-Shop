<%-- 
    Document   : active-account
    Created on : Mar 30, 2021, 10:44:34 PM
    Author     : TRANTATDAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Active</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

        <!-- Custom CSS -->
        <link rel="stylesheet" type="text/css" href="assets/css/form.css">
        <link rel="stylesheet" type="text/css" href="assets/css/animation.css">

        <script src="https://kit.fontawesome.com/9507fb7f76.js" crossorigin="anonymous"></script>
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
                    <p class="notification"> Enter your active code </p>
                    <form action="active" method="post">
                        <div class="form-group">
                            <input type="text" name="active-code" class="form-control" placeholder="Your Active Code" >
                        </div>                       
                        
                        <div class="form-group">
                            <div class="col">
                                <button type="submit" class="btn btn-sm">
                                    <i class="fas fa-sign-in-alt mr-1"></i> Submit
                                </button>
                            </div>
                        </div>                       
                    </form>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="assets/js/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
        <script src="assets/js/bootstrap/bootstrap.min.js"></script>

        <script src="assets/js/animation.js"></script>
    </body>
</html>
