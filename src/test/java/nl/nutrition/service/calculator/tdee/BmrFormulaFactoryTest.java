package nl.nutrition.service.calculator.tdee;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BmrFormulaFactoryTest {
  final BmrFormulaFactory bmrFormulaFactory = new BmrFormulaFactory();

  @Test
  void getFormula() {
    // Arrange
    // Act
    final BmrFormula shouldBeMifflin = bmrFormulaFactory.getFormula(0);
    final BmrFormula shouldBeHarris = bmrFormulaFactory.getFormula(1);
    final BmrFormula shouldBeMifflinToo = bmrFormulaFactory.getFormula(-1);

    // Assert
    assertTrue(shouldBeMifflin instanceof MifflinStJeorImpl);
    assertTrue(shouldBeHarris instanceof HarrisBenedictImpl);
    assertTrue(shouldBeMifflinToo instanceof MifflinStJeorImpl);
  }
}
