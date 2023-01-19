package ca.ulaval.glo4002.cafe.domain.customer;

import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.receipt.Receipt;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategy;
import ca.ulaval.glo4002.cafe.rest.customer.CustomerAlreadyCheckedOutException;
import ca.ulaval.glo4002.cafe.rest.customer.InvalidCustomerIdException;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {

  private final List<Customer> customers = new ArrayList<>();

  public boolean customerAlreadyVisited(Customer customer) {
    return this.customers.stream().anyMatch(existingCustomer -> existingCustomer.equals(customer));
  }

  public void addCustomer(Customer customer) {
    this.customers.add(customer);
  }

  public Customer getCustomerById(String customerId) {
    return this.customers.stream().filter(customer -> customer.getId().equals(customerId)).findFirst().orElseThrow(InvalidCustomerIdException::new);
  }

  public void checkoutCustomer(String customerId, TaxStrategy tax, double tipRate) {
    Customer customer = this.getCustomerById(customerId);
    if (customer.isCheckedOut()) {
      throw new CustomerAlreadyCheckedOutException();
    }
    customer.checkout(tax, tipRate);
  }

  public List<Customer> getRemainingCustomersFromGroup(String groupName) {
    return this.customers.stream().filter(customer ->
      customer.getGroupName().isPresent() && customer.getGroupName().get().equals(groupName) && !customer.isCheckedOut()).toList();
  }

  public Receipt getCustomerReceipt(String customerId) {
    Customer customer = this.getCustomerById(customerId);
    return customer.getReceipt();
  }

  public List<Product> getCustomerOrder(String customerId) {
    Customer customer = this.getCustomerById(customerId);
    return customer.getOrders();
  }

  public List<Customer> getCustomers() {
    return this.customers;
  }

  public void clearAllCustomers() {
    this.customers.clear();
  }

}
