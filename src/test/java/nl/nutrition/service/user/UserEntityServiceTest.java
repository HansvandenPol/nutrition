package nl.nutrition.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import nl.nutrition.exception.CreateAccountException;
import nl.nutrition.model.RegisterViewModel;
import nl.nutrition.model.dao.User;
import nl.nutrition.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserEntityServiceTest {
  @Mock private PasswordEncoder passwordEncoder;
  @Mock private UserRepository userRepository;
  @InjectMocks private UserEntityService userEntityService;

  @Test
  void testCreateUser() {
    // Arrange
    final RegisterViewModel testModel = createTestModel();
    final User userMock = new User(testModel.getUsername(), "12345", false, 0, "ROLE_USER");
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
    when(passwordEncoder.encode(any())).thenReturn("12345_encoded");
    when(userRepository.save(any())).thenReturn(userMock);

    // Act
    final User newUser = userEntityService.createUser(testModel);

    // Assert
    assertEquals("gerrit123", newUser.getUsername());
    verify(userRepository, atLeastOnce()).findByUsername(anyString());
    verify(passwordEncoder, atLeastOnce()).encode(any());
    verify(userRepository, atLeastOnce()).save(any());
  }

  @Test
  void testCreateUser_throwException_whenPasswordsDontMatch() {
    // Arrange
    final RegisterViewModel testModel = createTestModel();
    testModel.setPasswordConfirm("wrong");

    // Act
    // Assert
    assertThrows(CreateAccountException.class, () -> userEntityService.createUser(testModel));
  }

  @Test
  void testCreateUser_shouldThrowException_whenUserExists() {
    // Arrange
    final RegisterViewModel testModel = createTestModel();
    final User userMock = new User(testModel.getUsername(), "12345", false, 0, "ROLE_USER");
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userMock));

    // Act
    // Assert
    assertThrows(CreateAccountException.class, () -> userEntityService.createUser(testModel));
  }

  @Test
  void testCreateUser_shouldThrowException_whenSaveNotWorks() {
    // Arrange
    final RegisterViewModel testModel = createTestModel();
    final User userMock = new User(testModel.getUsername(), "12345", false, 0, "ROLE_USER");
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
    when(passwordEncoder.encode(any())).thenReturn("12345_encoded");
    when(userRepository.save(any())).thenThrow(new CreateAccountException(""));

    // Act
    // Assert
    assertThrows(CreateAccountException.class, () -> userEntityService.createUser(testModel));
    verify(userRepository, atLeastOnce()).findByUsername(anyString());
    verify(passwordEncoder, atLeastOnce()).encode(any());
    verify(userRepository, atLeastOnce()).save(any());
  }

  private RegisterViewModel createTestModel() {
    return new RegisterViewModel("Gekke", "Gerrit", "gekke@gerrit", "gerrit123", "12345", "12345");
  }
}
