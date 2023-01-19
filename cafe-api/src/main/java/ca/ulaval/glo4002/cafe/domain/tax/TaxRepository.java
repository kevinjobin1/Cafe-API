package ca.ulaval.glo4002.cafe.domain.tax;

import java.util.Optional;

public interface TaxRepository {

  Optional<Tax> findTaxByAcronym(String acronym);

  void save(Tax repositoryTax);

}
