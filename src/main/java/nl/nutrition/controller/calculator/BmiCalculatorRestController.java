package nl.nutrition.controller.calculator;

import javax.validation.Valid;
import nl.nutrition.model.calculator.BmiRequestData;
import nl.nutrition.model.calculator.BmiRestResponse;
import nl.nutrition.service.calculator.BmiCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/calculator")
@Validated
public class BmiCalculatorRestController {
  @Autowired private BmiCalculatorService bmiCalculatorService;

  @PostMapping("bmi")
  public ResponseEntity<BmiRestResponse> getBmiValue(
      @RequestBody @Valid BmiRequestData requestData) {
    final float score = bmiCalculatorService.calculateBmi(requestData);
    return ResponseEntity.ok(new BmiRestResponse(score));
  }
}
