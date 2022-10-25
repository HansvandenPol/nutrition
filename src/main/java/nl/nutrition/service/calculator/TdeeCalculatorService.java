package nl.nutrition.service.calculator;

import nl.nutrition.model.calculator.TdeeRequestData;
import nl.nutrition.service.calculator.tdee.BmrFormulaFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TdeeCalculatorService {
  @Autowired private BmrFormulaFactory bmrFormulaFactory;

  public int calculateTdee(TdeeRequestData tdeeRequestData) {
    final int bmr =
        bmrFormulaFactory.getFormula(tdeeRequestData.getFormula()).calculate(tdeeRequestData);

    double multiplier;
    switch (tdeeRequestData.getActivityLevel()) {
      case 0:
        {
          multiplier = 1.2;
          break;
        }
      case 1:
        {
          multiplier = 1.375;
          break;
        }
      case 2:
        {
          multiplier = 1.55;
          break;
        }
      case 3:
        {
          multiplier = 1.725;
          break;
        }
      case 4:
        {
          multiplier = 1.9;
          break;
        }
      default:
        multiplier = 1.2;
    }
    return (int) (bmr * multiplier);
  }
}
