<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en-us">

  <head>
    
    <title>${applicationName}</title>
    
    <%@ include file = "fragments/meta-block.jsp" %>
    
    <link href="https://fonts.googleapis.com/css?family=Unica+One|Passion+One|Roboto|Oswald|Anton|Exo+2:900|Raleway:700" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" type="text/css" href="${context}/css/home.css?v=${projectVersion}">
    
    <!-- Inline Styles from page object. If there is not page background randomize between two for now -->
    <c:choose>
      <c:when test="${not empty page.getMediaWithMediaType('PAGE_BACKGROUND')}">
        <c:set var="backgroundImageUrl" value="url('${context}/get-page-media/${page.pageId}-PAGE_BACKGROUND')"/>
      </c:when>
      <c:otherwise>
        <c:choose>
          <c:when test="${randomNumber == 0}">
            <c:set var="backgroundImageUrl" value="url('${context}/images/background-grey.png')"/>
            <style>
              .main .main-inner {
                background: rgba(73,82,86,.8);
              }
            </style>
          </c:when>
          <c:otherwise>
            <c:set var="backgroundImageUrl" value="url('${context}/images/background-pink.png')"/>
            <style>
              .main .main-inner {
                background: rgba(224,90,179,.8);
              }
            </style>
          </c:otherwise>
        </c:choose>
      </c:otherwise>
    </c:choose>
    <style>
      .main {
        background-image: ${backgroundImageUrl};
      }
      .thumb-large {
        background-image: url('${context}/get-page-media/${page.pageId}-BACKGROUND_INNER');
      }
    </style>
    <style>${page.css}</style>
    
  </head>
  <body>
  
    <nav>
      <a href="https://github.com/NYPD/moe-sounds" target="_blank">about</a>
      <a href="${context}/all">all</a>
    </nav>

    <div class="main">

      <div class="main-inner">
        
        <div class="thumb-large"></div>
        
        <div class="hit-count">
          <span id="count">${page.clickCount}</span>
        </div>

        <div class="sound-play">
          <c:set var="carouselImage" value="${page.getMediaWithMediaType('CAROUSEL_IMAGE')}"/>
          <c:if test="${not empty carouselImage}">
            <div class="carousel-container">
              <img class="page-carousel-image noselect" src="data:${carouselImage.fileType};base64,${carouselImage.fileDataAsBase64}">
            </div>
          </c:if>
          <p class="play-display noselect">PLAY</p>
        </div>

        <div class="sound-name">
          <p class="noselect">${page.pageName}</p>
        </div>
        
        <div class="sound-footer">
        
          <div class="share-links">
            <div class="social-media facebook noselect">
              <div class="fb-share-button" data-href="${applicationUrl}" data-layout="button_count"></div>
            </div>
  
            <div class="social-media twitter noselect">
              <a href="https://twitter.com/share" class="twitter-share-button" data-text="Check out this moe site:" data-url="${applicationUrl}" data-show-count="false"></a>
            </div>
          </div>
  
          <div class="project-info">
            <span id="misc_text">
              <span>${projectName}</span>
              <a class="version-link" href="https://github.com/NYPD/moe-sounds/releases" target="_blank">v${projectVersion}</a>
            </span>
          </div>
          
        </div>

      </div>

    </div>

    <footer class="footer">
      <span class="copyright">&copy;<span id="copyright-year"></span> moe-sounds</span>
    </footer>
  
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="${context}/js/vendor/ion.sound.min.js"></script>
    <script src="${context}/js/global.js?v=${projectVersion}"></script>
    
    <!-- I set in the browser session what page id currently loaded, and retrieve it in the home.js -->
    <script type="text/javascript">
      sessionStorage.setItem('currentPageId', '${page.pageId}');
    </script>
    <script src="${context}/js/home.js"></script>
    
    <!-- Facebook Like Script -->
    <div id="fb-root"></div>
    <script>
      (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s); js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.8";
        fjs.parentNode.insertBefore(js, fjs);
      }(document, 'script', 'facebook-jssdk'));
    </script>
    <!-- Twitter Share Script from twitcount.com -->
    <script type='text/javascript'>
      !function (d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/
            .test(d.location) ? 'http' : 'http';
        if (!d.getElementById(id)) {
          js = d.createElement(s);
          js.id = id;
          js.src = p + '://static1.twitcount.com/js/twitcount.js';
          fjs.parentNode.insertBefore(js, fjs);
        }
      }(document, 'script', 'twitcount_plugins');
    </script>
</body>
</html>