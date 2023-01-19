package ca.ulaval.glo4002.cafe.domain.cafe.seat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatTest {
  private static final int SEAT_NUMBER = 7;
  private static final SeatStatus AVAILABLE_SEAT_STATUS = SeatStatus.AVAILABLE;
  private static final SeatStatus OCCUPIED_SEAT_STATUS = SeatStatus.OCCUPIED;
  private static final SeatStatus RESERVED_SEAT_STATUS = SeatStatus.RESERVED;
  private static final String CUSTOMER_ID = "1234";
  private Seat seat;

  @BeforeEach
  public void setUp() {
    this.seat = new Seat(SEAT_NUMBER);
  }

  @Test
  void whenSeatIsCreated_thenSeatStatusIsAvailable() {
    assertEquals(AVAILABLE_SEAT_STATUS, seat.getStatus());
  }

  @Test
  void whenOccupySeat_thenSeatStatusIsOccupied() {
    seat.occupySeat(CUSTOMER_ID);

    assertEquals(OCCUPIED_SEAT_STATUS, seat.getStatus());
  }

  @Test
  void whenReserveSeat_thenSeatStatusIsReserved() {
    seat.reserveSeat(CUSTOMER_ID);

    assertEquals(RESERVED_SEAT_STATUS, seat.getStatus());
  }

  @Test
  void whenFreeSeat_thenSeatStatusIsAvailable() {
    seat.occupySeat(CUSTOMER_ID);

    seat.freeSeat();

    assertEquals(AVAILABLE_SEAT_STATUS, seat.getStatus());
  }
}
