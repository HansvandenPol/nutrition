package nl.nutrition.model.calculator;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BmiRestResponse {
  @NonNull private float score;
}
