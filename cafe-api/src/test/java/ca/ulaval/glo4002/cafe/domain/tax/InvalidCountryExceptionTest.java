package ca.ulaval.glo4002.cafe.domain.tax;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidCountryExceptionTest {
  private static final String INVALID_COUNTRY_ERROR = "INVALID_COUNTRY";
  private static final String INVALID_COUNTRY_ERROR_MESSAGE = "The specified country is invalid.";
  private static final ErrorResponse INVALID_COUNTRY_ERROR_RESPONSE = new ErrorResponse(INVALID_COUNTRY_ERROR, INVALID_COUNTRY_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInsufficientIngredientsErrorWithStatusBadRequest() {
    InvalidCountryException invalidCountryException = new InvalidCountryException();

    assertEquals(SC_BAD_REQUEST, invalidCountryException.getStatus());
    assertEquals(INVALID_COUNTRY_ERROR_RESPONSE, invalidCountryException.getErrorResponse());
  }
}
