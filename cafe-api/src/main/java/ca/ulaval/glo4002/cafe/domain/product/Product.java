package ca.ulaval.glo4002.cafe.domain.product;

import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;

import java.util.List;

public class Product {
  private final String name;
  private final double price;
  private final List<Ingredient> ingredients;

  public Product(String name, double price, List<Ingredient> ingredients) {
    this.name = name;
    this.price = price;
    this.ingredients = ingredients;
  }

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }

  public List<Ingredient> getIngredients() {
    return this.ingredients;
  }

}
