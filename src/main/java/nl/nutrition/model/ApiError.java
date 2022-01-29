package nl.nutrition.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Holder of general REST error information */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
  private int status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timeStamp;

  private String error;

  private String message;
}
