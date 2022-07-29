package nl.nutrition.service.dao;

import java.util.List;
import nl.nutrition.exception.MealDaoException;
import nl.nutrition.model.MealRequest;
import nl.nutrition.model.dao.Meal;
import nl.nutrition.service.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealDaoService {
  @Autowired private MealRepository mealRepository;

  public Meal addMeal(MealRequest mealRequest) throws MealDaoException {
    // TODO add users functionality
    final int userId = 1;

    final Meal newMeal = new Meal(userId, mealRequest.getName());

    return mealRepository.save(newMeal);
  }

  public List<Meal> getMeals() {
    return mealRepository.findAll();
  }

  public void updateMeal(Meal addedMeal) {
    mealRepository.save(addedMeal);
  }
}
