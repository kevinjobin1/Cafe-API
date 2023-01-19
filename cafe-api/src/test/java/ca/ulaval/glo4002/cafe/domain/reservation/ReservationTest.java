package ca.ulaval.glo4002.cafe.domain.reservation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationTest {
  private static final String DEFAULT_GROUP_NAME = "abc-123";
  private static final int DEFAULT_GROUP_SIZE = 2;

  @Test
  void whenCreatingReservation_thenGroupNameAndGroupSizeAreSet() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, DEFAULT_GROUP_SIZE);

    assertEquals(DEFAULT_GROUP_NAME, reservation.getGroupName());
    assertEquals(DEFAULT_GROUP_SIZE, reservation.getGroupSize());
  }
}
