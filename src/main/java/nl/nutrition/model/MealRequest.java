package nl.nutrition.model;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class MealRequest {

  @Size(min = 1, max = 30)
  @NotNull
  private String name;

  private List<MealProductRequest> mealProductRequests;
}
