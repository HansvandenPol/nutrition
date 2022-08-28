package nl.nutrition.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import nl.nutrition.model.dao.Meal;
import nl.nutrition.model.dao.MealProduct;
import nl.nutrition.model.dao.Product;
import nl.nutrition.model.dao.User;
import nl.nutrition.service.MealProductRepository;
import nl.nutrition.service.MealRepository;
import nl.nutrition.service.ProductRepository;
import nl.nutrition.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/** Service to initialize the database */
@Service
@Profile("dev")
public class DatabaseInitialiser implements CommandLineRunner {

  public static final String BASE_ITEM_NAME = "item";
  @Autowired private ProductRepository productRepository;
  @Autowired private MealRepository mealRepository;
  @Autowired private MealProductRepository mealProductRepository;
  @Autowired private UserRepository userRepository;
  @Autowired private PasswordEncoder passwordEncoder;

  private List<Product> productList = new ArrayList<>();
  private List<MealProduct> mealProducts = new ArrayList<>();
  private List<Meal> meals = new ArrayList<>();

  @Override
  public void run(String... args) throws Exception {
    IntStream.range(0, 10).forEach(this::addProduct);
    IntStream.range(0, 5).forEach(this::addMeal);
    IntStream.range(0, 5).forEach(this::addMealProduct);

    userRepository.save(createUser());
    productRepository.saveAll(productList);
    mealRepository.saveAll(meals);
    mealProductRepository.saveAll(mealProducts);
  }

  private void addMeal(int i) {
    final Meal meal = new Meal(1, "Lasagne");
    meals.add(meal);
  }

  private void addMealProduct(int i) {
    final MealProduct mealProduct = new MealProduct(60.5);
    mealProduct.setProduct(productList.get(i));
    mealProduct.setMeal(meals.get(i));
    mealProducts.add(mealProduct);
  }

  private void addProduct(int i) {
    final Product product =
        new Product(
            1,
            BASE_ITEM_NAME + "-" + i,
            BASE_ITEM_NAME + "-" + i,
            BASE_ITEM_NAME + "-" + i,
            BASE_ITEM_NAME + "-" + i,
            "g",
            100.0,
            1000.0,
            200.0,
            1.0,
            1.0,
            300.0,
            1.0,
            1.0,
            1.0,
            50.0,
            1.0,
            1.0,
            1.0,
            1.0,
            1.0,
            1.0,
            1.0);
    productList.add(product);
  }

  private User createUser() {
    return new User("hans", passwordEncoder.encode("hans"), false, 0, "ROLE_USER");
  }
}
