# Software Engineering Assignment 2 - Group 2
A simple GUI or text-input calculator webapp.

## How to Run
There are two active releases for this project.
1. `v1.0.0` - Lacks the calculator GUI, primitive frontend, lack of functions such as exp and log.
2. `v2.0.1` - Final release, includes functions, calculator GUI, advanced frontend.

### Pulling Images from Dockerhub
Run either of the following two commands, dependent on what version you wish to run.
1. `docker image pull sauvagen/calculator_webapp:v1.0.0`
2. `docker image pull sauvagen/calculator_webapp:v2.0.1`

Next, depending on which image you pulled, run either
1. `docker run --name=calc-container --rm -d -p 8080:8080 sauvagen/calculator_webapp:v2.0.1`
2. `docker run --name=calc-container --rm -d -p 8080:8080 sauvagen/calculator_webapp:v1.0.0`

### Running Locally
In order to run locally and not out of a docker container, we will use the mvnw file.
#### Windows
`.\mvnw clean install spring-boot:run`
#### MacOS/Linux
`./mvnw clean install spring-boot:run`

## Accessing the Calculator
In order to access the calculator, enter `localhost:8080` into the address bar of your browser. This will produce the website. From here, choose either calculator input or string input and you may enter expressions. 

## Group Members
1. Aritro De - 20331942
2. James Fenlon - 19337246	
3. Leah O’Connor - 20333782
4. Niall Sauvage - 20334203
5. Saif Ali - 20332469
