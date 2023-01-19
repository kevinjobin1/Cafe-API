package ca.ulaval.glo4002.cafe.rest.customer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


import ca.ulaval.glo4002.cafe.application.cafe.CafeService;
import ca.ulaval.glo4002.cafe.domain.customer.Customer;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.rest.order.CustomerOrdersAssembler;
import ca.ulaval.glo4002.cafe.rest.order.CustomerOrdersRequest;
import ca.ulaval.glo4002.cafe.rest.order.CustomerOrdersResponse;
import ca.ulaval.glo4002.cafe.rest.order.ReceiptAssembler;
import jakarta.ws.rs.core.Response;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class CustomerResourceTest {
  private static final String A_CUSTOMER_ID = "123456789";
  private static final String A_CUSTOMER_NAME = "John Doe";
  private static final String A_GROUP_NAME = "X-Men";
  private static final String CUSTOMERS_PATH = "customers/";
  private static final int A_CUSTOMER_SEAT_NUMBER = 8;
  private static final String A_PRODUCT_NAME = "Espresso";
  private static final double A_PRODUCT_PRICE = 2.05;
  private static final String A_SECOND_PRODUCT_NAME = "Cappuccino";
  private static final double A_SECOND_PRODUCT_PRICE = 2.25;
  private static final String A_THIRD_PRODUCT_NAME = "Latte";
  private static final double A_THIRD_PRODUCT_PRICE = 2.45;
  private static final String LOCATION_HEADER = "Location";
  private static final List<String> A_CUSTOMER_ORDERS_NAMES = List.of(A_PRODUCT_NAME, A_SECOND_PRODUCT_NAME, A_THIRD_PRODUCT_NAME);
  private static final List<Double> A_CUSTOMER_ORDERS_PRICES = List.of(A_PRODUCT_PRICE, A_SECOND_PRODUCT_PRICE, A_THIRD_PRODUCT_PRICE);
  private static final List<Product> A_CUSTOMER_ORDERS = IntStream.range(0, A_CUSTOMER_ORDERS_NAMES.size()).mapToObj(
      i -> new Product(A_CUSTOMER_ORDERS_NAMES.get(i), A_CUSTOMER_ORDERS_PRICES.get(i), List.of())).toList();
  private static final List<Product> AN_EMPTY_CUSTOMER_ORDERS = List.of();
  private static final List<String> A_NEW_CUSTOMER_ORDERS = List.of(A_PRODUCT_NAME, A_SECOND_PRODUCT_NAME);
  private final CustomerResponse A_CUSTOMER_WITHOUT_GROUP_RESPONSE = new CustomerResponse(A_CUSTOMER_NAME, A_CUSTOMER_SEAT_NUMBER, null);
  private final CustomerResponse A_CUSTOMER_WITH_GROUP_RESPONSE = new CustomerResponse(A_CUSTOMER_NAME, A_CUSTOMER_SEAT_NUMBER, A_GROUP_NAME);
  private final CustomerOrdersResponse A_CUSTOMER_ORDERS_RESPONSE = new CustomerOrdersResponse(A_CUSTOMER_ORDERS_NAMES);
  private final CustomerOrdersResponse AN_EMPTY_CUSTOMER_ORDERS_RESPONSE = new CustomerOrdersResponse(List.of());
  private final Customer A_CUSTOMER_WITHOUT_GROUP = new Customer(A_CUSTOMER_ID, A_CUSTOMER_NAME, null);
  private final Customer A_CUSTOMER_WITH_GROUP = new Customer(A_CUSTOMER_ID, A_CUSTOMER_NAME, A_GROUP_NAME);
  private CustomerAssembler customerAssembler;
  private CustomerOrdersAssembler customerOrdersAssembler;
  private ReceiptAssembler receiptAssembler;
  private CafeService cafeService;
  private CustomerCheckInRequest aCustomerCheckInRequest;
  private CustomerOrdersRequest aCustomerOrdersRequest;
  private CustomerResource customerResource;

  @BeforeEach
  public void setup() {
    this.aCustomerCheckInRequest = new CustomerCheckInRequest();
    this.aCustomerCheckInRequest.customerId = A_CUSTOMER_ID;
    this.aCustomerCheckInRequest.customerName = A_CUSTOMER_NAME;
    this.aCustomerCheckInRequest.groupName = A_GROUP_NAME;

    this.aCustomerOrdersRequest = new CustomerOrdersRequest();
    this.aCustomerOrdersRequest.orders = A_NEW_CUSTOMER_ORDERS.toArray(new String[0]);

    this.cafeService = mock(CafeService.class);
    this.customerAssembler = mock(CustomerAssembler.class);
    this.customerOrdersAssembler = mock(CustomerOrdersAssembler.class);
    this.receiptAssembler = mock(ReceiptAssembler.class);
    this.customerResource = new CustomerResource(customerAssembler, cafeService, customerOrdersAssembler, receiptAssembler);
  }

  @Test
  public void givenACustomerCheckInRequest_whenPostingCustomerCheckIn_thenReturnsValidHeaderLocationWithCustomerIdAndStatusCreated() {
    when(customerAssembler.fromRequest(aCustomerCheckInRequest)).thenReturn(A_CUSTOMER_WITH_GROUP);
    doNothing().when(cafeService).checkInCustomer(A_CUSTOMER_WITH_GROUP);

    Response response = customerResource.postCustomer(aCustomerCheckInRequest);

    assertEquals(SC_CREATED, response.getStatus());
    assertEquals(CUSTOMERS_PATH + A_CUSTOMER_WITH_GROUP.getId(), response.getHeaderString(LOCATION_HEADER));
  }

  @Test
  public void givenACustomerCheckedInWithoutGroup_whenGettingCustomer_thenReturnsSameCustomerWithNullGroupNameAndStatusOk() {
    Customer expectedCustomer = A_CUSTOMER_WITHOUT_GROUP;
    int expectedSeatNumber = A_CUSTOMER_SEAT_NUMBER;
    when(cafeService.getCustomerById(expectedCustomer.getId())).thenReturn(expectedCustomer);
    when(cafeService.findCustomerSeatNumber(expectedCustomer.getId())).thenReturn(expectedSeatNumber);
    when(customerAssembler.toResponse(expectedCustomer, expectedSeatNumber)).thenReturn(A_CUSTOMER_WITHOUT_GROUP_RESPONSE);

    Response response = customerResource.getCustomer(expectedCustomer.getId());

    CustomerResponse actualCustomer = (CustomerResponse) response.getEntity();
    assertEquals(SC_OK, response.getStatus());
    assertEquals(expectedCustomer.getName(), actualCustomer.name);
    assertEquals(expectedSeatNumber, actualCustomer.seat_number);
    assertNull(actualCustomer.group_name);
  }

  @Test
  public void givenACustomerCheckedInWithGroup_whenGettingCustomer_thenReturnsSameCustomerWithStatusOk() {
    Customer expectedCustomer = this.A_CUSTOMER_WITH_GROUP;
    int expectedSeatNumber = A_CUSTOMER_SEAT_NUMBER;
    when(cafeService.getCustomerById(expectedCustomer.getId())).thenReturn(expectedCustomer);
    when(cafeService.findCustomerSeatNumber(expectedCustomer.getId())).thenReturn(expectedSeatNumber);
    when(customerAssembler.toResponse(expectedCustomer, expectedSeatNumber)).thenReturn(A_CUSTOMER_WITH_GROUP_RESPONSE);

    Response response = customerResource.getCustomer(expectedCustomer.getId());

    CustomerResponse actualCustomer = (CustomerResponse) response.getEntity();
    assertEquals(SC_OK, response.getStatus());
    assertEquals(expectedCustomer.getName(), actualCustomer.name);
    assertEquals(expectedCustomer.getGroupName().get(), actualCustomer.group_name);
    assertEquals(expectedSeatNumber, actualCustomer.seat_number);
  }

  @Test
  public void givenACustomerWithoutOrders_whenGettingCustomerOrders_thenReturnsEmptyOrdersWithStatusOk() {
    List<Product> expectedCustomerOrders = AN_EMPTY_CUSTOMER_ORDERS;
    when(cafeService.getCustomerOrder(A_CUSTOMER_ID)).thenReturn(expectedCustomerOrders);
    when(customerOrdersAssembler.toResponse(expectedCustomerOrders)).thenReturn(AN_EMPTY_CUSTOMER_ORDERS_RESPONSE);

    Response response = customerResource.getCustomerOrders(A_CUSTOMER_ID);

    CustomerOrdersResponse actualCustomerOrders = (CustomerOrdersResponse) response.getEntity();
    assertEquals(SC_OK, response.getStatus());
    assertEquals(0, actualCustomerOrders.orders.size());
  }

  @Test
  public void givenACustomerWithOrders_whenGettingCustomerOrders_thenReturnsSameCustomerOrdersWithStatusOk() {
    List<Product> expectedCustomerOrders = A_CUSTOMER_ORDERS;
    when(cafeService.getCustomerOrder(A_CUSTOMER_ID)).thenReturn(expectedCustomerOrders);
    when(customerOrdersAssembler.toResponse(expectedCustomerOrders)).thenReturn(A_CUSTOMER_ORDERS_RESPONSE);

    Response response = customerResource.getCustomerOrders(A_CUSTOMER_ID);

    CustomerOrdersResponse actualCustomerOrders = (CustomerOrdersResponse) response.getEntity();
    assertEquals(SC_OK, response.getStatus());
    assertEquals(expectedCustomerOrders.stream().map(Product::getName).collect(Collectors.toList()), actualCustomerOrders.orders);
  }

  @Test
  public void whenPuttingNewOrder_thenReturnsStatusOk() {
    Response response = customerResource.putCustomerOrders(A_CUSTOMER_ID, aCustomerOrdersRequest);

    assertEquals(SC_OK, response.getStatus());
  }
}
