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

for (var i = 0; i < thumbnails.length; i++) {
  var offsetHeight = thumbnails[i].offsetHeight;
  if(offsetHeight > tallestThumbnailSize) tallestThumbnailSize = offsetHeight;
};

for (var i = 0; i < thumbnails.length; i++) {
  thumbnails[i].setAttribute('style','min-height:' + tallestThumbnailSize + 'px');
};

//After thumbnail processing, remove the page-loading blocker
document.querySelector('body').classList.remove('content-loading');

window.setInterval(function() {
  
  $.get('get-total-click-count').done(function(totalClicks) {
    document.querySelector('.total-clicks').innerHTML = totalClicks;
  });
  
}, 5000);