package nl.nutrition.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import nl.nutrition.model.MealProductRequest;
import nl.nutrition.model.MealRequest;
import nl.nutrition.model.dao.Meal;
import nl.nutrition.model.dao.MealProduct;
import nl.nutrition.model.dao.Product;
import nl.nutrition.service.dao.MealDaoService;
import nl.nutrition.service.dao.MealProductDaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MealServiceTest {

  @Mock private MealDaoService mealDaoService;
  @Mock private MealProductDaoService mealProductDaoService;
  @InjectMocks private MealService mealService;

  @Test
  void testAddMeal_shouldByDefault() {
    final Meal meal = new Meal();
    final MealRequest mealRequest = new MealRequest();
    mealRequest.setMealProductRequests(new ArrayList<>());
    when(mealDaoService.addMeal(any())).thenReturn(meal);

    mealService.addMeal(mealRequest);
    verify(mealDaoService, atLeastOnce()).addMeal(any());
    verify(mealDaoService, atLeastOnce()).updateMeal(any());
  }

  @Test
  void testAddMeal_shouldAddMealAndProduct() {
    final Meal meal = new Meal();
    final MealRequest mealRequest = new MealRequest();
    final MealProductRequest mealProductRequest = new MealProductRequest(1L, 0.5);
    final MealProduct mealProduct = new MealProduct();
    final Product mockProduct = Mockito.mock(Product.class);
    mealProduct.setMeal(meal);
    mealProduct.setId(1L);
    mealProduct.setQuantity(0.5);
    mealProduct.setProduct(mockProduct);

    mealRequest.setMealProductRequests(Collections.singletonList(mealProductRequest));

    when(mealDaoService.addMeal(any())).thenReturn(meal);
    when(mealProductDaoService.addMealProduct(any(), any())).thenReturn(mealProduct);

    when(mockProduct.getKcal()).thenReturn(1.5);
    when(mockProduct.getProteinTotal()).thenReturn(1.6);
    when(mockProduct.getCarbsTotal()).thenReturn(1.7);
    when(mockProduct.getFatTotal()).thenReturn(1.8);

    mealService.addMeal(mealRequest);
    verify(mealDaoService, atLeastOnce()).addMeal(any());
    verify(mealDaoService, atLeastOnce()).updateMeal(any());
  }

  @Test
  void testGetMeals() {
    when(mealDaoService.getMeals()).thenReturn(new ArrayList<>());
    assertTrue(mealService.getMeals().isEmpty());
  }
}
