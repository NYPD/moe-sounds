/* Cached Variables *******************************************************************************/
var $staySignedInCheckbox = $('.stay-signed-in');

/* Listeners **************************************************************************************/
$('.login-image').on('click' , function() {
  
  var href = this.getAttribute('data-href');
  var staySignedIn = $staySignedInCheckbox[0].checked;
  
  window.location.href = href + '?rememberMe=' + staySignedIn;
  
});