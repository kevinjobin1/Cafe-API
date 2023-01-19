package ca.ulaval.glo4002.cafe.domain.ingredient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

  @BeforeEach
  void setUp() {
    ingredient = new Ingredient(INGREDIENT_NAME, INGREDIENT_QUANTITY);
  }

  @Test
  void whenGetName_thenReturnIngredientName() {
    String ingredientName = ingredient.getName();

    assertEquals(INGREDIENT_NAME, ingredientName);
  }

  @Test
  void whenGetQuantity_thenReturnIngredientQuantity() {
    int ingredientQuantity = ingredient.getQuantity();

    assertEquals(INGREDIENT_QUANTITY, ingredientQuantity);
  }

  @Test
  void whenAddQuantity_thenIngredientQuantityIsAdded() {
    ingredient.addQuantity(ADD_OR_REMOVE_QUANTITY);

    int expectedQuantity = INGREDIENT_QUANTITY + ADD_OR_REMOVE_QUANTITY;
    int ingredientQuantity = ingredient.getQuantity();
    assertEquals(expectedQuantity, ingredientQuantity);
  }

  @Test
  void whenReduceQuantity_thenIngredientQuantityIsReduced() {
    ingredient.reduceQuantity(ADD_OR_REMOVE_QUANTITY);

    int expectedQuantity = INGREDIENT_QUANTITY - ADD_OR_REMOVE_QUANTITY;
    int ingredientQuantity = ingredient.getQuantity();
    assertEquals(expectedQuantity, ingredientQuantity);
  }

  @Test
  void whenEmptyIngredient_thenIngredientQuantityEmpty() {
    ingredient.emptyIngredient();

    int ingredientQuantity = ingredient.getQuantity();
    assertEquals(0, ingredientQuantity);
  }

  private static final String INGREDIENT_NAME = "ingredient";
  private static final int INGREDIENT_QUANTITY = 10;
  private static final int ADD_OR_REMOVE_QUANTITY = 5;
  private Ingredient ingredient;
}
