package nl.nutrition.service.calculator;

import nl.nutrition.model.calculator.BmiRequestData;
import org.springframework.stereotype.Service;

@Service
public class BmiCalculatorService {
  public float calculateBmi(BmiRequestData bmiRequestData) {
    final int weightKg = bmiRequestData.getWeight();
    final float heightMeter = bmiRequestData.getHeight() / 100f;
    final float result = weightKg / (heightMeter * heightMeter);
    return (float) Math.round(result * 10) / 10;
  }
}
