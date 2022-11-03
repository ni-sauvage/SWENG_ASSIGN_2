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
                    <form action="/calculate" method="POST" enctype="multipart/form-data">
                            <div class="inputText">
                            <h3>Input your calculation here</h3>
                            </div></label></br>
                            <div class="twoInputs">
                                <div class="boxArea">
                                    <textarea class="f4 db border-box hover-black w-100 measure ba b--black-20 pa2 br2 mb2" rows="5"
                                            name="description"><#if description??>${description}</#if></textarea></br>
                                </div>
                                <div class="buttonStyle">
                                    <input class="f6 link dim br1 ba bw1 ph3 pv2 mb2 dib black bg-white pointer" type="submit" value="Calculate" name="expr" margin="0">
                                </div>
                            </div>
                    </form>
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
    </body>
</html>