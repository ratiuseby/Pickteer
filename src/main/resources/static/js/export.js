function copyToClipboard(element) {
  var $temp = $("<input>")
  $("body").append($temp)
  $temp.val(document.getElementById(element).textContent).select()
  document.execCommand("copy")
  $temp.remove()
}