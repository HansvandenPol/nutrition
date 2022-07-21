package nl.nutrition.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class MealProductRequest {

  @NotNull
  @Min(1)
  private Long productId;

  @NotNull
  @Min(0)
  @Max(100000)
  private double productQuantity;
}
