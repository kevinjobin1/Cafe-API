package ca.ulaval.glo4002.cafe.domain.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ca.ulaval.glo4002.cafe.domain.receipt.NoBillException;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.receipt.Receipt;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategy;

public class Customer {
  private final String id;
  private final String name;
  private final String groupName;
  private final List<Product> orders = new ArrayList<>();
  private final List<Receipt> receipts = new ArrayList<>();

  public Customer(String id, String name, String groupName) {
    this.id = id;
    this.name = name;
    this.groupName = groupName;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Optional<String> getGroupName() {
    return Optional.ofNullable(groupName);
  }

  public List<Product> getOrders() {
    return this.orders;
  }

  public void placeOrder(List<Product> product) {
    this.orders.addAll(product);
  }

  public void checkout(TaxStrategy tax, double tipRate) {
    if (this.getGroupName().isEmpty()) {
      tipRate = 0;
    }
    Receipt receipt = new Receipt(this.orders, tax, tipRate);
    this.receipts.add(receipt);
    this.orders.clear();
  }

  public Receipt getReceipt() {
    if (this.receipts.size() == 0) {
      throw new NoBillException();
    }
    return this.receipts.get(0);
  }

  public boolean isCheckedOut() {
    return this.receipts.size() != 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return id.equals(customer.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, groupName);
  }

}
