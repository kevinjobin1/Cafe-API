package ca.ulaval.glo4002.cafe.domain.cafe;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

class InvalidGroupReservationMethodExceptionTest {
  private static final String INVALID_GROUP_RESERVATION_METHOD_ERROR = "INVALID_GROUP_RESERVATION_METHOD";
  private static final String INVALID_GROUP_RESERVATION_METHOD_ERROR_MESSAGE = "The group reservation method is not supported.";
  private static final ErrorResponse INVALID_GROUP_RESERVATION_METHOD_ERROR_RESPONSE = new ErrorResponse(INVALID_GROUP_RESERVATION_METHOD_ERROR,
                                                                                                         INVALID_GROUP_RESERVATION_METHOD_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInvalidGroupReservationMethodErrorWithStatusBadRequest() {
    InvalidGroupReservationMethodException invalidGroupReservationMethodException = new InvalidGroupReservationMethodException();

    assertEquals(SC_BAD_REQUEST, invalidGroupReservationMethodException.getStatus());
    assertEquals(INVALID_GROUP_RESERVATION_METHOD_ERROR_RESPONSE, invalidGroupReservationMethodException.getErrorResponse());
  }
}
