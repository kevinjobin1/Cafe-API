package ca.ulaval.glo4002.cafe.domain.cafe;

import java.util.List;

import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategy;
import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;
import ca.ulaval.glo4002.cafe.domain.ingredient.InventoryFactory;
import ca.ulaval.glo4002.cafe.domain.product.Menu;
import ca.ulaval.glo4002.cafe.domain.product.Product;

public class CafeFactory {
  private final ReservationStrategyFactory reservationStrategyFactory;
  private final InventoryFactory inventoryFactory;

  public CafeFactory(ReservationStrategyFactory reservationStrategyFactory, InventoryFactory inventoryFactory) {
    this.reservationStrategyFactory = reservationStrategyFactory;
    this.inventoryFactory = inventoryFactory;
  }

  public Cafe createCafe(CafeConfiguration cafeConfiguration, List<Product> products, List<Ingredient> ingredients) {
    ReservationStrategy reservationStrategy = reservationStrategyFactory.createReservationStrategy(cafeConfiguration.getGroupReservationMethod());
    Inventory inventory = this.inventoryFactory.createInventory(ingredients);
    return new Cafe(cafeConfiguration.getOrganizationName(), cafeConfiguration.getCubeSize(), reservationStrategy, new Menu(products),
      inventory, cafeConfiguration.getTip());
  }
}
