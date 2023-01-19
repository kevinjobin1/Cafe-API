package ca.ulaval.glo4002.cafe.domain.cafe;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.InventoryFactory;
import ca.ulaval.glo4002.cafe.domain.product.Product;

class CafeFactoryTest {

  private static final String ORGANIZATION_NAME = "Les 4-Fées";
  private static final int CUBE_SIZE = 4;
  private static final GroupReservationMethod DEFAULT_RESERVATION_METHOD = GroupReservationMethod.DEFAULT;

  private CafeFactory cafeFactory;
  private CafeConfiguration cafeConfiguration;
  private List<Product> products;
  private List<Ingredient> ingredients;

  @BeforeEach
  public void setUp() {
    cafeFactory = new CafeFactory(new ReservationStrategyFactory(), new InventoryFactory());
    this.products = new ArrayList<>();
    this.ingredients = new ArrayList<>();
  }

  @Test
  void givenAnyConfiguration_whenCreatingCafe_thenCafeIsCreated() {
    this.cafeConfiguration = new CafeConfiguration(ORGANIZATION_NAME, CUBE_SIZE, DEFAULT_RESERVATION_METHOD, 0);

    Cafe cafe = cafeFactory.createCafe(cafeConfiguration, products, ingredients);

    assertEquals(ORGANIZATION_NAME, cafe.getOrganizationName());
    assertEquals(CUBE_SIZE, cafe.getCubeSize());
  }
}
