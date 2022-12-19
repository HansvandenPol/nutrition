package nl.nutrition.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "app_users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @NonNull
  @Column(unique = true)
  private String username;

  @NotNull @NonNull private String password;

  @Column(name = "oauth_id", unique = true)
  private String oauthId;

  @Column(name = "is_locked")
  @NotNull
  @NonNull
  private boolean isLocked;

  @NotNull @NonNull private int attempts;

  @NotNull @NonNull private String role;

  protected User() {}
}
