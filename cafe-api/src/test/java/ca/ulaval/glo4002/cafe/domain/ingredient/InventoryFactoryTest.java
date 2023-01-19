package ca.ulaval.glo4002.cafe.domain.ingredient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryFactoryTest {
  private static final String INGREDIENT_NAME = "Espresso";
  private static final int STARTING_QUANTITY = 0;
  private InventoryFactory inventoryFactory;

  @BeforeEach
  void setUp() {
    inventoryFactory = new InventoryFactory();
  }

  @Test
  void whenCreatingInventory_thenInventoryIsCreatedWithCorrectIngredients() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, STARTING_QUANTITY));
    Inventory inventory = inventoryFactory.createInventory(ingredients);


    assertEquals(INGREDIENT_NAME, inventory.getIngredients().get(0).getName());
    assertEquals(1, inventory.getIngredients().size());
  }
}
