package ca.ulaval.glo4002.cafe.domain.ingredient;

import ca.ulaval.glo4002.cafe.domain.product.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
  private static final int STARTING_QUANTITY = 0;
  private static final String INGREDIENT_NAME = "Espresso";
  private static final String ANOTHER_INGREDIENT_NAME = "Milk";
  private static final int INGREDIENT_QUANTITY = 150;
  private static final int ANOTHER_INGREDIENT_QUANTITY = 100;
  private static final int INGREDIENT_USAGE_QUANTITY = 10;
  private static final String INVALID_INGREDIENT_NAME = "Kevin's Famous Chile";
  private static final String PRODUCT_NAME = "Americano";
  private static final double PRODUCT_PRICE = 2.5;
  private Inventory inventory;

  @Test
  void givenListOfIngredientWithEmptyInventory_whenAddingIngredientsToInventory_thenIngredientsAreAdded() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, STARTING_QUANTITY));
    ingredients.add(new Ingredient(ANOTHER_INGREDIENT_NAME, STARTING_QUANTITY));
    inventory = new Inventory(ingredients);
    Ingredient ingredient = new Ingredient(INGREDIENT_NAME, INGREDIENT_QUANTITY);
    Ingredient anotherIngredient = new Ingredient(ANOTHER_INGREDIENT_NAME, ANOTHER_INGREDIENT_QUANTITY);
    List<Ingredient> ingredientsToAdd = new ArrayList<>();
    ingredientsToAdd.add(ingredient);
    ingredientsToAdd.add(anotherIngredient);

    inventory.addIngredients(ingredientsToAdd);

    assertEquals(INGREDIENT_QUANTITY, inventory.getIngredientByName(INGREDIENT_NAME).get().getQuantity());
    assertEquals(ANOTHER_INGREDIENT_QUANTITY, inventory.getIngredientByName(ANOTHER_INGREDIENT_NAME).get().getQuantity());
  }

  @Test
  void whenAddIngredientsAddsIngredientThatIsNotInInventory_thenThrowInvalidIngredientException() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, STARTING_QUANTITY));
    inventory = new Inventory(ingredients);
    Ingredient ingredient = new Ingredient(INVALID_INGREDIENT_NAME, INGREDIENT_QUANTITY);
    List<Ingredient> ingredientsToUse = new ArrayList<>();
    ingredientsToUse.add(ingredient);

    assertThrows(InvalidIngredientException.class, () -> inventory.addIngredients(ingredientsToUse));
  }

  @Test
  void givenListOfIngredientsWithNonEmptyInventory_whenUsingIngredientsFromInventory_thenIngredientsAreUsed() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, INGREDIENT_QUANTITY));
    ingredients.add(new Ingredient(ANOTHER_INGREDIENT_NAME, ANOTHER_INGREDIENT_QUANTITY));
    inventory = new Inventory(ingredients);
    Ingredient ingredient = new Ingredient(INGREDIENT_NAME, INGREDIENT_USAGE_QUANTITY);
    Ingredient anotherIngredient = new Ingredient(ANOTHER_INGREDIENT_NAME, INGREDIENT_USAGE_QUANTITY);
    List<Ingredient> ingredientsToUse = new ArrayList<>();
    ingredientsToUse.add(ingredient);
    ingredientsToUse.add(anotherIngredient);

    inventory.useIngredients(ingredientsToUse);

    assertEquals(INGREDIENT_QUANTITY - INGREDIENT_USAGE_QUANTITY, inventory.getIngredientByName(INGREDIENT_NAME).get().getQuantity());
    assertEquals(ANOTHER_INGREDIENT_QUANTITY - INGREDIENT_USAGE_QUANTITY, inventory.getIngredientByName(ANOTHER_INGREDIENT_NAME).get().getQuantity());
  }

  @Test
  void whenUseIngredientsUsesIngredientThatIsNotInInventory_thenThrowInvalidIngredientException() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, STARTING_QUANTITY));
    inventory = new Inventory(ingredients);
    Ingredient ingredient = new Ingredient(INVALID_INGREDIENT_NAME, INGREDIENT_QUANTITY);
    List<Ingredient> ingredientsToUse = new ArrayList<>();
    ingredientsToUse.add(ingredient);

    assertThrows(InvalidIngredientException.class, () -> inventory.useIngredients(ingredientsToUse));
  }

  @Test
  void givenProductsWithIngredients_whenUsingProductsIngredients_thenProductIngredientsAreUsed() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, INGREDIENT_QUANTITY));
    inventory = new Inventory(ingredients);
    List<Ingredient> receipeIngredients = new ArrayList<>();
    receipeIngredients.add(new Ingredient(INGREDIENT_NAME, INGREDIENT_USAGE_QUANTITY));
    List<Product> products = new ArrayList<>();
    Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE, receipeIngredients);
    products.add(product);

    inventory.useProductsIngredients(products);

    assertEquals(INGREDIENT_QUANTITY - INGREDIENT_USAGE_QUANTITY, inventory.getIngredientByName(INGREDIENT_NAME).get().getQuantity());
  }

  @Test
  void givenProductsWithIngredients_whenCheckingHasEnoughIngredients_thenReturnTrueIfHasEnoughIngredients() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, INGREDIENT_QUANTITY));
    inventory = new Inventory(ingredients);
    List<Ingredient> receipeIngredients = new ArrayList<>();
    receipeIngredients.add(new Ingredient(INGREDIENT_NAME, INGREDIENT_USAGE_QUANTITY));
    List<Product> products = new ArrayList<>();
    Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE, receipeIngredients);
    products.add(product);

    boolean hasEnoughIngredients = inventory.hasEnoughIngredients(products);

    assertTrue(hasEnoughIngredients);
  }

  @Test
  void givenProductsWithIngredients_whenCheckingHasEnoughIngredients_thenReturnFalseIfDoesNotHaveEnoughIngredients() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, STARTING_QUANTITY));
    inventory = new Inventory(ingredients);
    List<Ingredient> receipeIngredients = new ArrayList<>();
    receipeIngredients.add(new Ingredient(INGREDIENT_NAME, INGREDIENT_USAGE_QUANTITY));
    List<Product> products = new ArrayList<>();
    Product product = new Product(PRODUCT_NAME, PRODUCT_PRICE, receipeIngredients);
    products.add(product);

    boolean hasEnoughIngredients = inventory.hasEnoughIngredients(products);

    assertFalse(hasEnoughIngredients);
  }

  @Test
  void givenInventoryWithIngredients_whenEmptyInventory_thenInventoryIsEmpty() {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient(INGREDIENT_NAME, INGREDIENT_QUANTITY));
    inventory = new Inventory(ingredients);

    inventory.emptyInventory();

    assertEquals(0, inventory.getIngredientByName(INGREDIENT_NAME).get().getQuantity());
  }
}
