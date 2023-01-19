package ca.ulaval.glo4002.cafe.rest.inventory;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public class InventoryRequest {

  private Map<String, Integer> ingredientList = new HashMap<>();

  @JsonAnySetter
  public void addIngredient(String property, int value) {
    this.ingredientList.put(property, value);
  }

  public Map<String, Integer> getIngredientList() {
    return ingredientList;
  }

}
