package nl.nutrition.service.calculator.tdee;

import nl.nutrition.model.calculator.TdeeRequestData;

public interface BmrFormula {
  int calculate(TdeeRequestData requestData);
}
