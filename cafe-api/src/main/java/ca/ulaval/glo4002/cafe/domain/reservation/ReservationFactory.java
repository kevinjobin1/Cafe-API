package ca.ulaval.glo4002.cafe.domain.reservation;

public class ReservationFactory {
  private static final int MINIMUM_GROUP_SIZE = 2;

  public Reservation createReservation(String groupName, int groupSize) {

    verifyGroupSize(groupSize);

    return new Reservation(groupName, groupSize);
  }

  private void verifyGroupSize(int groupSize) {
    if (groupSize < MINIMUM_GROUP_SIZE) {
      throw new InvalidGroupSizeException();
    }
  }
}
