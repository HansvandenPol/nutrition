package nl.nutrition.model;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class MealProductRequest {

  @NotNull private int productId;

  @NotNull private double productQuantity;
}
