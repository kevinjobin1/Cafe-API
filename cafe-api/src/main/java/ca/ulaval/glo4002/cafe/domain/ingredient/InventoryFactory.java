package ca.ulaval.glo4002.cafe.domain.ingredient;

import java.util.List;

public class InventoryFactory {

  public Inventory createInventory(List<Ingredient> ingredients) {
    return new Inventory(ingredients);
  }

}
