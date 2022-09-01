package nl.nutrition.service.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import nl.nutrition.model.dao.User;
import nl.nutrition.service.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AuthProviderImplTest {
  private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
  private UserRepository userRepository = mock(UserRepository.class);
  private UserDetailsService userDetailsService = mock(UserDetailsService.class);
  private AuthProviderImpl authProvider =
      new AuthProviderImpl(passwordEncoder, userRepository, userDetailsService);

  UserDetails userDetailsMock;
  UsernamePasswordAuthenticationToken tokenMock;

  @BeforeEach
  public void initTests() {
    userDetailsMock = mock(UserDetails.class);
    tokenMock = mock(UsernamePasswordAuthenticationToken.class);
  }

  @Test
  void testAdditionalAuthenticationChecks() {
    // Arrange
    when(userDetailsMock.getPassword()).thenReturn("a_password");
    when(tokenMock.getCredentials()).thenReturn("secure_password");
    when(passwordEncoder.matches(any(), anyString())).thenReturn(true);

    // Act
    authProvider.additionalAuthenticationChecks(userDetailsMock, tokenMock);
    // Assert
    verify(userRepository, never()).save(any());
  }

  @Test
  void testAdditionalAuthenticationCheck_shouldThrowException_whenCredentialsAreEmpty() {
    // Arrange
    when(tokenMock.getCredentials()).thenReturn(null);
    // Act
    // Assert
    assertThrows(
        BadCredentialsException.class,
        () -> authProvider.additionalAuthenticationChecks(userDetailsMock, tokenMock));
  }

  @Test
  void testAdditionalAuthenticationCheck_shouldThrowException_whenPasswordDoesntMatch() {
    // Arrange
    final User userMock = mock(User.class);
    when(userDetailsMock.getPassword()).thenReturn("a_password");
    when(tokenMock.getCredentials()).thenReturn("secure_password");
    when(userDetailsMock.getUsername()).thenReturn("hoi");
    when(passwordEncoder.matches(any(), anyString())).thenReturn(false);
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userMock));
    when(userMock.getAttempts()).thenReturn(5);
    when(userRepository.save(any())).thenReturn(userMock);
    // Act
    // Assert
    assertThrows(
        BadCredentialsException.class,
        () -> authProvider.additionalAuthenticationChecks(userDetailsMock, tokenMock));
    verify(userMock, atLeastOnce()).setLocked(true);
  }
}
