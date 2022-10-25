package nl.nutrition.service.calculator.tdee;

import nl.nutrition.model.calculator.TdeeRequestData;

public class HarrisBenedictImpl implements BmrFormula {

  @Override
  public int calculate(TdeeRequestData requestData) {
    if (requestData.getSex().equals("male")) {
      return (int)
          ((13.397 * requestData.getWeight()
                  + 4.799 * requestData.getHeight()
                  - 5.677 * requestData.getAge())
              + 88.362);
    } else {
      return (int)
          ((9.247 * requestData.getWeight()
                  + 3.098 * requestData.getHeight()
                  - 4.330 * requestData.getAge())
              + 447.593);
    }
  }
}
