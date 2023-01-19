package ca.ulaval.glo4002.cafe.domain.customer;

public class CustomerFactory {
  public Customer createCustomer(String id, String name, String groupName) {
    return new Customer(id, name, groupName);
  }
}
