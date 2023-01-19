package ca.ulaval.glo4002.cafe.domain.tax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryTaxTest {

  private static final double TAX_RATE = 0.05;
  private static final String TAX_ACRONYM = "QC";

  private Tax tax;

  @BeforeEach
  void setUp() {
    tax = new Tax(TAX_RATE, TAX_ACRONYM);
  }

  @Test
  void whenGetRate_thenReturnsRate() {
    double rate = tax.getRate();

    assertEquals(TAX_RATE, rate);
  }

  @Test
  void whenGetAcronym_thenReturnsAcronym() {
    String acronym = tax.getAcronym();

    assertEquals(TAX_ACRONYM, acronym);
  }
}
