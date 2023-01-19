package ca.ulaval.glo4002.cafe.rest.inventory;

import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryAssembler {

  public InventoryResponse toResponse(Inventory inventory) {
    InventoryResponse inventoryResponse = new InventoryResponse();
    for (Ingredient ingredient : inventory.getIngredients()) {
      inventoryResponse.add(ingredient.getName(), ingredient.getQuantity());
    }
    return inventoryResponse;
  }

  public List<Ingredient> fromRequest(InventoryRequest inventoryRequest) {
    List<Ingredient> ingredients = new ArrayList<>();
    for (Map.Entry<String, Integer> ingredient : inventoryRequest.getIngredientList().entrySet()) {
      ingredients.add(new Ingredient(ingredient.getKey(), ingredient.getValue()));
    }
    return ingredients;
  }

}
