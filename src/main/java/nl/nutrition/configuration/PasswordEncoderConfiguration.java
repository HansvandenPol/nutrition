package nl.nutrition.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configures which password encoder to use
 *
 * <p>This configuration creates an {@link BCryptPasswordEncoder}
 */
@Configuration
public class PasswordEncoderConfiguration {

  @Bean
  public PasswordEncoder createEncoder() {
    return new BCryptPasswordEncoder();
  }
}
