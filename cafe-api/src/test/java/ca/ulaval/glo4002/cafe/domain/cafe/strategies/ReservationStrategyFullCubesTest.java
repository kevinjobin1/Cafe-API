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

class ReservationStrategyFullCubesTest {

  private static final int NUMBER_OF_SEATS = 4;
  private static final String DEFAULT_GROUP_NAME = "Scott's tots";
  private static final String ANOTHER_GROUP_NAME = "Avengers";
  private static final int A_GROUP_SIZE = 4;
  private static final int ANOTHER_GROUP_SIZE = 3;
  private static final int TOO_LARGE_GROUP_SIZE = 17;
  private static final String CUSTOMER_ID = "123";
  private static final String CUSTOMER_NAME = "Michael Scarn";
  private static final String CUSTOMER_ID_2 = "456";
  private static final String CUSTOMER_NAME_2 = "Dwight Schrute";
  private static final String CUSTOMER_ID_3 = "789";
  private static final String CUSTOMER_NAME_3 = "Jim Halpert";
  private static final SeatStatus EXPECTED_SEAT_STATUS = SeatStatus.RESERVED;
  private ReservationStrategyFullCubes seatReservationStrategyFullCubes;
  private Cafe cafe;

  @BeforeEach
  public void setUp() {
    this.seatReservationStrategyFullCubes = new ReservationStrategyFullCubes();
    this.cafe = Mockito.mock(Cafe.class);
    Mockito.when(cafe.getCubes()).thenReturn(new Cafe("test", NUMBER_OF_SEATS,
        seatReservationStrategyFullCubes, null, null, 0).getCubes());
  }

  @Test
  void whenReservingWithGroupSmallerGroupThanCubeSize_thenEntireCubeIsReserved() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, ANOTHER_GROUP_SIZE);
    seatReservationStrategyFullCubes.reserveSeats(cafe.getCubes(), reservation);

    assertTrue(reservation.getGroupSize() < NUMBER_OF_SEATS);
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(0).getSeats().get(0).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(0).getSeats().get(1).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(0).getSeats().get(2).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(0).getSeats().get(3).getStatus());
  }

  @Test
  void givenFirstCubeIsReserved_whenReserving_thenNextCubeIsAlsoReserved() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, A_GROUP_SIZE);
    seatReservationStrategyFullCubes.reserveSeats(cafe.getCubes(), reservation);

    Reservation newReservation = new Reservation(ANOTHER_GROUP_NAME, A_GROUP_SIZE);
    seatReservationStrategyFullCubes.reserveSeats(cafe.getCubes(), newReservation);

    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(1).getSeats().get(0).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(1).getSeats().get(1).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(1).getSeats().get(2).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(1).getSeats().get(3).getStatus());
  }

  @Test
  void whenReservingWithTooLargeGroupSize_thenInsufficientSeatsExceptionIsThrown() {
    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, TOO_LARGE_GROUP_SIZE);
    assertThrows(InsufficientSeatsException.class, () -> {
      seatReservationStrategyFullCubes.reserveSeats(cafe.getCubes(), reservation);
    });
  }

  @Test
  void givenEveryCubeButOneHasSingleCustomerInIt_whenReserving_thenLastCubeIsReserved() {
    Customer customerCube0 = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.getCubes().get(0).getSeats().get(0).occupySeat(customerCube0.getId());
    assertEquals(SeatStatus.OCCUPIED, cafe.getCubes().get(0).getSeats().get(0).getStatus());

    Customer customerCube1 = new Customer(CUSTOMER_ID_2, CUSTOMER_NAME_2, null);
    cafe.getCubes().get(1).getSeats().get(0).occupySeat(customerCube1.getId());
    assertEquals(SeatStatus.OCCUPIED, cafe.getCubes().get(1).getSeats().get(0).getStatus());

    Customer customerCube2 = new Customer(CUSTOMER_ID_3, CUSTOMER_NAME_3, null);
    cafe.getCubes().get(2).getSeats().get(0).occupySeat(customerCube2.getId());
    assertEquals(SeatStatus.OCCUPIED, cafe.getCubes().get(2).getSeats().get(0).getStatus());

    Reservation reservation = new Reservation(DEFAULT_GROUP_NAME, A_GROUP_SIZE);
    seatReservationStrategyFullCubes.reserveSeats(cafe.getCubes(), reservation);

    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(3).getSeats().get(0).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(3).getSeats().get(1).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(3).getSeats().get(2).getStatus());
    assertEquals(EXPECTED_SEAT_STATUS, cafe.getCubes().get(3).getSeats().get(3).getStatus());
  }
}
