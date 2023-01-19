package ca.ulaval.glo4002.cafe.domain.tax;

public class Tax {

  private final double rate;
  private final String acronym;

  public Tax(double rate, String acronym) {
    this.rate = rate;
    this.acronym = acronym;
  }

  public double getRate() {
    return rate;
  }

  public String getAcronym() {
    return acronym;
  }

}
