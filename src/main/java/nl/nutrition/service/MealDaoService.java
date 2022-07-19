package nl.nutrition.service;

import java.util.List;
import nl.nutrition.model.MealProductRequest;
import nl.nutrition.model.MealRequest;
import nl.nutrition.model.dao.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealDaoService {
  @Autowired private MealRepository mealRepository;

  public Meal addMeal(MealRequest mealRequest) {
    final int userId = 1;
    final List<MealProductRequest> productList = mealRequest.getMealProductRequests();

    //    final Meal newMeal = new Meal(userId, mealRequest.getName());
    //    productList.to
    //    newMeal.setProducts(productList);
    //    mealRepository.save()
    return null;
  }
}
