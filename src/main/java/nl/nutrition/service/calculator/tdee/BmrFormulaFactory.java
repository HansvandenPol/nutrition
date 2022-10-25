package nl.nutrition.service.calculator.tdee;

import org.springframework.stereotype.Service;

@Service
public class BmrFormulaFactory {
  public BmrFormula getFormula(int formulaIndex) {
    switch (formulaIndex) {
      case 0 -> {
        return new MifflinStJeorImpl();
      }
      case 1 -> {
        return new HarrisBenedictImpl();
      }
      default -> {
        return new MifflinStJeorImpl();
      }
    }
  }
}
