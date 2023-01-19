package ca.ulaval.glo4002.cafe.domain.tax;

public class CanadaTaxStrategy implements TaxStrategy {

  private final double rate;
  private final TaxStrategy provinceTax;

  public CanadaTaxStrategy(double rate, TaxStrategy provinceTax) {
    this.rate = rate;
    this.provinceTax = provinceTax;
  }

  @Override
  public double getTotalTax(double price) {
    return price * rate + provinceTax.getTotalTax(price);
  }
}
