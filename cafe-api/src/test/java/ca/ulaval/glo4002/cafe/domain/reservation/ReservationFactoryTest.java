package ca.ulaval.glo4002.cafe.domain.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReservationFactoryTest {

  private static final String DEFAULT_GROUP_NAME = "Scott's Tots";
  private static final int GROUP_SIZE = 4;
  private static final int INVALID_GROUP_SIZE = 1;
  private ReservationFactory reservationFactory;
  private Reservation reservation;

  @BeforeEach
  public void setUp() {
    reservationFactory = new ReservationFactory();
  }

  @Test
  void whenCreatingReservation_thenReservationIsCreated() {
    Reservation reservation = reservationFactory.createReservation(DEFAULT_GROUP_NAME, GROUP_SIZE);

    assertEquals(reservation.getGroupSize(), GROUP_SIZE);
    assertEquals(reservation.getGroupName(), DEFAULT_GROUP_NAME);
  }

  @Test
  void whenCreatingReservationWithInvalidGroupSize_thenThrowsInvalidGroupSizeException() {
    assertThrows(InvalidGroupSizeException.class, () -> reservationFactory.createReservation(DEFAULT_GROUP_NAME, INVALID_GROUP_SIZE));
  }
}
