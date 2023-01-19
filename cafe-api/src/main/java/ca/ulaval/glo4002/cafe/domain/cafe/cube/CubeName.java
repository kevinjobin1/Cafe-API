package ca.ulaval.glo4002.cafe.domain.cafe.cube;

public enum CubeName {
  BLOOM("Bloom"),
  MERRYWEATHER("Merryweather"),
  TINKER_BELL("Tinker Bell"),
  WANDA("Wanda");

  private final String name;

  CubeName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
