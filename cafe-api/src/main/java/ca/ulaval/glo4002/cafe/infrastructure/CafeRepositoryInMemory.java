package ca.ulaval.glo4002.cafe.infrastructure;

import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CafeRepositoryInMemory implements CafeRepository {
  private List<Cafe> cafeList = new ArrayList<>();

  @Override
  public void save(Cafe cafe) {
    Optional<Cafe> previousCafe = this.cafeList.stream().filter(existingCafe ->
        existingCafe.getOrganizationName().equals(cafe.getOrganizationName())).findFirst();
    if (previousCafe.isPresent()) {
      this.cafeList.remove(previousCafe.get());
    }
    this.cafeList.add(cafe);
  }

  @Override
  public Cafe findByName(String name) {
    return this.cafeList.stream().filter(cafe -> name.equals(cafe.getOrganizationName())).findFirst().orElseThrow();
  }

}
