package ca.ulaval.glo4002.cafe.domain.product;

import java.util.List;
public interface ProductRepository {
  List<Product> findAll();
  void save(Product product);
}
