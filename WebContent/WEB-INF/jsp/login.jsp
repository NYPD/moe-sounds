<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en-us">

  <head>
  
    <title>Moe Login</title>
  
    <%@ include file = "fragments/meta-block.jsp" %>

    <!-- Styles -->
    <link href="${context}/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/global.css" rel="stylesheet">
    <link href="${context}/css/login.css" rel="stylesheet">
    
  </head>
  
  <body>
  
    <div class="container">
      
      <div class="login-container">
      
        <h2>Please Sign In</h2>
        
        <div class="checkbox">
          <label>
            <input type="checkbox" class="stay-signed-in"> Stay signed in
          </label>
        </div>
        
        <div class="vertical-rule"></div>
        
        <div class="login-options">
          <img class="login-image" alt="Google Login" src="${context}/images/btn_google_signin_dark_normal_web.png" data-href="admin/api/google-oauth-login">
        </div>
        
      </div>
      
      <c:if test="${not empty loginErrorMessage}">
        <div class="alert alert-warning alert-dismissible col-xs-12 col-sm-offset-2 col-sm-8" role="alert">
          <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
          <span>${loginErrorMessage}</span>
        </div>
      </c:if>
      
    </div>
    
    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    <script src="${context}/js/login.js"></script>
    
  </body>
  
</html>