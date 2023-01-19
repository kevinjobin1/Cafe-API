package ca.ulaval.glo4002.cafe.application.tax;

import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategy;
import ca.ulaval.glo4002.cafe.domain.tax.Tax;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategyFactory;
import ca.ulaval.glo4002.cafe.domain.tax.TaxRepository;
import ca.ulaval.glo4002.cafe.domain.tax.LocationDetails;

import java.util.Optional;

public class TaxService {

  private TaxStrategy tax;
  private final TaxStrategyFactory taxFactory;
  private final TaxRepository countryTaxRepository;
  private final TaxRepository provinceTaxRepository;
  private final TaxRepository stateTaxRepository;

  public TaxService(TaxStrategyFactory taxFactory, TaxRepository countryTaxRepository, TaxRepository provinceTaxRepository, TaxRepository stateTaxRepository) {
    this.taxFactory = taxFactory;
    this.countryTaxRepository = countryTaxRepository;
    this.provinceTaxRepository = provinceTaxRepository;
    this.stateTaxRepository = stateTaxRepository;
    this.tax = this.taxFactory.createDefaultTax();
  }

  public void setLocation(LocationDetails locationDetails) {
    Optional<Tax> countryTax = this.countryTaxRepository.findTaxByAcronym(locationDetails.getCountry());
    Optional<Tax> provinceTax = this.provinceTaxRepository.findTaxByAcronym(locationDetails.getProvince());
    Optional<Tax> stateTax = this.stateTaxRepository.findTaxByAcronym(locationDetails.getState());
    this.tax = this.taxFactory.createTax(countryTax, provinceTax, stateTax);
  }

  public TaxStrategy getTax() {
    return this.tax;
  }

}
