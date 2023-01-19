package ca.ulaval.glo4002.cafe.domain.reservation;

public class Reservation {
  private final String groupName;
  private final int groupSize;

  public Reservation(String groupName, int groupSize) {
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
