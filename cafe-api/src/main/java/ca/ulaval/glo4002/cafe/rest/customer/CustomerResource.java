package ca.ulaval.glo4002.cafe.rest.customer;

import java.net.URI;
import java.util.List;

import ca.ulaval.glo4002.cafe.application.cafe.CafeService;
import ca.ulaval.glo4002.cafe.domain.customer.Customer;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.receipt.Receipt;
import ca.ulaval.glo4002.cafe.rest.order.*;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class CustomerResource {
  private final CustomerAssembler customerAssembler;
  private final CafeService cafeService;
  private final CustomerOrdersAssembler customerOrdersAssembler;
  private final ReceiptAssembler receiptAssembler;

  public CustomerResource(CustomerAssembler customerAssembler, CafeService cafeService, CustomerOrdersAssembler customerOrdersAssembler,
                          ReceiptAssembler receiptAssembler) {
    this.customerAssembler = customerAssembler;
    this.cafeService = cafeService;
    this.customerOrdersAssembler = customerOrdersAssembler;
    this.receiptAssembler = receiptAssembler;
  }

  @POST()
  @Path("check-in")
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response postCustomer(CustomerCheckInRequest customerRequest) {
    Customer customer = this.customerAssembler.fromRequest(customerRequest);
    cafeService.checkInCustomer(customer);
    return Response.created(URI.create("customers/" + customer.getId())).build();
  }

  @POST()
  @Path("checkout")
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response checkoutCustomer(CustomerCheckoutRequest customerCheckoutRequest) {
    cafeService.checkoutCustomer(customerCheckoutRequest.customerId);
    return Response.created(URI.create("customers/" + customerCheckoutRequest.customerId + "/bill")).build();
  }

  @GET()
  @Path("customers/{CUSTOMER_ID}/bill")
  @Produces({MediaType.APPLICATION_JSON})
  public Response getCustomerReceipt(@PathParam("CUSTOMER_ID") String customerId) {
    Receipt receipt = this.cafeService.getCustomerReceipt(customerId);
    ReceiptResponse receiptResponse = this.receiptAssembler.toResponse(receipt);
    return Response.ok().entity(receiptResponse).build();
  }

  @GET()
  @Path("customers/{CUSTOMER_ID}")
  @Produces({MediaType.APPLICATION_JSON})
  public Response getCustomer(@PathParam("CUSTOMER_ID") String customerId) {
    Customer customer = this.cafeService.getCustomerById(customerId);
    int seatNumber = this.cafeService.findCustomerSeatNumber(customerId);
    CustomerResponse customerResponse = this.customerAssembler.toResponse(customer, seatNumber);
    return Response.ok().entity(customerResponse).build();
  }

  @GET
  @Path("customers/{CUSTOMER_ID}/orders")
  @Produces({MediaType.APPLICATION_JSON})
  public Response getCustomerOrders(@PathParam("CUSTOMER_ID") String customerId) {
    List<Product> orders = this.cafeService.getCustomerOrder(customerId);
    CustomerOrdersResponse orderResponse = this.customerOrdersAssembler.toResponse(orders);
    return Response.ok().entity(orderResponse).build();
  }

  @PUT
  @Path("customers/{CUSTOMER_ID}/orders")
  @Produces({MediaType.APPLICATION_JSON})
  public Response putCustomerOrders(@PathParam("CUSTOMER_ID") String customerId, CustomerOrdersRequest customerOrdersRequest) {
    this.cafeService.orderProducts(customerId, List.of(customerOrdersRequest.orders));
    return Response.ok().build();
  }

}
