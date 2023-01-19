package ca.ulaval.glo4002.cafe.rest.reservation;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NoReservationsFoundExceptionTest {
  private static final String NO_RESERVATIONS_FOUND_ERROR = "NO_RESERVATIONS_FOUND";
  private static final String NO_RESERVATIONS_FOUND_ERROR_MESSAGE = "No reservations were made today for that group.";
  private static final ErrorResponse NO_RESERVATIONS_FOUND_ERROR_RESPONSE = new ErrorResponse(NO_RESERVATIONS_FOUND_ERROR, NO_RESERVATIONS_FOUND_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenIsCreatedWithNoReservationErrorAndStatusBadRequest() {
    NoReservationsFoundException noReservationsFoundException = new NoReservationsFoundException();

    assertEquals(SC_BAD_REQUEST, noReservationsFoundException.getStatus());
    assertEquals(NO_RESERVATIONS_FOUND_ERROR_RESPONSE, noReservationsFoundException.getErrorResponse());
  }
}
