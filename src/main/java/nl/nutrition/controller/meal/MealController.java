package nl.nutrition.controller.meal;

import javax.validation.Valid;
import nl.nutrition.model.MealRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/meal")
public class MealController {

  @GetMapping
  public String getMeals() {
    return "doet t !";
  }

  @PostMapping
  public ResponseEntity<String> addMeal(@Valid @RequestBody MealRequest mealRequest) {

    return ResponseEntity.ok("");
  }
}
