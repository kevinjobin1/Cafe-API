package ca.ulaval.glo4002.cafe.domain.product;

import ca.ulaval.glo4002.cafe.domain.cafe.InvalidMenuOrderException;

import java.util.ArrayList;
import java.util.List;

public class Menu {

  private final List<Product> products;

  public Menu(List<Product> products) {
    this.products = products;
  }

  public List<Product> getProductsByName(List<String> names) {
    List<Product> orderedProducts = new ArrayList<>();
    for (String name : names) {
      Product orderedProduct = this.products.stream().filter(existingProduct ->
          existingProduct.getName().equals(name)).findFirst().orElseThrow(InvalidMenuOrderException::new);
      orderedProducts.add(orderedProduct);
    }
    return orderedProducts;
  }

}
