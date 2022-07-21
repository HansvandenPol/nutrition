package nl.nutrition.service.mapper;

import java.util.function.Function;
import nl.nutrition.model.MealProductRequest;
import nl.nutrition.model.MealRequest;
import nl.nutrition.model.dao.Meal;
import org.springframework.stereotype.Service;

@Service
public class MealToMealRequest implements Function<Meal, MealRequest> {

  @Override
  public MealRequest apply(Meal meal) {
    final MealRequest temp = new MealRequest();
    temp.setName(meal.getMealName());
    temp.setMealProductRequests(
        meal.getMealProducts().stream()
            .map(
                o -> {
                  final MealProductRequest temp2 = new MealProductRequest();
                  temp2.setProductId(o.getId());
                  temp2.setProductQuantity(o.getQuantity());
                  return temp2;
                })
            .toList());
    return temp;
  }
}
