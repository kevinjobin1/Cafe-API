package ca.ulaval.glo4002.cafe.rest.inventory;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.HashMap;
import java.util.Map;

public class InventoryResponse {

  private Map<String, Integer> ingredients;

  public InventoryResponse() {
    this.ingredients = new HashMap<>();
  }

  @JsonAnyGetter
  public Map<String, Integer> getIngredients() {
    return ingredients;
  }

  public void add(String ingredientName, int quantity) {
    this.ingredients.put(ingredientName, quantity);
  }

}
