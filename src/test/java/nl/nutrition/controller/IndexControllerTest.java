package nl.nutrition.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class IndexControllerTest {
  private IndexController indexController = new IndexController();

  @Test
  void testHome() {
    // Arrange
    // Act
    final String response = indexController.home();
    // Assert
    assertEquals("index", response);
  }
}
