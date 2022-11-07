function myFunction() {
  //Calculator input dissappears and so does the text area
  var x = document.getElementById("stringInput");
  var y = document.getElementById("calcButtons");
  var z = document.getElementById("strButtons");
  var k = document.getElementById("calcDisplay");
  var l = document.getElementById("userExpression");
  var m = document.getElementById("fixStyle");
  x.style.display = "none";
  z.style.backgroundColor = "lightGray";
  y.style.backgroundColor = "lightblue";
  k.style.display = "block";
  l.style.display = "none";
  m.style.width = "100%";
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

// Initializes a new instance of the StringBuilder class
// and appends the given value if supplied
function StringBuilder(value) {
  this.strings = new Array("");
  this.append(value);
}

// Appends the given value to the end of this instance.
StringBuilder.prototype.append = function (value) {
  if (value) {
    this.strings.push(value);
  }
};

// Clears the string buffer
StringBuilder.prototype.clear = function () {
  this.strings.length = 1;
};

// Converts this instance to a String.
StringBuilder.prototype.toString = function () {
  return this.strings.join("");
};

var sb = new StringBuilder();
function expressionBuilder(x) {
  sb.append(x);
  document.getElementById("userExpression").innerHTML = sb.toString();
  document.getElementById("userDisplay").innerHTML = sb.toString();
}
function clearCalc() {
  sb.clear();
  document.getElementById("userExpression").innerHTML = sb.toString();
  document.getElementById("userDisplay").innerHTML = sb.toString();
}
