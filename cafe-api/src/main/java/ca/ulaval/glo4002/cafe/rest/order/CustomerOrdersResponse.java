package ca.ulaval.glo4002.cafe.rest.order;

import java.util.List;

public class CustomerOrdersResponse {
  public List<String> orders;

  public CustomerOrdersResponse(List<String> orders) {
    this.orders = orders;
  }

}
