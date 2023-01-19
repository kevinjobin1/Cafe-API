package ca.ulaval.glo4002.cafe.rest.customer;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

class InvalidCustomerIdExceptionTest {
  private static final String INVALID_CUSTOMER_ID_ERROR = "INVALID_CUSTOMER_ID";
  private static final String INVALID_CUSTOMER_ID_ERROR_MESSAGE = "The customer does not exist.";
  private static final ErrorResponse INVALID_CUSTOMER_ID_ERROR_RESPONSE = new ErrorResponse(INVALID_CUSTOMER_ID_ERROR, INVALID_CUSTOMER_ID_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsInvalidCustomerIdErrorWithStatusNotFound() {
    InvalidCustomerIdException invalidCustomerIdException = new InvalidCustomerIdException();

    assertEquals(SC_NOT_FOUND, invalidCustomerIdException.getStatus());
    assertEquals(INVALID_CUSTOMER_ID_ERROR_RESPONSE, invalidCustomerIdException.getErrorResponse());
  }
}
