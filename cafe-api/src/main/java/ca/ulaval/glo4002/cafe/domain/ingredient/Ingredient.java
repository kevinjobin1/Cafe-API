package ca.ulaval.glo4002.cafe.domain.ingredient;

public class Ingredient {

  private final String name;
  private int quantity;

  public Ingredient(String name , int quantity) {
    this.name = name;
    this.quantity = quantity;
  }

  public String getName() {
    return this.name;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void addQuantity(double quantity) {
    this.quantity += quantity;
  }

  public void reduceQuantity(double quantity) {
    this.quantity -= quantity;
  }

  public void emptyIngredient() {
    this.quantity = 0;
  }
}
