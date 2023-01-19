package ca.ulaval.glo4002.cafe.domain.cafe;

public enum GroupReservationMethod {
  DEFAULT("Default"),
  FULL_CUBES("Full Cubes"),
  NO_LONERS("No Loners");

  private final String methodName;

  GroupReservationMethod(String methodName) {
    this.methodName = methodName;
  }

  @Override
  public String toString() {
    return this.methodName;
  }
}
