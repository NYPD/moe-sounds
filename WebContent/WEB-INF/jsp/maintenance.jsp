<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Moe Maintenance</title>

    <!-- Styles -->
    <link href="${context}/css/vendor/bootstrap.min.css" rel="stylesheet">
    <link href="${context}/css/vendor/bootstrap-theme.min.css" rel="stylesheet">
    <link href="${context}/css/maintenance.css" rel="stylesheet">

  </head>
  
  <body>
  
    <div class="container">
      
      <nav class="navbar navbar-default">
  	    <div class="container-fluid">
        
  	      <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
  	        <a class="navbar-brand" href="#">
              <img src="https://v.dreamwidth.org/231938/328523">
              <span>Moe Sounds</span>
  	        </a>
  	      </div>
          
          <div class="collapse navbar-collapse">
            <p class="navbar-text navbar-right">
              <span>Signed in as ${moeSoundsSessionBean.user.nickname}</span>
              <img class="user-profile-picture" src="${moeSoundsSessionBean.user.userProfilePicture}">
            </p>
          </div>
          
  	    </div>
  	  </nav>
    
      <h1>Moe Pages</h1>
    </div>
    

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${context}/js/vendor/bootstrap.min.js"></script>
    
  </body>
  
</html>

