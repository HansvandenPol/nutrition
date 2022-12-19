package nl.nutrition.service.oauth;

import nl.nutrition.model.oauth.OidcDbUser;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class DatabaseOidcOauthServiceImpl extends OidcUserService {
  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    final OidcUser user = super.loadUser(userRequest);

    final String uniqueId = user.getAttribute("sub");


    return new OidcDbUser();
  }



}
