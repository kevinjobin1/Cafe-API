package ca.ulaval.glo4002.cafe.infrastructure;

import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IngredientRepositoryInMemoryTest {
  private static final String INGREDIENT_NAME = "Espresso";
  private static final String ANOTHER_INGREDIENT_NAME = "Milk";
  private static final int INGREDIENT_QUANTITY = 100;
  private static final Ingredient A_FIRST_INGREDIENT = new Ingredient(INGREDIENT_NAME, INGREDIENT_QUANTITY);
  private static final Ingredient A_SECOND_INGREDIENT = new Ingredient(ANOTHER_INGREDIENT_NAME, INGREDIENT_QUANTITY);
  private IngredientRepositoryInMemory ingredientRepository;

  @BeforeEach
  void setUp() {
    this.ingredientRepository = new IngredientRepositoryInMemory();
  }

  @Test
  void givenAnEmptyRepo_whenFindingAll_thenReturnsNone() {
    assertIterableEquals(List.of(), this.ingredientRepository.findAll());
  }

  @Test
  void givenARepoWithOneIngredient_whenFindingAll_thenReturnsOneIngredient() {
    List<Ingredient> expectedIngredients = List.of(A_FIRST_INGREDIENT);
    expectedIngredients.forEach(this.ingredientRepository::save);

    List<Ingredient> actualIngredients = this.ingredientRepository.findAll();

    assertIterableEquals(expectedIngredients, actualIngredients);
  }

  @Test
  void givenARepoWithTwoIngredients_whenFindingAll_thenReturnsTwoIngredients() {
    List<Ingredient> expectedIngredients = List.of(A_FIRST_INGREDIENT, A_SECOND_INGREDIENT);
    expectedIngredients.forEach(this.ingredientRepository::save);

    List<Ingredient> actualIngredients = this.ingredientRepository.findAll();

    assertIterableEquals(expectedIngredients, actualIngredients);
  }

  @Test
  void givenAnEmptyRepoAndAnIngredient_whenSavingOneIngredient_thenTheSameIngredientIsSaved() {
    this.ingredientRepository.save(A_FIRST_INGREDIENT);

    assertTrue(this.ingredientRepository.findAll().contains(A_FIRST_INGREDIENT));
    assertEquals(1, this.ingredientRepository.findAll().size());
  }

  @Test
  void givenAnEmptyRepoAndTwoIngredients_whenSavingTwoIngredients_thenSameTwoIngredientsAreSaved() {
    this.ingredientRepository.save(A_FIRST_INGREDIENT);
    this.ingredientRepository.save(A_SECOND_INGREDIENT);

    assertTrue(this.ingredientRepository.findAll().contains(A_FIRST_INGREDIENT));
    assertTrue(this.ingredientRepository.findAll().contains(A_SECOND_INGREDIENT));
    assertEquals(2, this.ingredientRepository.findAll().size());
  }
}
