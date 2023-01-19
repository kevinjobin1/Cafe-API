package ca.ulaval.glo4002.cafe.rest.order;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.product.Product;

public class CustomerOrdersAssembler {

  public CustomerOrdersResponse toResponse(List<Product> orders) {
    List<String> orderedProductNames = new ArrayList<>();
    for (Product product : orders) {
      orderedProductNames.add(product.getName());
    }
    return new CustomerOrdersResponse(orderedProductNames);
  }

}
