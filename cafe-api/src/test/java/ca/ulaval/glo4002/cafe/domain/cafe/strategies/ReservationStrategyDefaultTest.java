package ca.ulaval.glo4002.cafe.domain.cafe.strategies;

import java.util.List;

import ca.ulaval.glo4002.cafe.domain.product.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.SeatStatus;
import ca.ulaval.glo4002.cafe.domain.customer.Customer;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import ca.ulaval.glo4002.cafe.rest.customer.InsufficientSeatsException;

class ReservationStrategyDefaultTest {

  private static final int NUMBER_OF_SEATS = 4;
  private static final String DEFAULT_GROUP_NAME = "Scott's tots";
  private static final String ANOTHER_GROUP_NAME = "Avengers";
  private static final int A_GROUP_SIZE = 4;
  private static final int ANOTHER_GROUP_SIZE = 3;
  private static final int TOO_LARGE_GROUP_SIZE = 17;
  private static final String CUSTOMER_ID = "123";
  private static final String CUSTOMER_NAME = "Michael Scarn";
  private static final String OTHER_CUSTOMER_ID = "456";
  private static final String OTHER_CUSTOMER_NAME = "Dwight Schrute";
  private static final SeatStatus SEAT_STATUS_RESERVED = SeatStatus.RESERVED;
  private static final List<Product> DEFAULT_PRODUCTS = List.of();
  private static final Menu DEFAULT_MENU = new Menu(DEFAULT_PRODUCTS);
  private ReservationStrategyDefault reservationStrategyDefault;
  private Cafe cafe;

  @BeforeEach
  public void setUp() {
    this.reservationStrategyDefault = new ReservationStrategyDefault();
    this.cafe = Mockito.mock(Cafe.class);
    Mockito.when(cafe.getCubes()).thenReturn(new Cafe("test", NUMBER_OF_SEATS,
        reservationStrategyDefault, DEFAULT_MENU, null, 0).getCubes());
  }

  @Test
  void whenReserving_thenSeatsAreReservedInOrder() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, A_GROUP_SIZE);
    reservationStrategyDefault.reserveSeats(cafe.getCubes(), reservation);

    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(1).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(2).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(3).getStatus());
  }

  @Test
  void givenSomeSeatsAreAlreadyReserved_whenReserving_thenNextSeatsAreReservedForOtherGroup() {
    Reservation firstReservation = new Reservation(DEFAULT_GROUP_NAME, A_GROUP_SIZE);
    reservationStrategyDefault.reserveSeats(cafe.getCubes(), firstReservation);

    Reservation newReservation = new Reservation(ANOTHER_GROUP_NAME, ANOTHER_GROUP_SIZE);
    reservationStrategyDefault.reserveSeats(cafe.getCubes(), newReservation);

    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(1).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(2).getStatus());
    assertNotEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(3).getStatus());
  }

  @Test
  void whenReservingWithTooLargeGroupSize_thenInsufficientSeatsExceptionIsThrown() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, TOO_LARGE_GROUP_SIZE);
    assertThrows(InsufficientSeatsException.class, () -> {
      reservationStrategyDefault.reserveSeats(cafe.getCubes(), reservation);
    });
  }

  @Test
  void givenOneSeatIsOccupiedInCube_whenReserving_thenAvailableSeatsInCubeAreStillReserved() {
    Customer customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.getCubes().get(0).getSeats().get(0).occupySeat(customer.getId());

    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, A_GROUP_SIZE);
    reservationStrategyDefault.reserveSeats(cafe.getCubes(), reservation);

    assertNotEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(1).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(2).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(3).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(0).getStatus());
  }

  @Test
  void givenFirstAndThirdSeatInCubeIsOccupied_whenReserving_AllAvailableSeatsInCubeAreReserved() {
    Customer customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.getCubes().get(0).getSeats().get(0).occupySeat(customer.getId());
    Customer customerOther = new Customer(OTHER_CUSTOMER_ID, OTHER_CUSTOMER_NAME, null);
    cafe.getCubes().get(0).getSeats().get(2).occupySeat(customerOther.getId());

    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, A_GROUP_SIZE);
    reservationStrategyDefault.reserveSeats(cafe.getCubes(), reservation);

    assertNotEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(1).getStatus());
    assertNotEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(2).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(0).getSeats().get(3).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(0).getStatus());
    assertEquals(SEAT_STATUS_RESERVED, cafe.getCubes().get(1).getSeats().get(1).getStatus());
  }
}
