package nl.nutrition.model.calculator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TdeeRequestData {
  @NotNull
  @Size(max = 20)
  private String sex;

  @NotNull
  @Min(20)
  @Max(500)
  private int weight;

  @NotNull
  @Min(50)
  @Max(300)
  private int height;

  @NotNull
  @Min(2)
  @Max(120)
  private int age;

  @NotNull private boolean isMetric;

  @NotNull
  @Min(0)
  private int formula;

  @NotNull
  @Min(0)
  private int activityLevel;
}