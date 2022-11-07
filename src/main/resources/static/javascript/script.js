/*START OF THE BUTTON TOGGLE SWITCH BETWEEN THE STRING INPUT AND THE CALCULATOR DISPLAY*/
function myFunction() {
  //Calculator input dissappears and so does the text area
  var stringInput = document.getElementById("stringInput");
  var calcButtons = document.getElementById("calcButtons");
  var strButtons = document.getElementById("strButtons");
  var calcDisplay = document.getElementById("calcDisplay");
  var userExpression = document.getElementById("userExpression");
  var fixStyle = document.getElementById("fixStyle");
  stringInput.style.display = "none";
  strButtons.style.backgroundColor = "lightGray";
  calcButtons.style.backgroundColor = "lightblue";
  calcDisplay.style.display = "block";
  userExpression.style.display = "none"; //THIS IS ONLINE IN TO HIDE THE TEXT AREA THE BUTTON IS GETTING THE EXPRESSION FROM--IT IS BEING DISPLAYED BETTER AS THE TOP OF THE CALCULATOR
  fixStyle.style.width = "100%";
}
function strFunction() {
  //String input dissappears and so does the calculator design
  var stringInput = document.getElementById("stringInput");
  var strButtons = document.getElementById("strButtons");
  var calcButtons = document.getElementById("calcButtons");
  var calcDisplay = document.getElementById("calcDisplay");
  stringInput.style.display = "block";
  calcButtons.style.backgroundColor = "lightGray";
  strButtons.style.backgroundColor = "lightblue";
  calcDisplay.style.display = "none";
}
/*END OF THE BUTTON TOGGLE SWITCH BETWEEN THE STRING INPUT AND THE CALCULATOR DISPLAY -*/

/*START OF THE STRING BUILDER CLASS IN ORDER TO KEEP ADDING LETTER BY LETTER UNITL THE USER HITS THE '=' BUTTOIN*/
function StringBuilder(value) {
  this.strings = new Array("");
  this.append(value);
}

StringBuilder.prototype.append = function (value) {
  if (value) {
    this.strings.push(value);
  }
};

StringBuilder.prototype.clear = function () {
  this.strings.length = 1;
};

StringBuilder.prototype.toString = function () {
  return this.strings.join("");
};

var sb = new StringBuilder();
function expressionBuilder(x) {
  if (sb.toString().length % 15 === 0) {
    sb.append("\n");
    sb.append(x);
  } else {
    sb.append(x);
  }
  document.getElementById("userExpression").innerHTML = sb.toString();
  document.getElementById("userDisplay").innerHTML = sb.toString();
}
function clearCalc() {
  sb.clear();
  document.getElementById("userExpression").innerHTML = sb.toString();
  document.getElementById("userDisplay").innerHTML = sb.toString();
}
/*END OF THE STRING BUILDER CLASS IN ORDER TO KEEP ADDING LETTER BY LETTER UNITL THE USER HITS THE '=' BUTTOIN*/
