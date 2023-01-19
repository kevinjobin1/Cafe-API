package ca.ulaval.glo4002.cafe.domain.receipt;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategy;

public class Receipt {
  private final List<Product> orders = new ArrayList<>();
  private final TaxStrategy tax;
  private final double tipRate;

  public Receipt(List<Product> orders, TaxStrategy tax, double tipRate) {
    this.tax = tax;
    this.tipRate = tipRate;
    for (Product product : orders) {
      this.orders.add(product);
    }
  }

  public List<Product> getOrders() {
    return this.orders;
  }

  public double getSubTotal() {
    double subTotal = 0;
    for (Product product : this.orders) {
      subTotal += product.getPrice();
    }
    return subTotal;
  }

  public double getTaxes() {
    return this.tax.getTotalTax(this.getSubTotal());
  }

  public double getTotal() {
    return this.getSubTotal() + this.getTaxes() + this.getTip();
  }

  public double getTip() {
    return tipRate * this.getSubTotal() / 100;
  }
}
