package nl.nutrition.service.dao;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import nl.nutrition.model.MealProductRequest;
import nl.nutrition.model.dao.Meal;
import nl.nutrition.model.dao.MealProduct;
import nl.nutrition.model.dao.Product;
import nl.nutrition.service.MealProductRepository;
import nl.nutrition.service.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealProductDaoService {
  @Autowired private MealProductRepository mealProductRepository;

  @Autowired private ProductRepository productRepository;

  public MealProduct addMealProduct(MealProductRequest mealProductRequest, Meal addedMeal) {
    final MealProduct newMealProduct = new MealProduct(mealProductRequest.getProductQuantity());

    final Optional<Product> productOptional =
        productRepository.findById(mealProductRequest.getProductId());

    final Product product =
        productOptional.orElseThrow(() -> new EntityNotFoundException("Couldn't find the product"));

    newMealProduct.setProduct(product);
    newMealProduct.setMeal(addedMeal);

    return mealProductRepository.save(newMealProduct);
  }
}
