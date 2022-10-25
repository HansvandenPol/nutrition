package nl.nutrition.controller.calculator;

import javax.validation.Valid;
import nl.nutrition.model.calculator.TdeeRequestData;
import nl.nutrition.model.calculator.TdeeRestResponse;
import nl.nutrition.service.calculator.TdeeCalculatorService;
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
public class TdeeCalculatorRestController {
  @Autowired private TdeeCalculatorService tdeeCalculatorService;

  @PostMapping("tdee")
  public ResponseEntity<TdeeRestResponse> getBmiValue(
      @RequestBody @Valid TdeeRequestData requestData) {
    final int score = tdeeCalculatorService.calculateTdee(requestData);
    return ResponseEntity.ok(new TdeeRestResponse(score));
  }
}
