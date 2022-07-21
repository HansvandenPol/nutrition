package nl.nutrition.service;

import java.util.List;
import nl.nutrition.model.dao.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {
  List<Meal> findAll();
}
