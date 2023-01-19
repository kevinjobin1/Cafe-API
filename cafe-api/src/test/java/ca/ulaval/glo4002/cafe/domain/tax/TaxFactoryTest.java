package ca.ulaval.glo4002.cafe.domain.tax;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxFactoryTest {
  Tax countryTax;
  Tax provinceTax;
  Tax stateTax;
  TaxStrategyFactory taxStrategyFactory;

  static final double CHILI_TAX_RATE = 0.19;
  static final double CANADA_TAX_RATE = 0.05;
  static final double US_TAX_RATE = 0.00;
  static final double NONE_TAX_RATE = 0.00;
  static final double A_PROVINCE_TAX_RATE = 0.09975;
  static final double A_STATE_TAX_RATE = 0.0560;
  static final double A_PRICE_BEFORE_TAX = 100.0;

  @BeforeEach
  void setUp() {
    taxStrategyFactory = new TaxStrategyFactory();
  }

  @Test
  void whenCreatingDefaultTax_thenReturnsRegularTaxWithZeroRate() {
    TaxStrategy tax = taxStrategyFactory.createDefaultTax();

    assertEquals(0.0, tax.getTotalTax(A_PRICE_BEFORE_TAX));
  }

  @Test
  void whenCreatingDefaultTax_thenRegularTaxIsReturned() {
    TaxStrategy tax = taxStrategyFactory.createDefaultTax();

    assertEquals(RegularTaxStrategy.class, tax.getClass());
  }

  @Test
  void givenWeAreInCanada_whenCreatingTax_thenCanadaTaxIsReturned() {
    countryTax = new Tax(CANADA_TAX_RATE, "CA");
    provinceTax = new Tax(A_PROVINCE_TAX_RATE, "QC");

    TaxStrategy tax = taxStrategyFactory.createTax(Optional.of(countryTax), Optional.of(provinceTax), Optional.empty());

    assertEquals(CanadaTaxStrategy.class, tax.getClass());
  }

  @Test
  void givenWeAreInUS_whenCreatingTax_thenUSTaxIsReturned() {
    countryTax = new Tax(US_TAX_RATE, "US");
    stateTax = new Tax(A_STATE_TAX_RATE, "AZ");

    TaxStrategy tax = taxStrategyFactory.createTax(Optional.of(countryTax), Optional.empty(), Optional.of(stateTax));

    assertEquals(UsTaxStrategy.class, tax.getClass());
  }

  @Test
  void givenWeAreInChili_whenCreatingTax_thenRegularTaxIsReturned() {
    countryTax = new Tax(CHILI_TAX_RATE, "CL");

    TaxStrategy tax = taxStrategyFactory.createTax(Optional.of(countryTax), Optional.empty(), Optional.empty());

    assertEquals(RegularTaxStrategy.class, tax.getClass());
  }

  @Test
  void givenWeAreInNone_whenCreatingTax_thenRegularTaxIsReturned() {
    countryTax = new Tax(NONE_TAX_RATE, "None");

    TaxStrategy tax = taxStrategyFactory.createTax(Optional.of(countryTax), Optional.empty(), Optional.empty());

    assertEquals(RegularTaxStrategy.class, tax.getClass());
  }

  @Test
  void givenWeAreInChili_whenCreatingTax_thenReturnsCorrectAmountOfTaxes() {
    countryTax = new Tax(CHILI_TAX_RATE, "CL");

    TaxStrategy tax = taxStrategyFactory.createTax(Optional.of(countryTax), Optional.empty(), Optional.empty());

    assertEquals(A_PRICE_BEFORE_TAX * CHILI_TAX_RATE, tax.getTotalTax(A_PRICE_BEFORE_TAX));
  }

  @Test
  void givenWeAreInCanada_whenCreatingTax_thenReturnsCorrectAmountOfTaxes() {
    countryTax = new Tax(CANADA_TAX_RATE, "CA");
    provinceTax = new Tax(A_PROVINCE_TAX_RATE, "QC");

    TaxStrategy tax = taxStrategyFactory.createTax(Optional.of(countryTax), Optional.of(provinceTax), Optional.empty());

    assertEquals((A_PRICE_BEFORE_TAX * CANADA_TAX_RATE) + (A_PRICE_BEFORE_TAX * A_PROVINCE_TAX_RATE), tax.getTotalTax(A_PRICE_BEFORE_TAX));
  }

  @Test
  void givenWeAreInUS_whenCreatingTax_thenReturnsCorrectAmountOfTaxes() {
    countryTax = new Tax(US_TAX_RATE, "US");
    stateTax = new Tax(A_STATE_TAX_RATE, "AZ");

    TaxStrategy tax = taxStrategyFactory.createTax(Optional.of(countryTax), Optional.empty(), Optional.of(stateTax));

    assertEquals((A_PRICE_BEFORE_TAX * US_TAX_RATE) + (A_PRICE_BEFORE_TAX * A_STATE_TAX_RATE), tax.getTotalTax(A_PRICE_BEFORE_TAX));
  }

  @Test
  void givenWeAreInChili_whenCreatingTax_thenIgnoresProvinceAndStateTaxes() {
    countryTax = new Tax(CHILI_TAX_RATE, "CL");
    provinceTax = new Tax(A_PROVINCE_TAX_RATE, "QC");
    stateTax = new Tax(A_STATE_TAX_RATE, "AZ");

    TaxStrategy tax = taxStrategyFactory.createTax(Optional.of(countryTax), Optional.of(provinceTax), Optional.of(stateTax));

    assertEquals(A_PRICE_BEFORE_TAX * CHILI_TAX_RATE, tax.getTotalTax(A_PRICE_BEFORE_TAX));
  }
}
