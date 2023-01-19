package ca.ulaval.glo4002.cafe.rest.customer;

import ca.ulaval.glo4002.cafe.domain.customer.Customer;

public class CustomerAssembler {

  public Customer fromRequest(CustomerCheckInRequest customerCheckInRequest) {
    return new Customer(customerCheckInRequest.customerId, customerCheckInRequest.customerName, customerCheckInRequest.groupName);
  }

  public CustomerResponse toResponse(Customer customer, int seatNumber) {
    return new CustomerResponse(customer.getName(), seatNumber, customer.getGroupName().orElse(null));
  }
}
