package ca.ulaval.glo4002.cafe.domain.tax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CanadaTaxTest {

  private static final double COUNTRY_TAX_RATE = 0.05;
  private static final double PROVINCE_TAX_RATE = 0.10;
  private static final double PRICE = 100.0;
  private static final double TOTAL_TAX = COUNTRY_TAX_RATE * PRICE + PROVINCE_TAX_RATE * PRICE;

  private CanadaTaxStrategy canadaTaxStrategy;

  @BeforeEach
  void setUp() {
    TaxStrategy provinceTax = mock(TaxStrategy.class);
    when(provinceTax.getTotalTax(PRICE)).thenReturn(PROVINCE_TAX_RATE * PRICE);
    canadaTaxStrategy = new CanadaTaxStrategy(COUNTRY_TAX_RATE, provinceTax);
  }

  @Test
  void whenGetTotalTax_thenReturnsTotalTax() {
    double totalTax = canadaTaxStrategy.getTotalTax(PRICE);

    assertEquals(TOTAL_TAX, totalTax);
  }
}
