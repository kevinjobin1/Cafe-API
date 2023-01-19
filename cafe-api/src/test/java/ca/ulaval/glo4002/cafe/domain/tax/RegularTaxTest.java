package ca.ulaval.glo4002.cafe.domain.tax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegularTaxTest {

  private static final double TAX_RATE = 0.05;
  private static final double PRICE = 100.0;
  private static final double TAX = PRICE * TAX_RATE;

  private RegularTaxStrategy regularTaxStrategy;

  @BeforeEach
  void setUp() {
    regularTaxStrategy = new RegularTaxStrategy(TAX_RATE);
  }

  @Test
  void whenGetTotalTax_thenReturnsTotalTax() {
    double totalTax = regularTaxStrategy.getTotalTax(PRICE);

    assertEquals(TAX, totalTax);
  }
}

