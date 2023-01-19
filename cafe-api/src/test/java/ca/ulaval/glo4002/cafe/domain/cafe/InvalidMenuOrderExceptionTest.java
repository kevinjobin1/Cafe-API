package ca.ulaval.glo4002.cafe.domain.cafe;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class InvalidMenuOrderExceptionTest {
  private static final String INVALID_MENU_ORDER_ERROR = "INVALID_MENU_ORDER";
  private static final String INVALID_MENU_ORDER_ERROR_MESSAGE = "An item ordered is not on the menu.";
  private static final ErrorResponse INVALID_MENU_ORDER_ERROR_RESPONSE = new ErrorResponse(INVALID_MENU_ORDER_ERROR, INVALID_MENU_ORDER_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInvalidMenuOrderErrorWithStatusBadRequest() {
    InvalidMenuOrderException invalidMenuOrderException = new InvalidMenuOrderException();

    assertEquals(SC_BAD_REQUEST, invalidMenuOrderException.getStatus());
    assertEquals(INVALID_MENU_ORDER_ERROR_RESPONSE, invalidMenuOrderException.getErrorResponse());
  }
}
