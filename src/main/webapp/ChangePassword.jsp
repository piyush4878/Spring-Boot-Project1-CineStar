<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Change Password</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>

  <% if(session.getAttribute("username")!=null ){ %>
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card">
            <div class="card-header">
              Change Password for <%= session.getAttribute("username") %>
            </div>
            <div class="card-body">
              <form id="changePasswordForm" action="changePassword" method="post">
                <div class="form-group">
                  <label for="username">Username</label>
                  <input name="username" type="text" class="form-control" id="username" required>
                  <div id="usernameMessage" class="text-muted"></div> <!-- Add this div for the message -->
                </div>
                <div class="form-group">
                  <label for="newPassword">New Password</label>
                  <input name="newPassword" type="password" class="form-control" id="newPassword" required>
                </div>
                <div class="form-group">
                  <label for="confirmPassword">Confirm New Password</label>
                  <input name="confirmPassword" type="password" class="form-control" id="confirmPassword" required>
                  <small id="passwordMatchError" class="text-danger d-none">Passwords do not match</small>
                  <small id="passwordMatchSuccess" class="text-success d-none">Passwords match</small>
                </div>
                <button value="Submit" type="submit" class="btn btn-info" id="changePasswordButton" disabled>Change
                  Password</button>
                <a href="Admin.jsp" class="btn btn-danger">Cancel</a>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    <% } else{%>

      <div class="container">
        <h2 class="display-4">Invalid User</h2>
        <hr>
        <p>Please log in with valid credentials</p>
        <br>
        <a href="index.jsp">Home</a>

        <% }%>

          <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
          <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
          <script>
            $(document).ready(function () {
              const newPasswordInput = $('#newPassword');
              const confirmPasswordInput = $('#confirmPassword');
              const passwordMatchError = $('#passwordMatchError');
              const passwordMatchSuccess = $('#passwordMatchSuccess');
              const changePasswordButton = $('#changePasswordButton');
              const usernameInput = $('#username');
              const usernameMessage = $('#usernameMessage');
              const sessionUsername = '<%= session.getAttribute("username") %>';

              // Attach input event listeners for password matching
              function validatePasswordMatch() {
                if (newPasswordInput.val() === confirmPasswordInput.val()) {
                  passwordMatchError.addClass('d-none');
                  passwordMatchSuccess.removeClass('d-none');
                  changePasswordButton.removeAttr('disabled');
                  return true;
                } else {
                  passwordMatchSuccess.addClass('d-none');
                  passwordMatchError.removeClass('d-none');
                  changePasswordButton.attr('disabled', 'true');
                  return false;
                }
              }

              newPasswordInput.on('input', validatePasswordMatch);
              confirmPasswordInput.on('input', validatePasswordMatch);

              // Check if username matches session username
              usernameInput.on('blur', function () {
                if (usernameInput.val() === sessionUsername) {
                  usernameMessage.text('Username matched!').removeClass('text-danger').addClass('text-success');
                } else {
                  usernameMessage.text('Username does not match the session username.').removeClass('text-success').addClass('text-danger');
                }
              });
            });
          </script>

</body>

</html>