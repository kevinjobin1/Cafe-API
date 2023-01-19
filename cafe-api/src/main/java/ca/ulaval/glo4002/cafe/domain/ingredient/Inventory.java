package ca.ulaval.glo4002.cafe.domain.ingredient;

import ca.ulaval.glo4002.cafe.domain.product.Product;

import java.util.List;
import java.util.Optional;

public class Inventory {

  private final List<Ingredient> ingredients;

  public Inventory(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public void addIngredients(List<Ingredient> ingredientsToAdd) {
    for (Ingredient ingredientToAdd : ingredientsToAdd) {
      Ingredient ingredientInInventory = this.getIngredientByName(ingredientToAdd.getName()).orElseThrow(InvalidIngredientException::new);
      ingredientInInventory.addQuantity(ingredientToAdd.getQuantity());
    }
  }

  public void useIngredients(List<Ingredient> ingredientsToUse) {
    for (Ingredient ingredientToUse : ingredientsToUse) {
      Ingredient ingredientInInventory = this.getIngredientByName(ingredientToUse.getName()).orElseThrow(InvalidIngredientException::new);
      ingredientInInventory.reduceQuantity(ingredientToUse.getQuantity());
    }
  }

  public void useProductsIngredients(List<Product> products) {
    for (Product product : products) {
      this.useIngredients(product.getIngredients());
    }
  }

  public boolean hasEnoughIngredients(List<Product> products) {
    for (Product product : products) {
      for (Ingredient ingredientToUse : product.getIngredients()) {
        Ingredient ingredientInInventory = this.getIngredientByName(ingredientToUse.getName()).orElseThrow(InvalidIngredientException::new);
        if (ingredientToUse.getQuantity() > ingredientInInventory.getQuantity()) {
          return false;
        }
      }
    }
    return true;
  }

  public Optional<Ingredient> getIngredientByName(String ingredientName) {
    return this.ingredients.stream().filter(ingredient -> ingredient.getName().equals(ingredientName)).findFirst();
  }

  public List<Ingredient> getIngredients() {
    return this.ingredients;
  }

  public void emptyInventory() {
    for (Ingredient ingredient : ingredients) {
      ingredient.emptyIngredient();
    }
  }

}
