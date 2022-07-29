package nl.nutrition.service;

import nl.nutrition.model.dao.MealProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealProductRepository extends CrudRepository<MealProduct, Long> {}
