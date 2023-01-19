package ca.ulaval.glo4002.cafe.domain.customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerFactoryTest {

  private static final String EXPECTED_CUSTOMER_ID = "abd4bced-4fce-44a0-aa41-c41c5777e679";
  private static final String EXPECTED_CUSTOMER_NAME = "John Doe";
  private static final String EXPECTED_GROUP_NAME = "Scott's Tots";

  private CustomerFactory customerFactory;

  @BeforeEach
  public void setUp() {
    customerFactory = new CustomerFactory();
  }

  @Test
  void givenCustomerWithNoGroupName_whenCreatingCustomer_thenReturnsCustomerWithSameIDAndName() {
    Customer actualCustomer = customerFactory.createCustomer(EXPECTED_CUSTOMER_ID, EXPECTED_CUSTOMER_NAME, null);

    assertEquals(EXPECTED_CUSTOMER_ID, actualCustomer.getId());
    assertEquals(EXPECTED_CUSTOMER_NAME, actualCustomer.getName());
    assertTrue(actualCustomer.getGroupName().isEmpty());
  }

  @Test
  void givenCustomerWithAGroupName_whenCreatingCustomer_thenReturnsCustomerWithSameIDAndNameAndGroupName() {
    Customer actualCustomer = customerFactory.createCustomer(EXPECTED_CUSTOMER_ID, EXPECTED_CUSTOMER_NAME, EXPECTED_GROUP_NAME);

    assertEquals(EXPECTED_CUSTOMER_ID, actualCustomer.getId());
    assertEquals(EXPECTED_CUSTOMER_NAME, actualCustomer.getName());
    assertEquals(Optional.of(EXPECTED_GROUP_NAME), actualCustomer.getGroupName());
  }
}
