package ca.ulaval.glo4002.cafe.rest.customer;

import ca.ulaval.glo4002.cafe.domain.customer.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerAssemblerTest {
  private static final String DEFAULT_CUSTOMER_ID = "abc-123";
  private static final String DEFAULT_CUSTOMER_NAME = "Jean DuNord";
  private static final String DEFAULT_GROUP_NAME = "Les gars du Sud";
  private static final int DEFAULT_SEAT_NUMBER = 1;

  private Customer customer;
  private CustomerAssembler customerAssembler;

  @BeforeEach
  public void setUp() {
    this.customerAssembler = new CustomerAssembler();
    this.customer = new Customer(DEFAULT_CUSTOMER_ID, DEFAULT_CUSTOMER_NAME, DEFAULT_GROUP_NAME);
  }

  @Test
  void givenCustomer_whenAssemblingResponse_thenResponseHasValidCustomerName() {
    CustomerResponse customerResponse = customerAssembler.toResponse(customer, DEFAULT_SEAT_NUMBER);

    Assertions.assertEquals(DEFAULT_CUSTOMER_NAME, customerResponse.name);
  }

  @Test
  void givenCustomer_whenAssemblingResponse_thenResponseHasValidCustomerGroup() {
    CustomerResponse customerResponse = customerAssembler.toResponse(customer, DEFAULT_SEAT_NUMBER);

    Assertions.assertEquals(DEFAULT_GROUP_NAME, customerResponse.group_name);
  }

  @Test
  void givenCustomer_whenAssemblingResponse_thenResponseHasValidSeatNumber() {
    CustomerResponse customerResponse = customerAssembler.toResponse(customer, DEFAULT_SEAT_NUMBER);

    Assertions.assertEquals(DEFAULT_SEAT_NUMBER, customerResponse.seat_number);
  }
}
