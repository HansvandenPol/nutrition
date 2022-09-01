package nl.nutrition.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import nl.nutrition.model.dao.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserDetailsImplTest {
  final User userMock = mock(User.class);
  private UserDetailsImpl userDetails = new UserDetailsImpl(userMock);

  @Test
  void testGetAuthorities() {
    // Arrange
    when(userMock.getRole()).thenReturn("ROLE_TEST");

    // Act
    final Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
    final GrantedAuthority ok = (GrantedAuthority) authorities.toArray()[0];

    // Assert
    assertEquals("ROLE_TEST", ok.getAuthority());
    assertEquals(1, authorities.size());
  }

  @Test
  void testGetPassword() {
    // Arrange
    when(userMock.getPassword()).thenReturn("12345");

    // Act
    final String password = userDetails.getPassword();

    // Assert
    assertEquals("12345", password);
  }

  @Test
  void testGetUsername() {
    // Arrange
    when(userMock.getUsername()).thenReturn("harrold");

    // Act
    final String username = userDetails.getUsername();

    // Assert
    assertEquals("harrold", username);
  }

  @Test
  void testIsAccountNonExpired() {
    // Arrange
    // Act
    final boolean isNonExpired = userDetails.isAccountNonExpired();

    // Assert
    assertTrue(isNonExpired);
  }

  @Test
  void testIsAccountNonLocked() {
    // Arrange
    when(userMock.isLocked()).thenReturn(false);
    // Act
    final boolean isNonLocked = userDetails.isAccountNonLocked();

    // Assert
    assertTrue(isNonLocked);
  }

  @Test
  void testIsCredentialsNonExpired() {
    // Arrange
    // Act
    final boolean isNonExpired = userDetails.isCredentialsNonExpired();

    // Assert
    assertTrue(isNonExpired);
  }

  @Test
  void testIsEnabled() {
    // Arrange
    // Act
    final boolean isEnabled = userDetails.isEnabled();

    // Assert
    assertTrue(isEnabled);
  }
}
