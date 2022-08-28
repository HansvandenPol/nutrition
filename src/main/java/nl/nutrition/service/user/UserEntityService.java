package nl.nutrition.service.user;

import java.util.Optional;
import nl.nutrition.exception.CreateAccountException;
import nl.nutrition.model.RegisterViewModel;
import nl.nutrition.model.dao.User;
import nl.nutrition.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {
  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private UserRepository userRepository;

  public User createUser(RegisterViewModel accountData) {
    if (!accountData.getPassword().equals(accountData.getPasswordConfirm())) {
      throw new CreateAccountException("Passwords didn't match");
    }

    final Optional<User> existingUser = userRepository.findByUsername(accountData.getUsername());
    existingUser.ifPresent(
        u -> {
          throw new CreateAccountException(
              "User already exist with username: " + accountData.getUsername());
        });

    final User newUserObject =
        new User(
            accountData.getUsername(),
            passwordEncoder.encode(accountData.getPassword()),
            false,
            0,
            "ROLE_USER");

    try {
      return userRepository.save(newUserObject);
    } catch (Exception exception) {
      throw new CreateAccountException(
          "Couldn't save the new user. Error message: " + exception.getMessage());
    }
  }
}
