package ca.ulaval.glo4002.cafe.infrastructure;

import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.IngredientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IngredientRepositoryInMemory implements IngredientRepository {

  private final ArrayList<Ingredient> ingredients = new ArrayList<>();

  @Override
  public Optional<Ingredient> findByName(String ingredientName) {
    return ingredients.stream().filter(ingredient -> ingredient.getName().equals(ingredientName)).findFirst();
  }

  @Override
  public List<Ingredient> findAll() {
    return ingredients;
  }

  @Override
  public void save(Ingredient ingredient) {
    this.ingredients.add(ingredient);
  }

}
