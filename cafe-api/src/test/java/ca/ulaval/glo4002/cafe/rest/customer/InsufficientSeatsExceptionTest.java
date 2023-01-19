package ca.ulaval.glo4002.cafe.rest.customer;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;


import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class InsufficientSeatsExceptionTest {
  private static final String INSUFFICIENT_SEATS_ERROR = "INSUFFICIENT_SEATS";
  private static final String INSUFFICIENT_SEATS_ERROR_MESSAGE = "There are currently no available seats. Please come back later.";
  private static final ErrorResponse INSUFFICIENT_SEATS_ERROR_RESPONSE = new ErrorResponse(INSUFFICIENT_SEATS_ERROR, INSUFFICIENT_SEATS_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInsufficientSeatsErrorWithStatusBadRequest() {
    InsufficientSeatsException insufficientSeatsException = new InsufficientSeatsException();

    assertEquals(SC_BAD_REQUEST, insufficientSeatsException.getStatus());
    assertEquals(INSUFFICIENT_SEATS_ERROR_RESPONSE, insufficientSeatsException.getErrorResponse());
  }
}
