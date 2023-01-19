package ca.ulaval.glo4002.cafe.domain.reservation;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class InvalidGroupSizeExceptionTest {
  private static final String INVALID_GROUP_SIZE_ERROR = "INVALID_GROUP_SIZE";
  private static final String INVALID_GROUP_SIZE_ERROR_MESSAGE = "Groups must reserve at least two seats.";
  private static final ErrorResponse INVALID_GROUP_SIZE_ERROR_RESPONSE = new ErrorResponse(INVALID_GROUP_SIZE_ERROR, INVALID_GROUP_SIZE_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInvalidGroupSizeErrorWithStatusBadRequest() {
    InvalidGroupSizeException invalidGroupSizeException = new InvalidGroupSizeException();

    assertEquals(SC_BAD_REQUEST, invalidGroupSizeException.getStatus());
    assertEquals(INVALID_GROUP_SIZE_ERROR_RESPONSE, invalidGroupSizeException.getErrorResponse());
  }
}
