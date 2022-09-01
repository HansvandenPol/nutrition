package nl.nutrition.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterViewModel {
  @NonNull
  @NotNull
  @Size(min = 1, max = 20)
  @Pattern(regexp = "^[A-Za-z ,.'-]+")
  private String firstName;

  @NonNull
  @NotNull
  @Size(min = 1, max = 45)
  @Pattern(regexp = "^[A-Za-z ,.'-]+")
  private String lastName;

  @NonNull
  @NotNull
  @Size(min = 1, max = 40)
  @Email
  private String email;

  @NonNull
  @NotNull
  @Size(min = 1, max = 15)
  @Pattern(regexp = "^[A-Za-z\\d]+")
  private String username;

  @NonNull
  @NotNull
  @Size(min = 8)
  private String password;

  @NonNull @NotNull private String passwordConfirm;
}
