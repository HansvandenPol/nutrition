package nl.nutrition.controller.meal;

import java.util.List;
import javax.validation.Valid;
import nl.nutrition.exception.MealDaoException;
import nl.nutrition.model.MealRequest;
import nl.nutrition.model.dao.Meal;
import nl.nutrition.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Handles the meal related REST requests */
@RestController
@RequestMapping("api/private/meal")
public class MealController {
  @Autowired private MealService mealService;

  /**
   * Retrieves all meals
   *
   * @return meals JSON response
   */
  @GetMapping
  public ResponseEntity<List<Meal>> getMeals() {
    return ResponseEntity.ok(mealService.getMeals());
  }

  /**
   * Adds a meal in the database
   *
   * @param mealRequest the meal to add
   * @return verification upon success.
   */
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
