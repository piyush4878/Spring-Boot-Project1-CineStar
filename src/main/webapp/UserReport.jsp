<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@ page import="java.sql.*" %>

    <%@taglib prefix="c" uri="jakarta.tags.core" %>

      <!DOCTYPE html>
      <html>

      <head>
        <meta charset="ISO-8859-1">
        <title>User Report</title>
        <link rel="stylesheet" href="bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="bootstrap.min.js">
      </head>

      <style>
        body {
          background-color: #28282B;
        }

        .myNavbar {
          background-color: #1B1212;
          color: white;
          /* Add a contrasting text color */
        }

        .navbar-nav a {
          color: white;
        }
      </style>

      <body>
        <% if(session.getAttribute("username")!=null ){ %>
          <div>
            <nav class="myNavbar navbar navbar-expand-lg  px-3">
              <div class="d-flex justify-content-start">
                <img
                  src="data:image/png;base64,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">
                <a class="navbar-brand mx-3" href="Admin.jsp" style="color: white">
                  <%= session.getAttribute("username") %>
                </a>
              </div>

              <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>

              <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarSupportedContent">
                <ul class="navbar-nav">
                  <li class="nav-item active text-secondary">
                    <a class="nav-link " href="Admin.jsp">Dashboard</a>
                  </li>


                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="settingsDropdown" role="button"
                      data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      Settings
                    </a>
                    <div class="dropdown-menu" aria-labelledby="settingsDropdown">
                      <a class="dropdown-item" href="ChangePassword.jsp" style="color: black">Change Password</a>
                    </div>
                  </li>

                  <li class="nav-item">
                    <a class="nav-link" href="logout" style="color: white">Log Out</a>
                  </li>
                </ul>
              </div>
            </nav>
          </div>

          <hr>


          <div class="container vh-100 my-3  shadow p-3 mb-5 bg-light ">

            <div class="card-deck-wrapper">
              <div class="container mt-4">
                <h2>User Records</h2>
                <table class="table table-bordered table-hover my-3">

                  <tr>
                    <th>ID</th>
                    <th>User Name</th>
                    <th>Password</th>
                    <th>Age</th>
                    <th>Gender</th>
                  </tr>



                  <c:forEach items="${userobj}" var="cm">
                    <tr>
                      <td>${cm.userId}</td>
                      <td>${cm.username}</td>
                      <td>${cm.password}</td>
                      <td>${cm.age}</td>
                      <td>${cm.gender}</td>
                    </tr>
                  </c:forEach>



                </table>
              </div>

            </div>
          </div>

          </div>

          <div class="text-center">

            <a href="Admin.jsp" class="btn btn-dark mr-2">Back</a>
          </div>
          <% } else{%>

            <div class="container">
              <h2 class="display-4">Invalid User</h2>
              <hr>
              <p>Please log in with valid credentials</p>
              <br>
              <a href="index.jsp">Home</a>

              <% }%>




      </body>

      </html>