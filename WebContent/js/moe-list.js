var context = sessionStorage.getItem('context');
var rowThumbnails = document.querySelector('.row-thumbnails');

rowThumbnails.addEventListener("click", moeRedirect, false);

function moeRedirect(event) {
  
  var target = event.target;
  
  var notImage = target.className !== 'thumbnail-image';
  if(notImage) return;
  
  var pageId = target.getAttribute('data-page-id');
  
  window.location.href = context + '/page/' + pageId;
  
}