package nl.nutrition.service.calculator.tdee;

import nl.nutrition.model.calculator.TdeeRequestData;

public class MifflinStJeorImpl implements BmrFormula {

  @Override
  public int calculate(TdeeRequestData requestData) {
    final int ageFactor = requestData.getSex().equals("male") ? 5 : -151;
    final double sum =
        (10 * requestData.getWeight() + 6.25 * requestData.getHeight() - 5 * requestData.getAge())
            + ageFactor;
    System.out.println(sum);
    return (int) sum;
  }
}
