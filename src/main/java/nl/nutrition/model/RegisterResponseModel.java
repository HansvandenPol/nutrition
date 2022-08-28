package nl.nutrition.model;

import java.time.Instant;
import lombok.Data;
import lombok.NonNull;

@Data
public class RegisterResponseModel {
  @NonNull private Long id;
  private Instant timeStamp;

  public static RegisterResponseModel withUserId(Long id) {
    return new RegisterResponseModel(id);
  }

  public RegisterResponseModel build() {
    this.timeStamp = Instant.now();
    return this;
  }
}
