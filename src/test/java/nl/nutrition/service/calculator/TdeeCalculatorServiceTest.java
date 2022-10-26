package nl.nutrition.service.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import nl.nutrition.model.calculator.TdeeRequestData;
import nl.nutrition.service.calculator.tdee.BmrFormulaFactory;
import nl.nutrition.service.calculator.tdee.HarrisBenedictImpl;
import nl.nutrition.service.calculator.tdee.MifflinStJeorImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class TdeeCalculatorServiceTest {
  @Mock private BmrFormulaFactory bmrFactoryMock;
  @InjectMocks private TdeeCalculatorService tdeeCalculator;

  @Test
  void calculateTdee() {
    // Arrange
    when(bmrFactoryMock.getFormula(anyInt())).thenReturn(new HarrisBenedictImpl(), new MifflinStJeorImpl());

    final TdeeRequestData tdeeRequestMock = new TdeeRequestData("male",80,180,20,true,1,2);
    final TdeeRequestData tdeeRequestMock2 = new TdeeRequestData("female",90,170,30,true,0,3);

    // Act
    final int tdeeScore = tdeeCalculator.calculateTdee(tdeeRequestMock);
    final int tdeeScore2 = tdeeCalculator.calculateTdee(tdeeRequestMock2);

    // Assert
    assertEquals(2960, tdeeScore);
    assertEquals(2865, tdeeScore2);
  }
}
