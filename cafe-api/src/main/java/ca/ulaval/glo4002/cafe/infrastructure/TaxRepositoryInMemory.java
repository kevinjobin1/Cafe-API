package ca.ulaval.glo4002.cafe.infrastructure;

import ca.ulaval.glo4002.cafe.domain.tax.Tax;
import ca.ulaval.glo4002.cafe.domain.tax.TaxRepository;

import java.util.ArrayList;
import java.util.Optional;

public class TaxRepositoryInMemory implements TaxRepository {

  private final ArrayList<Tax> taxes = new ArrayList<>();

  public Optional<Tax> findTaxByAcronym(String acronym) {
    return taxes.stream().filter(tax -> tax.getAcronym().equals(acronym)).findFirst();
  }

  public void save(Tax repositoryTax) {
    this.taxes.add(repositoryTax);
  }

}
