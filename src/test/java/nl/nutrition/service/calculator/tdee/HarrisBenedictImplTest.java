package nl.nutrition.service.calculator.tdee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.nutrition.model.calculator.TdeeRequestData;
import org.junit.jupiter.api.Test;

class HarrisBenedictImplTest {
  private HarrisBenedictImpl harrisBenedict = new HarrisBenedictImpl();

  @Test
  void calculate() {
    // Arrange
    final TdeeRequestData tdeeRequestMock = new TdeeRequestData("male",80,180,20,true,1,2);
    final TdeeRequestData tdeeRequestMock2 = new TdeeRequestData("female",90,170,30,true,1,3);
    // Act
    final int score = harrisBenedict.calculate(tdeeRequestMock);
    final int score2 = harrisBenedict.calculate(tdeeRequestMock2);
    // Assert
    assertEquals(1910, score);
    assertEquals(1676, score2);
  }
}
