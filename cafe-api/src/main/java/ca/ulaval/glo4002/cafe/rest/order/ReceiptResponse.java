package ca.ulaval.glo4002.cafe.rest.order;

import java.util.List;

public class ReceiptResponse {

  public List<String> orders;
  public double subtotal;
  public double taxes;
  public double tip;
  public double total;

  public ReceiptResponse(List<String> orders, double subtotal, double taxes, double tip, double total) {
    this.orders = orders;
    this.subtotal = subtotal;
    this.taxes = taxes;
    this.tip = tip;
    this.total = total;
  }

}
