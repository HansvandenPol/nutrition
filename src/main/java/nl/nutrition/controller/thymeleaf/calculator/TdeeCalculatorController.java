package nl.nutrition.controller.thymeleaf.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TdeeCalculatorController {
  @GetMapping("/calculator/tdee")
  public String getPage() {
    return "tdeeCalculator";
  }
}
