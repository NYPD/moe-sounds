/* Cached Variables *******************************************************************************/
var $staySignedInCheckbox = $('.stay-signed-in');

/* Listeners **************************************************************************************/
$('.login-image').on('click' , function() {
  
  var image = this;
  
  var href = image.getAttribute('data-href');
  var staySignedIn = $staySignedInCheckbox[0].checked;
  
  var prevPath = image.getAttribute('data-prev-path');
  var hasPrevPath = prevPath !== '' && prevPath !== undefined;
  
  
  window.location.href = href + '?rememberMe=' + staySignedIn + (hasPrevPath ? '&prevPath=' + prevPath : '');
  
});