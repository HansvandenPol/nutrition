package nl.nutrition.util;

import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;
import nl.nutrition.model.ApiError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ExceptionHandler that will handle any exceptions that are being thrown from any of the rest
 * controllers. Will logg the stacktrace, and return a generic ErrorResponse.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LogManager.getLogger();
  private static final String GENERAL_ERROR_MSG = "An error has occured!";

  @ExceptionHandler(value = EntityNotFoundException.class)
  public ResponseEntity<ApiError> handleEntityNotFoundException(
      Exception entityNotFoundException) {
    final int status = HttpStatus.NOT_FOUND.value();

    LOGGER.error(
        "Caught EnityNotFoundException!", entityNotFoundException);
    ApiError errorResponse = new ApiError(status, LocalDateTime.now(),"Entity not found","");

    return ResponseEntity.status(status).body(errorResponse);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ApiError> handleBasicException(
      Exception exception) {
    final int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    LOGGER.error(
        "Caught exception!", exception);
    ApiError errorResponse = new ApiError(status, LocalDateTime.now(),GENERAL_ERROR_MSG,"");

    return ResponseEntity.status(status).body(errorResponse);
  }
}
