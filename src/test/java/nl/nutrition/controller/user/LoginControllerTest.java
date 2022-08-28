package nl.nutrition.controller.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class LoginControllerTest {
  private LoginController loginController = new LoginController();

  @Test
  void testLoginPage() {
    // Arrange
    // Act
    final String loginPage = loginController.loginPage();

    // Assert
    assertEquals("login", loginPage);
  }
}
