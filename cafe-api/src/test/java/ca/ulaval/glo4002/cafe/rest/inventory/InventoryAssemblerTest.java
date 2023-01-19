package ca.ulaval.glo4002.cafe.rest.inventory;

import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryAssemblerTest {

  @BeforeEach
  void setUp() {
    this.inventoryAssembler = new InventoryAssembler();
  }

  @Test
  void givenInventoryIsNotEmpty_whenAssemblingResponse_thenInventoryResponseIsCreated() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(A_INGREDIENT);
    Inventory inventory = new Inventory(ingredients);

    InventoryResponse inventoryResponse = inventoryAssembler.toResponse(inventory);

    assertEquals(1, inventoryResponse.getIngredients().size());
  }

  @Test
  void givenInventoryRequestWithOneIngredient_whenReceivingFromRequest_thenIngredientsAreCreated() {
    InventoryRequest inventoryRequest = new InventoryRequest();
    inventoryRequest.addIngredient(INGREDIENT_NAME, STARTING_QUANTITY);

    List<Ingredient> ingredients = inventoryAssembler.fromRequest(inventoryRequest);

    assertEquals(1, ingredients.size());
  }

  @Test
  void givenInventoryRequestWithMultipleIngredients_whenReceivingFromRequest_thenIngredientsAreCreated() {
    InventoryRequest inventoryRequest = new InventoryRequest();
    inventoryRequest.addIngredient(INGREDIENT_NAME, STARTING_QUANTITY);
    inventoryRequest.addIngredient(ANOTHER_INGREDIENT_NAME, STARTING_QUANTITY);

    List<Ingredient> ingredients = inventoryAssembler.fromRequest(inventoryRequest);

    assertEquals(2, ingredients.size());
  }

  private static final String INGREDIENT_NAME = "Espresso";
  private static final String ANOTHER_INGREDIENT_NAME = "Milk";
  private static final int STARTING_QUANTITY = 0;
  private static final Ingredient A_INGREDIENT = new Ingredient(INGREDIENT_NAME, STARTING_QUANTITY);
  private InventoryAssembler inventoryAssembler;
}
