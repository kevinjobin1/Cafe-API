package ca.ulaval.glo4002.cafe.domain.tax;

import java.util.Optional;

public class TaxStrategyFactory {

  public TaxStrategy createDefaultTax() {
    return new RegularTaxStrategy(0);
  }

  public TaxStrategy createTax(Optional<Tax> countryTaxOptional, Optional<Tax> provinceTaxOptional, Optional<Tax> stateTaxOptional) {
    Tax countryTax = countryTaxOptional.orElseThrow(InvalidCountryException::new);
    if (countryTax.getAcronym().equals(CountryTaxSpecialCase.CANADA.toString())) {
      RegularTaxStrategy provinceTax = new RegularTaxStrategy(provinceTaxOptional.orElseThrow(InvalidCountryException::new).getRate());
      return new CanadaTaxStrategy(countryTax.getRate(), provinceTax);
    }
    else if (countryTax.getAcronym().equals(CountryTaxSpecialCase.UNITED_STATES.toString())) {
      RegularTaxStrategy stateTax = new RegularTaxStrategy(stateTaxOptional.orElseThrow(InvalidCountryException::new).getRate());
      return new UsTaxStrategy(countryTax.getRate(), stateTax);
    }
    else {
      return new RegularTaxStrategy(countryTax.getRate());
    }
  }

}
