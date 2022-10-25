package nl.nutrition.model.calculator;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TdeeRestResponse {
  @NonNull private int score;
}
