package ca.ulaval.glo4002.cafe.domain.ingredient;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.*;

class InvalidIngredientExceptionTest {
  private static final String INVALID_INGREDIENT_ERROR = "INVALID_INGREDIENT";
  private static final String INVALID_INGREDIENT_ERROR_MESSAGE = "The ingredient is not supported.";
  private static final ErrorResponse INVALID_INGREDIENT_ERROR_RESPONSE = new ErrorResponse(INVALID_INGREDIENT_ERROR, INVALID_INGREDIENT_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInvalidIngredientErrorWithStatusBadRequest() {
    InvalidIngredientException invalidIngredientException = new InvalidIngredientException();

    assertEquals(SC_BAD_REQUEST, invalidIngredientException.getStatus());
    assertEquals(INVALID_INGREDIENT_ERROR_RESPONSE, invalidIngredientException.getErrorResponse());

  }
}
