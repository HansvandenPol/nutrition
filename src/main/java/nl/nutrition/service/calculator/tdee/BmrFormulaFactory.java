package nl.nutrition.service.calculator.tdee;

import org.springframework.stereotype.Service;

/**
 * Factory service to produce a implementation of a BMR calculation formula based on the given input.
 */
@Service
public class BmrFormulaFactory {

  /**
   * Retrieves the BMR calculation implementation for a given index
   * @param formulaIndex the index which is matched with a formula.
   * @return BRM formula implementation
   */
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
