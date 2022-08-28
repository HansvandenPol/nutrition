package nl.nutrition.controller.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import nl.nutrition.model.RegisterResponseModel;
import nl.nutrition.model.RegisterViewModel;
import nl.nutrition.model.dao.User;
import nl.nutrition.service.user.UserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class RegisterControllerTest {
  @Mock private UserEntityService userEntityService;
  @InjectMocks private RegisterController registerController;

  @Test
  void testRegisterPage() {
    // Arrange
    // Act
    final String registerPage = registerController.registerPage();
    // Assert
    assertEquals("register", registerPage);
  }

  @Test
  void testCreate() {
    // Arrange
    final User user = mock(User.class);
    when(userEntityService.createUser(any(RegisterViewModel.class))).thenReturn(user);
    when(user.getId()).thenReturn(1L);

    // Act
    final ResponseEntity<RegisterResponseModel> responseModel =
        registerController.create(new RegisterViewModel());

    // Assert
    assertEquals(200, responseModel.getStatusCode().value());
    assertEquals(1L, responseModel.getBody().getId());
  }
}
