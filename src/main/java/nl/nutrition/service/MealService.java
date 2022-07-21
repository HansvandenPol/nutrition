package nl.nutrition.service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import nl.nutrition.exception.MealDaoException;
import nl.nutrition.model.MealProductRequest;
import nl.nutrition.model.MealRequest;
import nl.nutrition.model.dao.Meal;
import nl.nutrition.model.dao.MealProduct;
import nl.nutrition.service.dao.MealDaoService;
import nl.nutrition.service.dao.MealProductDaoService;
import nl.nutrition.service.mapper.MealToMealRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MealService {
  @Autowired private MealDaoService mealDaoService;
  @Autowired private MealProductDaoService mealProductDaoService;
  @Autowired private MealToMealRequest mealToMealRequest;

  public void addMeal(MealRequest mealRequest) throws MealDaoException {
    final Meal addedMeal = mealDaoService.addMeal(mealRequest);

    final List<MealProductRequest> mealProductRequests = mealRequest.getMealProductRequests();

    final List<MealProduct> addedProducts = new ArrayList<>();
    addMealProducts(mealProductRequests, addedProducts, addedMeal);
  }

  private void addMealProducts(
      List<MealProductRequest> mealProductRequests, List<MealProduct> addedMeals, Meal addedMeal) {
    mealProductRequests.forEach(
        i -> {
          try {
            final MealProduct addedMealProduct = mealProductDaoService.addMealProduct(i, addedMeal);
            addedMeals.add(addedMealProduct);
          } catch (Exception entityNotFoundException) {
            log.error(entityNotFoundException.getMessage(), entityNotFoundException);
          }
        });
  }

  public List<MealRequest> getMeals() {
    final List<Meal> mealsFromDB = mealDaoService.getMeals();

    return mealsFromDB.stream().map(mealToMealRequest).toList();
  }
}
