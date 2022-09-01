package nl.nutrition.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PasswordEncoderConfigurationTest {
  private PasswordEncoderConfiguration passwordEncoderConfiguration =
      new PasswordEncoderConfiguration();

  @Test
  void testGetManager() {
    // Arrange
    // Act
    final PasswordEncoder passwordEncoder = passwordEncoderConfiguration.createEncoder();
    // Assert
    assertNotNull(passwordEncoder);
  }
}
