package ca.ulaval.glo4002.cafe.domain.tax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UsTaxTest {

  private static final double COUNTRY_TAX_RATE = 0.03;
  private static final double STATE_TAX_RATE = 0.05;
  private static final double PRICE = 100.0;
  private static final double TOTAL_TAX = COUNTRY_TAX_RATE * PRICE + STATE_TAX_RATE * PRICE;

  private UsTaxStrategy usTaxStrategy;

  @BeforeEach
  void setUp() {
    TaxStrategy stateTax = mock(TaxStrategy.class);
    when(stateTax.getTotalTax(PRICE)).thenReturn(STATE_TAX_RATE * PRICE);
    usTaxStrategy = new UsTaxStrategy(COUNTRY_TAX_RATE, stateTax);
  }

  @Test
  void whenGetTotalTax_thenReturnsTotalTax() {
    double totalTax = usTaxStrategy.getTotalTax(PRICE);

    assertEquals(TOTAL_TAX, totalTax);
  }
}
