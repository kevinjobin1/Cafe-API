package ca.ulaval.glo4002.cafe.rest.reservation;

public class ReservationDTO {

  private final String groupName;
  private final int groupSize;

  public ReservationDTO(String groupName, int groupSize) {
    this.groupName = groupName;
    this.groupSize = groupSize;
  }

  public String getGroupName() {
    return groupName;
  }

  public int getGroupSize() {
    return groupSize;
  }

}
