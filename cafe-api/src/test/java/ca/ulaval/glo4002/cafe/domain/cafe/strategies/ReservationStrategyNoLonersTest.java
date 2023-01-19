package ca.ulaval.glo4002.cafe.domain.cafe.strategies;

import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.SeatStatus;
import ca.ulaval.glo4002.cafe.domain.customer.Customer;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import ca.ulaval.glo4002.cafe.rest.customer.InsufficientSeatsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ReservationStrategyNoLonersTest {

  private static final int DEFAULT_NUMBER_OF_SEATS = 4;
  private static final String DEFAULT_GROUP_NAME = "Scott's tots";
  private static final int A_GROUP_SIZE = 4;
  private static final int ANOTHER_GROUP_SIZE = 5;
  private static final int TOO_LARGE_GROUP_SIZE = 17;
  private static final String CUSTOMER_ID = "123";
  private static final String CUSTOMER_NAME = "Michael Scarn";
  private static final SeatStatus SEAT_STATUS_RESERVED = SeatStatus.RESERVED;
  private ReservationStrategyNoLoners seatReservationStrategyNoLooners;
  private Cafe cafe;

  @BeforeEach
  public void setUp() {
    this.seatReservationStrategyNoLooners = new ReservationStrategyNoLoners();
    this.cafe = Mockito.mock(Cafe.class);
    Mockito.when(cafe.getCubes()).thenReturn(new Cafe("test", DEFAULT_NUMBER_OF_SEATS,
        seatReservationStrategyNoLooners, null, null, 0).getCubes());
  }

  @Test
  void whenReserving_thenSeatsAreReservedInOrder() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, A_GROUP_SIZE);
    seatReservationStrategyNoLooners.reserveSeats(cafe.getCubes(), reservation);

    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(1).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(2).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(3).getStatus());
  }

  @Test
  void whenReservingWithTooLargeGroupSize_thenInsufficientSeatsExceptionIsThrown() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, TOO_LARGE_GROUP_SIZE);
    assertThrows(InsufficientSeatsException.class, () -> {
      seatReservationStrategyNoLooners.reserveSeats(cafe.getCubes(), reservation);
    });
  }

  @Test
  void whenReservingWithGroupSizeBiggerThanCubeSize_thenGroupReservationIsSplit() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, ANOTHER_GROUP_SIZE);
    seatReservationStrategyNoLooners.reserveSeats(cafe.getCubes(), reservation);

    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(1).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(2).getStatus());
    assertNotEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(3).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(1).getStatus());
  }

  @Test
  void givenOneSeatOccupiedInACube_whenReservingForFour_thenGroupReservationIsSplit() {
    Customer customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.getCubes().get(0).getSeats().get(0).occupySeat(customer.getId());

    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, A_GROUP_SIZE);
    seatReservationStrategyNoLooners.reserveSeats(cafe.getCubes(), reservation);

    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(1).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(2).getStatus());
    assertNotEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(3).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(1).getStatus());
  }
}
