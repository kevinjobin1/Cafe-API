package ca.ulaval.glo4002.cafe.domain.cafe;

public class CafeConfiguration {
  private final String organizationName;
  private final int cubeSize;
  private final GroupReservationMethod groupReservationMethod;
  private final double tip;

  public CafeConfiguration(String organizationName, int cubeSize, GroupReservationMethod groupReservationMethod, double tip) {
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

  public GroupReservationMethod getGroupReservationMethod() {
    return groupReservationMethod;
  }

  public double getTip() {
    return tip;
  }
}
