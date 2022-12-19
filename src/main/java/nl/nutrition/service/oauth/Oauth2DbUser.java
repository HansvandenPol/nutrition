package nl.nutrition.service.oauth;

import org.springframework.security.oauth2.core.user.OAuth2User;

public interface Oauth2DbUser {
  OAuth2User getUser(String oauthId);
}
