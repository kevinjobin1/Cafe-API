package ca.ulaval.glo4002.cafe.domain.ingredient;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class InsufficientIngredientsExceptionTest {
  private static final String INSUFFICIENT_INGREDIENTS_ERROR = "INSUFFICIENT_INGREDIENTS";
  private static final String INSUFFICIENT_INGREDIENTS_ERROR_MESSAGE = "We lack the necessary number of ingredients to fulfill your order.";
  private static final ErrorResponse INSUFFICIENT_INGREDIENTS_ERROR_RESPONSE = new ErrorResponse(INSUFFICIENT_INGREDIENTS_ERROR,
      INSUFFICIENT_INGREDIENTS_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInsufficientIngredientsErrorWithStatusBadRequest() {
    InsufficientIngredientsException insufficientIngredientsException = new InsufficientIngredientsException();

    assertEquals(SC_BAD_REQUEST, insufficientIngredientsException.getStatus());
    assertEquals(INSUFFICIENT_INGREDIENTS_ERROR_RESPONSE, insufficientIngredientsException.getErrorResponse());
  }
}
