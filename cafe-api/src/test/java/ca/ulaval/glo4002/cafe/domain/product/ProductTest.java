package ca.ulaval.glo4002.cafe.domain.product;

import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

  private static final String A_PRODUCT_NAME = "A product name";
  private static final double A_PRODUCT_PRICE = 1.0;

  private Product product;
  private List<Ingredient> ingredientList;

  @BeforeEach
  void setUp() {
    ingredientList = new ArrayList<>();
    product = new Product(A_PRODUCT_NAME, A_PRODUCT_PRICE, ingredientList);
  }

  @Test
  void whenGettingName_thenReturnsTheProductName() {
    assertEquals(A_PRODUCT_NAME, product.getName());
  }

  @Test
  void whenGettingPrice_thenReturnsTheProductPrice() {
    assertEquals(A_PRODUCT_PRICE, product.getPrice());
  }
}
