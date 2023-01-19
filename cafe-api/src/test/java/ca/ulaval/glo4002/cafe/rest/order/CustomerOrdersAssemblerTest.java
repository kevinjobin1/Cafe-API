package ca.ulaval.glo4002.cafe.rest.order;

import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerOrdersAssemblerTest {
  private static final String PRODUCT_NAME = "Espresso";
  private static final String ANOTHER_PRODUCT_NAME = "Latte";
  private static final double PRODUCT_PRICE = 2.0;
  private static final List<Ingredient> INGREDIENTS = new ArrayList<>();

  @Test
  void givenOrders_whenAssemblingResponse_thenResponseContainsOrderedProductNames() {
    CustomerOrdersAssembler customerOrdersAssembler = new CustomerOrdersAssembler();
    List<Product> orders = new ArrayList<>();
    orders.add(new Product(PRODUCT_NAME, PRODUCT_PRICE, INGREDIENTS));
    orders.add(new Product(ANOTHER_PRODUCT_NAME, PRODUCT_PRICE, INGREDIENTS));

    CustomerOrdersResponse response = customerOrdersAssembler.toResponse(orders);

    assertEquals(2, response.orders.size());
    assertEquals(PRODUCT_NAME, response.orders.get(0));
    assertEquals(ANOTHER_PRODUCT_NAME, response.orders.get(1));
  }
}
