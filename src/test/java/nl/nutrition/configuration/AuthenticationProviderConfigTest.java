package nl.nutrition.configuration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.nutrition.service.user.UserDetailServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AuthenticationProviderConfigTest {
  @Mock private PasswordEncoder passwordEncoder;
  @Mock private UserDetailServiceImpl userDetailService;
  @InjectMocks private AuthenticationProviderConfig authenticationProviderConfig;

  @Test
  void testGetManager() {
    // Arrange
    // Act
    final AuthenticationProvider authenticationProvider =
        authenticationProviderConfig.getManager(passwordEncoder, userDetailService);
    // Assert
    assertNotNull(authenticationProvider);
  }
}
