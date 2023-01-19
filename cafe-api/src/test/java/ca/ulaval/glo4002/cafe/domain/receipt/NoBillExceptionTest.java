package ca.ulaval.glo4002.cafe.domain.receipt;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoBillExceptionTest {
  private static final String NO_BILL_ERROR = "NO_BILL";
  private static final String NO_BILL_ERROR_MESSAGE = "The customer needs to do a checkout before receiving his bill.";
  private static final ErrorResponse NO_BILL_ERROR_RESPONSE = new ErrorResponse(NO_BILL_ERROR, NO_BILL_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsNoBillErrorWithStatusBadRequest() {
    NoBillException noBillException = new NoBillException();

    assertEquals(SC_BAD_REQUEST, noBillException.getStatus());
    assertEquals(NO_BILL_ERROR_RESPONSE, noBillException.getErrorResponse());
  }
}
