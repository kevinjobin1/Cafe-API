package ca.ulaval.glo4002.cafe.infrastructure;

import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.product.ProductRepository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryInMemoryTest {
  private static final String A_PRODUCT_NAME = "A product name";
  private static final String ANOTHER_PRODUCT_NAME = "Another product name";
  private static final String A_FIRST_PRODUCT_NAME = "A first product name";
  private static final String A_SECOND_PRODUCT_NAME = "A second product name";
  private static final double A_PRODUCT_PRICE = 1.50;
  private static final double ANOTHER_PRODUCT_PRICE = 2.75;
  private static final double A_FIRST_PRODUCT_PRICE = 5.25;
  private static final double A_SECOND_PRODUCT_PRICE = 2.25;
  private static final Product A_PRODUCT = new Product(A_PRODUCT_NAME, A_PRODUCT_PRICE, List.of());
  private static final Product ANOTHER_PRODUCT = new Product(ANOTHER_PRODUCT_NAME, ANOTHER_PRODUCT_PRICE, List.of());
  private static final Product A_FIRST_PRODUCT = new Product(A_FIRST_PRODUCT_NAME, A_FIRST_PRODUCT_PRICE, List.of());
  private static final Product A_SECOND_PRODUCT = new Product(A_SECOND_PRODUCT_NAME, A_SECOND_PRODUCT_PRICE, List.of());
  ProductRepository productRepository;

  @BeforeEach
  void setUp() {
    this.productRepository = new ProductRepositoryInMemory();
  }

  @Test
  void givenAnEmptyRepo_whenFindingAll_thenReturnsNone() {
    assertIterableEquals(List.of(), this.productRepository.findAll());
  }

  @Test
  void givenARepoWithOneProduct_whenFindingAll_thenReturnsOneProduct() {
    List<Product> expectedProducts = List.of(A_FIRST_PRODUCT);
    expectedProducts.forEach(this.productRepository::save);

    List<Product> actualProducts = this.productRepository.findAll();

    assertIterableEquals(expectedProducts, actualProducts);
  }
  @Test
  void givenARepoWithTwoProducts_whenFindingAll_thenReturnsTwoProducts() {
    List<Product> expectedProducts = List.of(A_FIRST_PRODUCT, A_SECOND_PRODUCT);
    expectedProducts.forEach(this.productRepository::save);

    List<Product> actualProducts = this.productRepository.findAll();

    assertIterableEquals(expectedProducts, actualProducts);
  }

  @Test
  void givenAnEmptyRepoAndAProduct_whenSavingOneProduct_thenTheSameProductIsSaved() {
    this.productRepository.save(A_PRODUCT);

    assertTrue(this.productRepository.findAll().contains(A_PRODUCT));
    assertEquals(1, this.productRepository.findAll().size());
  }

  @Test
  void givenAnEmptyRepoAndTwoProducts_whenSavingTwoProducts_thenSameTwoProductsAreSaved() {
    this.productRepository.save(A_PRODUCT);
    this.productRepository.save(ANOTHER_PRODUCT);

    assertTrue(this.productRepository.findAll().contains(A_PRODUCT));
    assertTrue(this.productRepository.findAll().contains(ANOTHER_PRODUCT));
    assertEquals(2, this.productRepository.findAll().size());
  }
}
