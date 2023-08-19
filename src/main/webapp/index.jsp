<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>StarCine - Your Movie Destination</title>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <link rel="stylesheet" href="bootstrap.min.css">
            <link rel="stylesheet" href="bootstrap.min.js">

            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


            <style>
                /* Custom styles here */
                body {
                    background-color: #f0f0f0;
                }

                .navbar {
                    background-color: #1B1212;
                    color: white;
                }

                .navbar-brand {
                    font-size: 24px;
                }

                .content-container {
                    display: flex;
                    justify-content: space-between;
                    margin: 10px;
                    background-color: white;
                    padding: 20px;
                    border: 1px solid #ddd;
                }

                .content {
                    flex: 1;
                    margin-right: 10px;
                }

                .login-form {
                    flex: 1;
                    margin-left: 10px;
                }

                .footer {
                    background-color: #1B1212;
                    color: white;
                    text-align: center;
                    padding: 10px;
                }

                .footer p {
                    color: goldenrod;
                    /* Change the text color of links inside the footer to golden */
                }

                .gradient-custom {
                    /* fallback for old browsers */
                    background: #6a85b6;

                    /* Chrome 10-25, Safari 5.1-6 */
                    background: -webkit-linear-gradient(to right, rgba(106, 133, 182, 0.5), rgba(186, 200, 224, 0.5));

                    /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                    background: linear-gradient(to right, rgba(106, 133, 182, 0.5), rgba(186, 200, 224, 0.5))
                }
            </style>
        </head>

        <body class="gradient-custom">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-md">
                <div class="container">
                    <a class="navbar-brand mx-auto" href="#">
                        <img src="images/logo.png" alt="StarCine Logo" width="200" height="50"
                            class="d-inline-block align-top">

                    </a>
                </div>
            </nav>

            <div class="modal fade" id="successModal" tabindex="-1" role="dialog" aria-labelledby="successModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="successModalLabel">Password Changed Successfully</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Your password has been changed successfully.
                        </div>
                    </div>
                </div>
            </div>


            


            <!-- Main Content -->
            <div class="container mt-5">
                <div class="content-container">
                    <!-- Featured Movies section -->
                    <div class="content">
                        <h2>Featured Movies</h2>
                        <!-- Slideshow using Bootstrap Carousel -->
                        <div id="movieCarousel" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img src="images/banner1.jpg" class="d-block w-100" alt="Movie 1">
                                </div>
                                <div class="carousel-item">
                                    <img src="images/banner2.jpg" class="d-block w-100" alt="Movie 2">
                                </div>
                                <div class="carousel-item">
                                    <img src="images/banner3.jpg" class="d-block w-100" alt="Movie 2">
                                </div>
                                <!-- Add more carousel items here -->
                            </div>
                            <a class="carousel-control-prev" href="#movieCarousel" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#movieCarousel" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                    <!-- Login form -->
                    <div class="login-form">
                        <h2>Login</h2>
                        <form action="login" method="post">
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input name="username" type="text" class="form-control" id="username"
                                    placeholder="Enter your username">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input name="password" type="password" class="form-control" id="password"
                                    placeholder="Enter your password">
                            </div>
                            <button value="Submit" type="submit" class="btn btn-primary">Login</button>
                        </form>
                        <p>New user? <a href="#" data-toggle="modal" data-target="#signUpModal">Sign up here</a></p>
                    </div>
                </div>
            </div>

        

            <!-- Sign Up Modal -->
            <div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel"
                aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="signUpModalLabel">Sign Up</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="register" method="post">
                                <div class="form-group">
                                    <label for="signupUsername">Username</label>
                                    <input name="username" type="text" class="form-control" id="signupUsername"
                                        required>
                                </div>
                                <div class="form-group">
                                    <label for="signupPassword">Password</label>
                                    <input name="password" type="text" class="form-control" id="signupPassword"
                                        required>
                                </div>
                                <div class="form-group">
                                    <label for="signupAge">Age</label>
                                    <input name="age" type="number" class="form-control" id="signupAge" required>
                                </div>
                                <div class="form-group">
                                    <label for="signupGender">Gender</label>
                                    <select name="gender" class="form-control" id="signupGender" required>
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                        <option value="other">Other</option>
                                    </select>
                                </div>
                                <button value="Submit" type="submit" class="btn btn-primary">Sign Up</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

          


            <!-- Footer -->
            <footer class="footer fixed-bottom">
                <p>&copy; 2023 CineStar. All rights reserved.</p>
            </footer>

            <script>
                $(document).ready(function () {
                    var passwordChangeSuccess = <c: out value="${sessionScope.passwordChangeSuccess}" default="false" />;
                    if (passwordChangeSuccess) {
                        $('#successModal').modal('show');
                        setTimeout(function () {
                            $('#successModal').modal('hide');
                // Remove the passwordChangeSuccess attribute from the session
                <%
                                session.removeAttribute("passwordChangeSuccess");
                %>
            }, 3000);
                    }
                });
            </script>



        </body>

        </html>