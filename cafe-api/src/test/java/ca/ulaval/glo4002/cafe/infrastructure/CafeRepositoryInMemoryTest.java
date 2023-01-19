package ca.ulaval.glo4002.cafe.infrastructure;

import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategy;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategyDefault;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;
import ca.ulaval.glo4002.cafe.domain.product.Menu;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CafeRepositoryInMemoryTest {
  private static final String DEFAULT_CAFE_NAME = "Les 4 fees";
  private static final int DEFAULT_CUBE_SIZE = 4;
  private static final int ANOTHER_CUBE_SIZE = 12;
  private static final List<Product> PRODUCT = new ArrayList<>();
  private static final Menu MENU = new Menu(PRODUCT);
  private final Inventory inventory = new Inventory(new ArrayList<>());
  private static final double TIP_RATE = 0;
  private static final ReservationStrategy DEFAULT_RESERVATION_STRATEGY = new ReservationStrategyDefault();
  private final CafeRepositoryInMemory cafeRepositoryInMemory = new CafeRepositoryInMemory();

  @Test
  void givenACafe_whenSavingCafe_thenCafeIsSaved() {
    Cafe expectedCafe = new Cafe(DEFAULT_CAFE_NAME, DEFAULT_CUBE_SIZE, DEFAULT_RESERVATION_STRATEGY, MENU, inventory, TIP_RATE);

    this.cafeRepositoryInMemory.save(expectedCafe);

    Cafe actualCafe = cafeRepositoryInMemory.findByName(DEFAULT_CAFE_NAME);
    assertEquals(expectedCafe, actualCafe);
  }

  @Test
  void givenACafeInMemory_whenASavingNewCafeWithSameName_ThenCafeIsOverride() {
    Cafe cafeInMemory = new Cafe(DEFAULT_CAFE_NAME, DEFAULT_CUBE_SIZE, DEFAULT_RESERVATION_STRATEGY, MENU, inventory,TIP_RATE);
    this.cafeRepositoryInMemory.save(cafeInMemory);
    Cafe newCafe = new Cafe(DEFAULT_CAFE_NAME, ANOTHER_CUBE_SIZE, DEFAULT_RESERVATION_STRATEGY, MENU, inventory, TIP_RATE);

    this.cafeRepositoryInMemory.save(newCafe);

    Cafe actualCafe = cafeRepositoryInMemory.findByName(DEFAULT_CAFE_NAME);
    assertEquals(newCafe, actualCafe);
  }

  @Test
  void givenACafeInMemory_whenFindingCafe_ThenCafeIsReturned() {
    Cafe expectedCafe = new Cafe(DEFAULT_CAFE_NAME, DEFAULT_CUBE_SIZE, DEFAULT_RESERVATION_STRATEGY, MENU, inventory, TIP_RATE);
    this.cafeRepositoryInMemory.save(expectedCafe);

    Cafe actualCafe = cafeRepositoryInMemory.findByName(DEFAULT_CAFE_NAME);

    assertEquals(expectedCafe, actualCafe);
  }
}
