package nl.nutrition.service.user;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import nl.nutrition.model.dao.User;
import nl.nutrition.service.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UserDetailServiceImplTest {
  @Mock private UserRepository userRepositoryMock;
  @InjectMocks private UserDetailServiceImpl userDetailService;

  @Test
  void testLoadUserByUsername() {
    // Arrange
    final User userMock = mock(User.class);
    when(userRepositoryMock.findByUsername(anyString())).thenReturn(Optional.of(userMock));

    // Act
    final UserDetails userDetails = userDetailService.loadUserByUsername("test");

    // Assert
    assertNotNull(userDetails);
    verify(userRepositoryMock, atLeastOnce()).findByUsername(anyString());
  }

  @Test
  void testLoadUserByUsername_throwException_whenUserNotFound() {
    // Arrange
    final User userMock = mock(User.class);
    when(userRepositoryMock.findByUsername(anyString())).thenReturn(Optional.empty());

    // Act
    // Assert
    assertThrows(
        UsernameNotFoundException.class, () -> userDetailService.loadUserByUsername("test"));
    verify(userRepositoryMock, atLeastOnce()).findByUsername(anyString());
  }
}
