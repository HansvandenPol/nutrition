package nl.nutrition.controller.meal;

import java.util.List;
import javax.validation.Valid;
import nl.nutrition.exception.MealDaoException;
import nl.nutrition.model.MealRequest;
import nl.nutrition.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/meal")
public class MealController {
  @Autowired private MealService mealService;

  @GetMapping
  public ResponseEntity<List<MealRequest>> getMeals() {
    return ResponseEntity.ok(mealService.getMeals());
  }

  @PostMapping
  public ResponseEntity<String> addMeal(@Valid @RequestBody MealRequest mealRequest) {
    try {
      mealService.addMeal(mealRequest);
    } catch (MealDaoException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
    return ResponseEntity.ok("created");
  }
}
