package nl.nutrition.controller.meal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import nl.nutrition.model.MealRequest;
import nl.nutrition.service.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class MealControllerTest {
  @Mock private MealService mealService;
  @InjectMocks private MealController mealController;

  @Test
  void testGetMeals() {
    when(mealService.getMeals()).thenReturn(new ArrayList<>());
    assertTrue(mealController.getMeals().getBody().isEmpty());
  }

  @Test
  void testAddMeal() {
    doNothing().when(mealService).addMeal(any());
    final ResponseEntity<String> response = mealController.addMeal(new MealRequest());
    assertEquals("created", response.getBody());
  }
}
