<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
    <head>
        <title>SWENG Calculator</title>
        <link href="css/index.css" rel="stylesheet">
    </head>
    <body class="ph3 pt0 pb4 mw7 center sans-serif">
        <div class="mainContainer">
            <div class="headerStyle">
                <h1 class="f2 mb0">
                    <span class="gold">i</span>Calculator
                </h1>
            </div>
            <div class="inputSection">
                <div class="explanationText">
                    <h2 class="f5 mt1 mb4 lh-copy">A simple spring boot calculator app.</h2>
                </div>
                <div class="calc">
                        <div class="inputText">
                            <h3>Input your calculation here</h3>
                        </div></br>
                        <div class="buttonSelector">
                            <div class="stringButton">
                                <button class="strButton" onClick="strFunction()" id="strButtons">String Input</button>
                            </div>
                            <div class="calcButton">
                                <button class="calcButton" onClick="myFunction()" id="calcButtons">Calculator Input</button>
                            </div>
                        </div>
                        <form action="/calculate" method="POST" enctype="multipart/form-data">
                            <div class="twoInputs" id="stringInput">
                                <div class="boxArea">
                                    <textarea class="f4 db border-box hover-black w-100 measure ba b--black-20 pa2 br2 mb2" rows="5"
                                            name="description"><#if description??>${description}</#if></textarea></br>
                                </div>
                                <div class="buttonStyle">
                                    <input class="f6 link dim br1 ba bw1 ph3 pv2 mb2 dib black bg-white pointer" type="submit" value="Calculate" name="expr" margin="0">
                                </div>
                            </div>
                        </form>
                        <div class="calculator" id="calcDisplay">
                        
                            
                                <div class = "calculatorDesign"  id="userDisplay"></div>
                                <div class = "function">
                                    <button class = "functions functions--operand" onClick="expressionBuilder('+')">+</button>
                                    <button class = "functions functions--operand" onClick="expressionBuilder('-')">-</button>
                                    <button class = "functions functions--operand" onClick="expressionBuilder('*')">&times;</button>
                                    <button class = "functions functions--operand" onClick="expressionBuilder('/')">÷</button>
                                    <button class = "functions" onClick="expressionBuilder('0')">0</button>
                                    <button class = "functions" onClick="expressionBuilder('1')">1</button>
                                    <button class = "functions" onClick="expressionBuilder('2')">2</button>
                                    <button class = "functions" onClick="expressionBuilder('log')">log</button>
                                    <button class = "functions" onClick="expressionBuilder('3')">3</button>
                                    <button class = "functions" onClick="expressionBuilder('4')">4</button>
                                    <button class = "functions" onClick="expressionBuilder('5')">5</button>
                                    <button class = "functions" onClick="expressionBuilder('exp')">exp</button>
                                    <button class = "functions" onClick="expressionBuilder('6')">6</button>
                                    <button class = "functions" onClick="expressionBuilder('7')">7</button>
                                    <button class = "functions" onClick="expressionBuilder('8')">8</button>
                                    <button class = "functions" onClick="clearCalc()">AC</button>
                                    <button class = "functions" onClick="expressionBuilder('9')">9</button>
                                    <button class = "functions" onClick="expressionBuilder('.')">.</button>
                                    <form action="/calculate" method="POST" enctype="multipart/form-data">
                                        <textarea class="f4 db border-box hover-black w-100 measure ba b--black-20 pa2 br2 mb2" rows="5"
                                            name="description" id="userExpression"><#if description??>${description}</#if></textarea>
                                        <button class = "functions functiond--enter" type="submit" name="expr" id="fixStyle">=</button>
                                    </form>
                                    <button class = "functions" onClick="expressionBuilder('^')">^</button>
                                    <button class = "functions"></button>
                                    <button class = "functions" onClick="expressionBuilder('(')">(</button>
                                    <button class = "functions" onClick="expressionBuilder(')')">)</button>
                                    <button class = "functions"></button>
                                </div>
                                
                        </div>
                </div>
            </div>
            <div class="membersArea">
                <div class="members">
                    <div class="one"><h4>Aritro De</h4></div>
                    <div class="two"><h4>James Fenlon</h4></div>
                    <div class="three"><h4>Leah O'Connor</h4></div>
                    <div class="four"><h4>Niall Sauvage</h4></div>
                    <div class="five"><h4>Saif Ali</h4></div>
                </div>
            </div>

        </div>
        <script type="text/javascript" src="javascript\script.js" ></script>
    </body>
</html>