package ca.ulaval.glo4002.cafe.domain.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationListTest {
  private static final String CUSTOMER_GROUP_NAME = "Scotts Tots";
  private static final int GROUP_SIZE = 5;

  private ReservationList reservationList;

  @BeforeEach
  public void setUp() {
    reservationList = new ReservationList();
  }

  @Test
  void givenAReservation_whenAddReservation_thenShouldAddReservation() {
    Reservation reservation = new Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);

    reservationList.addReservation(reservation);

    assertEquals(reservation, reservationList.getReservationByGroupName(CUSTOMER_GROUP_NAME).get());
  }

  @Test
  void givenAReservation_whenGetReservationByGroupName_thenShouldReturnReservation() {
    Reservation reservation = new Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);
    reservationList.addReservation(reservation);

    Optional<Reservation> reservationFound = reservationList.getReservationByGroupName(CUSTOMER_GROUP_NAME);

    assertEquals(reservation, reservationFound.get());
  }

  @Test
  void givenMultipleReservations_whenGetReservations_thenShouldReturnAllReservations() {
    Reservation reservation1 = new Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);
    Reservation reservation2 = new Reservation("Group 2", 2);
    reservationList.addReservation(reservation1);
    reservationList.addReservation(reservation2);

    List<Reservation> reservations = reservationList.getReservations();

    assertEquals(2, reservations.size());
  }

  @Test
  void givenMultipleReservations_whenClearingAllReservations_thenShouldRemoveAllReservations() {
    Reservation reservation1 = new Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);
    Reservation reservation2 = new Reservation("Group 2", 2);
    reservationList.addReservation(reservation1);
    reservationList.addReservation(reservation2);

    reservationList.clearAllReservations();

    assertEquals(0, reservationList.getReservations().size());
  }
}
