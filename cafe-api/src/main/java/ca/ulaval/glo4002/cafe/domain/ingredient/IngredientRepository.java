package ca.ulaval.glo4002.cafe.domain.ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository {

  Optional<Ingredient> findByName(String ingredientName);

  List<Ingredient> findAll();

  void save(Ingredient ingredient);

}
