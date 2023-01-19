package ca.ulaval.glo4002.cafe.domain.receipt;

import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ca.ulaval.glo4002.cafe.domain.tax.Tax;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategy;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptTest {
  private static final String A_PRODUCT_NAME = "A product name";
  private static final double A_PRODUCT_PRICE = 123.45;
  private final List<Ingredient> A_LIST_OF_INGREDIENT = new ArrayList<>();
  private final Product A_PRODUCT = new Product(A_PRODUCT_NAME, A_PRODUCT_PRICE, A_LIST_OF_INGREDIENT);

  private static final String ANOTHER_PRODUCT_NAME = "Another product name";
  private static final double ANOTHER_PRODUCT_PRICE = 234.56;
  private final Product ANOTHER_PRODUCT = new Product(ANOTHER_PRODUCT_NAME, ANOTHER_PRODUCT_PRICE, A_LIST_OF_INGREDIENT);

  private final List<Product> AN_ORDER = new ArrayList<>();

  private final TaxStrategyFactory taxStrategyFactory = new TaxStrategyFactory();
  private final TaxStrategy DEFAULT_TAX_RATE = taxStrategyFactory.createDefaultTax();

  private final Tax CANADA_TAX_RATE = new Tax(5.0, "CA");
  private final Tax PROVINCE_TAX_RATE = new Tax(9.975, "QC");
  private final TaxStrategy TOTAL_CANADA_TAX_RATE = taxStrategyFactory.createTax(Optional.of(CANADA_TAX_RATE),
      Optional.of(PROVINCE_TAX_RATE), Optional.empty());

  private final Tax US_TAX_RATE = new Tax(0.0, "US");
  private final Tax STATE_TAX_RATE = new Tax(5.60, "AZ");
  private final TaxStrategy TOTAL_US_TAX_RATE = taxStrategyFactory.createTax(Optional.of(US_TAX_RATE), Optional.empty(), Optional.of(STATE_TAX_RATE));

  private final double NO_TIP_RATE = 0.0;
  private final double A_TIP_RATE = 15.0;

  private Receipt receipt;


  @BeforeEach
  void setUp() {
    AN_ORDER.add(A_PRODUCT);
    AN_ORDER.add(ANOTHER_PRODUCT);
  }

  @Test
  void givenReceiptWithOrders_whenGettingOrders_thenReturnsTheOrders() {
    receipt = new Receipt(AN_ORDER, DEFAULT_TAX_RATE, A_TIP_RATE);

    List<Product> actualOrder = receipt.getOrders();

    assertEquals(AN_ORDER, actualOrder);
  }

  @Test
  void givenReceiptWithOrderOfMultipleProducts_whenGettingSubTotal_thenReturnsTheSumOfTheProductsPrices() {
    receipt = new Receipt(AN_ORDER, DEFAULT_TAX_RATE, A_TIP_RATE);

    double actualSubTotal = receipt.getSubTotal();

    assertEquals(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE, actualSubTotal);
  }

  @Test
  void givenReceiptWithOrderOfMultipleProductsAndDefaultTaxRateAndNoTip_whenGettingTotal_thenReturnsTheSumOfTheProductsPrices() {
    receipt = new Receipt(AN_ORDER, DEFAULT_TAX_RATE, NO_TIP_RATE);

    double actualTotal = receipt.getTotal();

    assertEquals(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE, actualTotal);
  }

  @Test
  void givenReceiptWithOrderOfMultipleProductsAndCanadianTaxRateAndNoTip_whenGettingTotal_thenReturnsTheSumOfTheProductsPricesPlusTaxes() {
    receipt = new Receipt(AN_ORDER, TOTAL_CANADA_TAX_RATE, NO_TIP_RATE);

    double actualTotal = receipt.getTotal();

    assertEquals(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE + TOTAL_CANADA_TAX_RATE.getTotalTax(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE),
        actualTotal);
  }

  @Test
  void givenReceiptWithOrderOfMultipleProductsAndCanadaTaxRateAndTip_whenGettingTotal_thenReturnsTheSumOfTheProductsPricesPlusTaxesAndTip() {
    receipt = new Receipt(AN_ORDER, TOTAL_CANADA_TAX_RATE, A_TIP_RATE);

    double actualTotal = receipt.getTotal();

    assertEquals(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE + TOTAL_CANADA_TAX_RATE.getTotalTax(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE)
        + A_TIP_RATE * (A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE) / 100, actualTotal);
  }

  @Test
  void givenReceiptWithOrderOfMultipleProductsAndUSTaxRateAndTip_whenGettingTotal_thenReturnsTheSumOfTheProductsPricesPlusTaxes() {
    receipt = new Receipt(AN_ORDER, TOTAL_US_TAX_RATE, A_TIP_RATE);

    double actualTotal = receipt.getTotal();

    assertEquals(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE + TOTAL_US_TAX_RATE.getTotalTax(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE)
        + A_TIP_RATE * (A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE) / 100, actualTotal);
  }

  @Test
  void givenDefaultTax_whenGettingTaxes_thenReturnsTheCorrectTotalTaxes() {
    receipt = new Receipt(AN_ORDER, DEFAULT_TAX_RATE, A_TIP_RATE);

    double actualTaxes = receipt.getTaxes();

    assertEquals(DEFAULT_TAX_RATE.getTotalTax(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE), actualTaxes);
  }

  @Test
  void givenDefaultTax_whenGettingTaxes_thenReceiptIsFreeOfTaxes() {
    receipt = new Receipt(AN_ORDER, DEFAULT_TAX_RATE, A_TIP_RATE);

    double actualTaxes = receipt.getTaxes();

    assertEquals(0.0, actualTaxes);
  }

  @Test
  void givenCanadaTax_whenGettingTaxes_thenReturnsCorrectTotalTaxes() {
    receipt = new Receipt(AN_ORDER, TOTAL_CANADA_TAX_RATE, A_TIP_RATE);

    double actualTaxes = receipt.getTaxes();

    assertEquals(TOTAL_CANADA_TAX_RATE.getTotalTax(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE), actualTaxes);
  }

  @Test
  void givenUSTax_whenGettingTaxes_thenReturnsCorrectTotalTaxes() {
    receipt = new Receipt(AN_ORDER, TOTAL_US_TAX_RATE, A_TIP_RATE);

    double actualTaxes = receipt.getTaxes();

    assertEquals(TOTAL_US_TAX_RATE.getTotalTax(A_PRODUCT_PRICE + ANOTHER_PRODUCT_PRICE), actualTaxes);
  }

  @Test
  void givenReceiptWith0TipRate_whenGettingTip_thenReturnsZero() {
    receipt = new Receipt(AN_ORDER, DEFAULT_TAX_RATE, NO_TIP_RATE);

    double actualTip = receipt.getTip();

    assertEquals(0.0, actualTip);
  }

  @Test
  void givenReceiptWithCustomTipRate_whenGettingTip_thenReturnsTheCorrectTip() {
    receipt = new Receipt(AN_ORDER, DEFAULT_TAX_RATE, A_TIP_RATE);

    double actualTip = receipt.getTip();

    assertEquals((A_TIP_RATE / 100) * receipt.getSubTotal(), actualTip);
  }

  @Test
  void givenTwoReceiptsForSameOrderWithSameTipRateButDifferentTaxes_whenGettingTip_thenReturnsTheSameTip() {
    receipt = new Receipt(AN_ORDER, DEFAULT_TAX_RATE, A_TIP_RATE);
    Receipt anotherReceipt = new Receipt(AN_ORDER, TOTAL_US_TAX_RATE, A_TIP_RATE);

    double actualTip = receipt.getTip();
    double anotherActualTip = anotherReceipt.getTip();

    assertEquals(actualTip, anotherActualTip);
  }
}
