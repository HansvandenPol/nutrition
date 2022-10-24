package nl.nutrition.controller.thymeleaf.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BmiCalculatorController {
  @GetMapping("/calculator/bmi")
  public String getPage() {
    return "bmiCalculator";
  }
}
