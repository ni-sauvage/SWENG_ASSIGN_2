<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
    <head> 
        <title>SWENG Calculator</title>
        <link href="css/result.css" rel="stylesheet">
    </head>
    <body>
        <div class="headerStyle">
            <h1>Your result is: </h1>
        </div>
        <div class="resultDisplay">
            <h1>${Calculation}</h1>
        </div>
        <div class="anotherInput">
            <h3>Click <a href="/">here</a> to submit another calculation.</h3>
        </div>
    </body>
</html>
