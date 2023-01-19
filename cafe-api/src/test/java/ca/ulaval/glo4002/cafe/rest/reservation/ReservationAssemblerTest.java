package ca.ulaval.glo4002.cafe.rest.reservation;

import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReservationAssemblerTest {
  private static final String DEFAULT_GROUP_NAME = "Scott's Tots";
  private static final int DEFAULT_GROUP_SIZE = 4;
  private Reservation reservation;
  private ReservationAssembler reservationAssembler;

  @BeforeEach
  public void setup() {
    this.reservationAssembler = new ReservationAssembler();
    this.reservation = new Reservation(DEFAULT_GROUP_NAME, DEFAULT_GROUP_SIZE);
  }

  @Test
  void givenReservation_whenAssemblingResponse_thenResponseHasValidGroupName() {
    ReservationResponse reservationResponse = reservationAssembler.toResponse(reservation);

    assertEquals(DEFAULT_GROUP_NAME, reservationResponse.group_name);
  }

  @Test
  void givenReservation_whenAssemblingResponse_thenResponseHasValidGroupSize() {
    ReservationResponse reservationResponse = reservationAssembler.toResponse(reservation);

    assertEquals(DEFAULT_GROUP_SIZE, reservationResponse.group_size);
  }

  @Test
  void givenMultipleReservations_whenAssemblingResponse_thenResponseHasValidNumberOfReservations() {
    Reservation otherReservation = new Reservation("The Office", 4);
    List<Reservation> reservations = List.of(reservation, otherReservation);

    List<ReservationResponse> reservationsResponse = reservationAssembler.toResponse(reservations);

    assertEquals(reservations.size(), reservationsResponse.size());
  }
}
