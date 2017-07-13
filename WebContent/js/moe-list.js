/* Cached Variables *******************************************************************************/
var context = sessionStorage.getItem('context');
var rowThumbnails = document.querySelector('.row-thumbnails');

/* Initialization *********************************************************************************/
rowThumbnails.addEventListener("click", moeRedirect, false);

function moeRedirect(event) {
  
  var target = event.target;
  
  var notImage = target.className !== 'thumbnail-image';
  if(notImage) return;
  
  var pageId = target.getAttribute('data-page-id');
  
  window.location.href = context + '/page/' + pageId;
  
}

var thumbnails = window.document.querySelectorAll('.thumbnail');
var tallestThumbnailSize = 0;

thumbnails.forEach(function(value, key, listObj, argument) {
	var offsetHeight = listObj[key].offsetHeight;
	if(offsetHeight > tallestThumbnailSize) tallestThumbnailSize = offsetHeight;
});

thumbnails.forEach(function(value, key, listObj, argument) {
	listObj[key].setAttribute('style','min-height:' + tallestThumbnailSize + 'px');
});


//After thumbnail processing, remove the page-loading blocker
document.querySelector('body').classList.remove('content-loading');

