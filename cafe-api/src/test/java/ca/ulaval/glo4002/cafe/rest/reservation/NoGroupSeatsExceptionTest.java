package ca.ulaval.glo4002.cafe.rest.reservation;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NoGroupSeatsExceptionTest {
  private static final String NO_GROUP_SEATS_ERROR = "NO_GROUP_SEATS";
  private static final String NO_GROUP_SEATS_ERROR_MESSAGE = "There are no more seats reserved for that group.";
  private static final ErrorResponse NO_GROUP_SEATS_ERROR_RESPONSE = new ErrorResponse(NO_GROUP_SEATS_ERROR, NO_GROUP_SEATS_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenCreatesNoGroupSeatsExceptionWithErrorStatusBadRequest() {
    NoGroupSeatsException noGroupSeatsException = new NoGroupSeatsException();

    assertEquals(SC_BAD_REQUEST, noGroupSeatsException.getStatus());
    assertEquals(NO_GROUP_SEATS_ERROR_RESPONSE, noGroupSeatsException.getErrorResponse());
  }
}
