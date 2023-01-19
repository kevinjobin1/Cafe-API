package ca.ulaval.glo4002.cafe.domain.customer;

import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.receipt.Receipt;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategy;
import ca.ulaval.glo4002.cafe.rest.customer.CustomerAlreadyCheckedOutException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerListTest {
  private static final String CUSTOMER_ID = "1";
  private static final String CUSTOMER_NAME = "Michael Scarn";
  private static final String CUSTOMER_GROUP_NAME = "Group 1";
  private static final String ANOTHER_CUSTOMER_ID = "2";
  private static final String ANOTHER_CUSTOMER_NAME = "Kanye West";
  private static final int DEFAULT_TIP_RATE = 8;

  @Mock
  private TaxStrategy taxStrategy;
  private Customer aCustomer;
  private Customer anotherCustomer;
  private List<Product> anOrder;
  private CustomerList customerList;

  @BeforeEach
  public void setUp() {
    this.aCustomer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_GROUP_NAME);
    this.anotherCustomer = new Customer(ANOTHER_CUSTOMER_ID, ANOTHER_CUSTOMER_NAME, CUSTOMER_GROUP_NAME);
    Product product = new Product("Orange", 100, new ArrayList<>());
    this.anOrder = Arrays.asList(product);
    this.customerList = new CustomerList();
  }

  @Test
  void givenACustomerWhoAlreadyVisited_whenCustomerAlreadyVisited_thenShouldReturnTrue() {
    this.customerList.addCustomer(aCustomer);

    boolean alreadyVisited = this.customerList.customerAlreadyVisited(aCustomer);

    assertTrue(alreadyVisited);
  }

  @Test
  void givenACustomerWhoNeverVisited_whenCustomerAlreadyVisited_thenShouldReturnFalse() {
    boolean alreadyVisited = this.customerList.customerAlreadyVisited(aCustomer);

    assertFalse(alreadyVisited);
  }

  @Test
  void whenCustomerVisits_thenHeIsAddedToCustomerList() {
    this.customerList.addCustomer(aCustomer);

    assertEquals(aCustomer, customerList.getCustomers().get(0));
  }

  @Test
  void givenPresentCustomer_whenCheckingOut_thenCustomerIsCheckedOut() {
    this.customerList.addCustomer(aCustomer);

    this.customerList.checkoutCustomer(aCustomer.getId(), taxStrategy, DEFAULT_TIP_RATE);

    assertTrue(aCustomer.isCheckedOut());
  }

  @Test
  void givenPresentCustomer_whenCheckingOutTwice_thenThrowsCustomerAlreadyCheckedOutException() {
    this.customerList.addCustomer(aCustomer);

    this.customerList.checkoutCustomer(aCustomer.getId(), taxStrategy, DEFAULT_TIP_RATE);

    assertThrows(CustomerAlreadyCheckedOutException.class, () -> this.customerList.checkoutCustomer(aCustomer.getId(), taxStrategy, DEFAULT_TIP_RATE));
  }

  @Test
  void givenTwoCustomersFromSameGroup_whenCheckingOut_thenRemainingMemberIsReturned() {
    this.customerList.addCustomer(aCustomer);
    this.customerList.addCustomer(anotherCustomer);

    this.customerList.checkoutCustomer(aCustomer.getId(), taxStrategy, DEFAULT_TIP_RATE);

    assertEquals(anotherCustomer, this.customerList.getRemainingCustomersFromGroup(CUSTOMER_GROUP_NAME).get(0));
  }

  @Test
  void givenCheckedInCustomers_whenClearingAllCustomers_thenNoCustomerIsLeft() {
    this.customerList.addCustomer(aCustomer);
    this.customerList.addCustomer(anotherCustomer);

    this.customerList.clearAllCustomers();

    assertEquals(this.customerList.getCustomers().size(), 0);
  }

  @Test
  void givenCustomerWithActiveOrder_whenGettingOrder_thenCorrectOrderIsReturned() {
    this.customerList.addCustomer(aCustomer);
    aCustomer.placeOrder(anOrder);

    List<Product> order = this.customerList.getCustomerOrder(aCustomer.getId());

    assertEquals(order, anOrder);
  }

  @Test
  void givenCheckedOutCustomer_whenGettingReceipt_thenCorrectReceiptIsReturned() {
    this.customerList.addCustomer(aCustomer);
    aCustomer.placeOrder(anOrder);
    this.customerList.checkoutCustomer(CUSTOMER_ID, taxStrategy, DEFAULT_TIP_RATE);

    Receipt receipt = this.customerList.getCustomerReceipt(aCustomer.getId());

    assertEquals(receipt.getOrders(), anOrder);
  }

}
