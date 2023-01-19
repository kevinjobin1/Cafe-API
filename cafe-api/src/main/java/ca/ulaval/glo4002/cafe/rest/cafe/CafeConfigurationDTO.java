package ca.ulaval.glo4002.cafe.rest.cafe;

public class CafeConfigurationDTO {

  private final String organizationName;
  private final int cubeSize;
  private final String groupReservationMethod;
  private final double tip;

  public CafeConfigurationDTO(String organizationName, int cubeSize, String groupReservationMethod, double tip) {
    this.organizationName = organizationName;
    this.cubeSize = cubeSize;
    this.groupReservationMethod = groupReservationMethod;
    this.tip = tip;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public int getCubeSize() {
    return cubeSize;
  }

  public String getGroupReservationMethod() {
    return groupReservationMethod;
  }

  public double getTip() {
    return tip;
  }

}
