package ca.ulaval.glo4002.cafe.domain.tax;

public class RegularTaxStrategy implements TaxStrategy {

  private final double rate;

  public RegularTaxStrategy(double rate) {
    this.rate = rate;
  }

  @Override
  public double getTotalTax(double price) {
    return price * rate;
  }

}
