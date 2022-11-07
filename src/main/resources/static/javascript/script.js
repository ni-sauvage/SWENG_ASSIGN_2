function myFunction() {
  //Calculator input dissappears and so does the text area
  var x = document.getElementById("stringInput");
  var y = document.getElementById("calcButtons");
  var z = document.getElementById("strButtons");
  var k = document.getElementById("calcDisplay");
  x.style.display = "none";
  z.style.backgroundColor = "lightGray";
  y.style.backgroundColor = "lightblue";
  k.style.display = "block";
}
function strFunction() {
  //String input dissappears and so does the calculator design
  var x = document.getElementById("stringInput");
  var y = document.getElementById("strButtons");
  var z = document.getElementById("calcButtons");
  var k = document.getElementById("calcDisplay");
  x.style.display = "block";
  z.style.backgroundColor = "lightGray";
  y.style.backgroundColor = "lightblue";
  k.style.display = "none";
}
