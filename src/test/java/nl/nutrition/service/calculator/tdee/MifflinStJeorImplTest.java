package nl.nutrition.service.calculator.tdee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.nutrition.model.calculator.TdeeRequestData;
import org.junit.jupiter.api.Test;

class MifflinStJeorImplTest {
  private MifflinStJeorImpl mifflinStJeor = new MifflinStJeorImpl();

  @Test
  void calculate() {
    // Arrange
    final TdeeRequestData tdeeRequestMock = new TdeeRequestData("male",80,180,20,true,0,2);
    final TdeeRequestData tdeeRequestMock2 = new TdeeRequestData("female",90,170,30,true,0,3);
    // Act
    final int score = mifflinStJeor.calculate(tdeeRequestMock);
    final int score2 = mifflinStJeor.calculate(tdeeRequestMock2);
    // Assert
    assertEquals(1830, score);
    assertEquals(1661, score2);
  }
}
