package nl.nutrition.service.oauth;

import java.util.Optional;
import nl.nutrition.model.dao.User;
import nl.nutrition.model.oauth.OidcDbUser;
import nl.nutrition.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class OidcUserProvider implements Oauth2DbUser{
  @Autowired private UserRepository userRepository;

  @Override
  public OidcUser getUser(String oauthId) {
    final Optional<User> optionalUser = userRepository.findByOauthId(oauthId);
    optionalUser.ifPresentOrElse(() -> {
      return new OidcDbUser();
    }, () -> {

    });
  }
}
