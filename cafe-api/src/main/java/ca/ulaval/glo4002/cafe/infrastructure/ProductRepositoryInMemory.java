package ca.ulaval.glo4002.cafe.infrastructure;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.product.ProductRepository;

public class ProductRepositoryInMemory implements ProductRepository {
  private final List<Product> products = new ArrayList<>();

  @Override
  public List<Product> findAll() {
    return this.products;
  }

  @Override
  public void save(Product product) {
    this.products.add(product);
  }

}
