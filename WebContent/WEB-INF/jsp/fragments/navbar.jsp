<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="userLoggedIn" value="${not empty moeSoundsSessionBean.user}"/>

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
  
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${context}/random">
        <img src="${context}/images/logo.png">
        <span>${applicationName} ${projectVersion}</span>
      </a>
    </div>
    
    <div class="collapse navbar-collapse" id="navbar-collapse">
      <ul class="nav navbar-nav">
        <li id="home-nav"><a href="${context}/">Home</a></li>
        <li id="all-nav"><a href="${context}/all">All</a></li>
        
        <c:if test="${userLoggedIn}">
          <li id="admin-nav"><a href="${context}/admin/maintenance">Admin</a></li>
        </c:if>
        
      </ul>
      
      <c:if test="${userLoggedIn}">
        <p class="navbar-text navbar-right">
          <span>Signed in as ${moeSoundsSessionBean.user.nickname}</span>
          <img class="user-profile-picture" src="${moeSoundsSessionBean.user.userProfilePicture}">
        </p>
      </c:if>
      
    </div>
    
  </div>
</nav>