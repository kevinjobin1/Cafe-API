package ca.ulaval.glo4002.cafe.domain.tax;

public enum CountryTaxSpecialCase {

  CANADA("CA"),
  UNITED_STATES("US");

  private final String acronym;

  CountryTaxSpecialCase(String acronym) {
    this.acronym = acronym;
  }

  @Override
  public String toString() {
    return this.acronym;
  }

}
