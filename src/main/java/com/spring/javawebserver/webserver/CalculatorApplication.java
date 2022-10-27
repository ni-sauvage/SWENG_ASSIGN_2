package com.spring.javawebserver.webserver;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SpringBootApplication
public class CalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorApplication.class, args);
	}
}

@Controller
class CalculationController {

    @PostMapping("/calculate")
    public String calculate(@RequestParam String description, @RequestParam String expr) {
        String[] inputLiterals = Parser.parse(description);
        if(Arith.validateInfixOrder(inputLiterals)){
            return String.valueOf(Arith.evaluateInfixOrder(inputLiterals));
        } else{
            return "Error";
        }
    }
}
