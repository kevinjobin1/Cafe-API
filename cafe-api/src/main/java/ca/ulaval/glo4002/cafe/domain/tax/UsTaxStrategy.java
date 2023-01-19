package ca.ulaval.glo4002.cafe.domain.tax;

public class UsTaxStrategy implements TaxStrategy {

  private final double rate;
  private final TaxStrategy stateTax;

  public UsTaxStrategy(double rate, TaxStrategy stateTax) {
    this.rate = rate;
    this.stateTax = stateTax;
  }

  @Override
  public double getTotalTax(double price) {
    return price * rate + stateTax.getTotalTax(price);
  }

}
