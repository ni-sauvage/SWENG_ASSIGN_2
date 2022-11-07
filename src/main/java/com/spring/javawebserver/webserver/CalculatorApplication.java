package com.spring.javawebserver.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
public class CalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }
}

@Controller
class CalculationController {

    @PostMapping("/calculate")
    public String calculate(@RequestParam String description, @RequestParam String expr, Model model) {
        String[] inputLiterals = Parser.parse(description);
        if (Arith.validateInfixOrder(inputLiterals)) {
            String result = String.valueOf(Arith.evaluateInfixOrder(inputLiterals));
            model.addAttribute("Calculation", result);
            return "/result";
        } else {
            return "/app";
        }

    }
}
