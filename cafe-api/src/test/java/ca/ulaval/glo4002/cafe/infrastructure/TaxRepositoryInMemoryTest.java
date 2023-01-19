package ca.ulaval.glo4002.cafe.infrastructure;

import ca.ulaval.glo4002.cafe.domain.tax.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaxRepositoryInMemoryTest {
  private static final String TAX_ACRONYM = "AB";
  private static final String ANOTHER_TAX_ACRONYM = "NT";
  private static final int TAX_RATE = 0;
  private static final Tax A_TAX = new Tax(TAX_RATE, TAX_ACRONYM);
  private static final Tax ANOTHER_TAX = new Tax(TAX_RATE, ANOTHER_TAX_ACRONYM);
  private TaxRepositoryInMemory taxRepository;

  @BeforeEach
  void setUp() {
    this.taxRepository = new TaxRepositoryInMemory();
  }

  @Test
  void givenAnEmptyRepo_whenFindingByTaxAcronym_thenReturnsNone() {
    assertTrue(this.taxRepository.findTaxByAcronym(TAX_ACRONYM).isEmpty());
  }

  @Test
  void givenARepoWithOneTax_whenFindingByTaxAcronym_thenReturnsOneTax() {
    this.taxRepository.save(A_TAX);

    Optional<Tax> actualTaxes = this.taxRepository.findTaxByAcronym(TAX_ACRONYM);

    assertEquals(TAX_ACRONYM, actualTaxes.get().getAcronym());
  }

  @Test
  void givenAnEmptyRepoAndATax_whenSavingTax_thenTheSameTaxIsSaved() {
    this.taxRepository.save(A_TAX);

    assertTrue(this.taxRepository.findTaxByAcronym(TAX_ACRONYM).isPresent());
  }

  @Test
  void givenAnEmptyRepoAndTwoTaxes_whenSavingTwoTaxes_thenSameTwoTaxesAreSaved() {
    this.taxRepository.save(A_TAX);
    this.taxRepository.save(ANOTHER_TAX);

    assertTrue(this.taxRepository.findTaxByAcronym(TAX_ACRONYM).isPresent());
    assertTrue(this.taxRepository.findTaxByAcronym(ANOTHER_TAX_ACRONYM).isPresent());
  }
}
