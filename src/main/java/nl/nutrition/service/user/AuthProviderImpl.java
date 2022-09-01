package nl.nutrition.service.user;

import nl.nutrition.model.dao.User;
import nl.nutrition.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthProviderImpl extends DaoAuthenticationProvider {
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Autowired
  public AuthProviderImpl(
      PasswordEncoder passwordEncoder,
      UserRepository userRepository,
      UserDetailsService userDetailsService) {
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
    this.setUserDetailsService(userDetailsService);
  }

  @Override
  public void additionalAuthenticationChecks(
      UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {

    if (authentication.getCredentials() == null) {
      this.logger.debug("Failed to authenticate since no credentials provided");
      throw new BadCredentialsException(
          this.messages.getMessage(
              "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
    }
    String presentedPassword = authentication.getCredentials().toString();
    if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
      this.logger.debug("Failed to authenticate since password does not match stored value");
      final User userFromDb =
          userRepository
              .findByUsername(userDetails.getUsername())
              .orElseThrow(() -> new BadCredentialsException("couldn't find user"));
      final int currentAttempts = userFromDb.getAttempts();
      if (currentAttempts >= 5) {
        userFromDb.setLocked(true);
      }
      userFromDb.setAttempts(userFromDb.getAttempts() + 1);
      userRepository.save(userFromDb);
      throw new BadCredentialsException(
          this.messages.getMessage(
              "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
    }
  }
}
