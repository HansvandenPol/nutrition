package nl.nutrition.controller.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import nl.nutrition.model.calculator.TdeeRequestData;
import nl.nutrition.model.calculator.TdeeRestResponse;
import nl.nutrition.service.calculator.TdeeCalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TdeeCalculatorRestControllerTest {
  @Mock private TdeeCalculatorService tdeeCalculatorService;
  @InjectMocks private TdeeCalculatorRestController tdeeCalculatorRestController;

  @Test
  void getBmiValue() {
    // Arrange
    final TdeeRequestData tdeeRequestMock = new TdeeRequestData("male",80,180,20,true,1,2);
    when(tdeeCalculatorService.calculateTdee(any(TdeeRequestData.class))).thenReturn(1500);
    // Act
    final ResponseEntity<TdeeRestResponse> responseObject = tdeeCalculatorRestController.getTdeeValue(tdeeRequestMock);

    // Assert
    assertEquals(200, responseObject.getStatusCodeValue());
    assertEquals(1500, responseObject.getBody().getScore());
  }
}
