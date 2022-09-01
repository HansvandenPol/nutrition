package nl.nutrition.configuration;

import nl.nutrition.service.user.AuthProviderImpl;
import nl.nutrition.service.user.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configurations for the Spring security authentication provider
 *
 * <p>This specific configuration uses the {@link AuthProviderImpl} that uses the database to
 * authenticate users
 */
public class AuthenticationProviderConfig {
  @Autowired private AuthProviderImpl authenticationProvider;
  /**
   * Creates an authentication provider Bean
   *
   * @param passwordEncoder the implementation of the password encoder
   * @param userDetailService the implmentation of the user detail service
   * @return newly created provider
   */
  @Bean
  @Autowired
  public AuthenticationProvider getManager(
      PasswordEncoder passwordEncoder, UserDetailServiceImpl userDetailService) {
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    authenticationProvider.setUserDetailsService(userDetailService);
    return authenticationProvider;
  }
}
