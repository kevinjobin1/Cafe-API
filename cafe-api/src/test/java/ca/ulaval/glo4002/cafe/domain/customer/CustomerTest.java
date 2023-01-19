package ca.ulaval.glo4002.cafe.domain.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import ca.ulaval.glo4002.cafe.domain.product.Product;

class CustomerTest {
  private static final String DEFAULT_CUSTOMER_ID = "abc-123";
  private static final String DEFAULT_CUSTOMER_NAME = "Jean Dubois";
  private static final String GROUP_NAME = "Scott's Tots";
  private static final String PRODUCT_NAME = "Coffee";
  private static final double PRODUCT_PRICE = 1.0;
  private Customer customerWithoutGroup;
  private Customer customerWithGroup;

  @BeforeEach
  public void setup() {
    customerWithoutGroup = new Customer(DEFAULT_CUSTOMER_ID, DEFAULT_CUSTOMER_NAME, null);
    customerWithGroup = new Customer(DEFAULT_CUSTOMER_ID, DEFAULT_CUSTOMER_NAME, GROUP_NAME);
  }

  @Test
  void whenCreatingCustomer_thenIdAndNameAreSet() {
    assertEquals(DEFAULT_CUSTOMER_ID, customerWithoutGroup.getId());
    assertEquals(DEFAULT_CUSTOMER_NAME, customerWithoutGroup.getName());
  }

  @Test
  void whenCreatingCustomerWithoutGroup_thenGroupNameIsEmpty() {
    assertTrue(customerWithoutGroup.getGroupName().isEmpty());
  }

  @Test
  void whenCreatingCustomerWithGroup_thenGroupNameIsSet() {
    assertEquals(Optional.of(GROUP_NAME), customerWithGroup.getGroupName());
  }

  @Test
  void givenCustomerWithoutOrders_whenPlacingCustomerOrderForOneProduct_thenOrderIsAdded() {
    Customer aCustomerWithoutOrders = new Customer(DEFAULT_CUSTOMER_ID, DEFAULT_CUSTOMER_NAME, null);

    List<Product> product = new ArrayList<>();
    product.add(new Product(PRODUCT_NAME, PRODUCT_PRICE, List.of()));
    aCustomerWithoutOrders.placeOrder(product);

    assertNotEquals(0, aCustomerWithoutOrders.getOrders().size());
  }

  @Test
  void givenCustomerWithoutOrders_whenPlacingCustomerOrderForMultipleProducts_thenOrderIsAdded() {
    Customer aCustomerWithoutOrders = new Customer(DEFAULT_CUSTOMER_ID, DEFAULT_CUSTOMER_NAME, null);

    List<Product> products = new ArrayList<>();
    products.add(new Product(PRODUCT_NAME, PRODUCT_PRICE, List.of()));
    products.add(new Product(PRODUCT_NAME, PRODUCT_PRICE, List.of()));
    aCustomerWithoutOrders.placeOrder(products);

    assertEquals(2, aCustomerWithoutOrders.getOrders().size());
  }
}
