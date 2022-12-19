package nl.nutrition.model.oauth;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import nl.nutrition.model.dao.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

@Data
@AllArgsConstructor
public class OidcDbUser extends User implements OidcUser {
  private final Set<GrantedAuthority> authorities;
  private final Map<String, Object> attributes;
  private final String nameAttributeKey;
  private final OidcIdToken idToken;
  private final OidcUserInfo userInfo;

  @Override
  public Map<String, Object> getClaims() {
    return this.attributes;
  }

  @Override
  public OidcUserInfo getUserInfo() {
    return userInfo;
  }

  @Override
  public OidcIdToken getIdToken() {
    return idToken;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getName() {
    final Object nameAttribute = this.getAttribute(this.nameAttributeKey);
    return nameAttribute != null ? nameAttribute.toString() : "No name";
  }
}
