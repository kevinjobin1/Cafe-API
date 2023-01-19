package ca.ulaval.glo4002.cafe.domain.cafe;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidGroupTipRateExceptionTest {
  private static final String INVALID_GROUP_TIP_RATE_ERROR = "INVALID_GROUP_TIP_RATE";
  private static final String INVALID_GROUP_TIP_RATE_MESSAGE = "The group tip rate must be set to a value between 0 to 100.";
  private static final ErrorResponse INVALID_GROUP_TIP_RATE_RESPONSE = new ErrorResponse(INVALID_GROUP_TIP_RATE_ERROR, INVALID_GROUP_TIP_RATE_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInvalidGroupTipRateErrorWithStatusBadRequest() {
    InvalidGroupTipRateException invalidGroupTipRateException = new InvalidGroupTipRateException();

    assertEquals(SC_BAD_REQUEST, invalidGroupTipRateException.getStatus());
    assertEquals(INVALID_GROUP_TIP_RATE_RESPONSE, invalidGroupTipRateException.getErrorResponse());
  }
}
