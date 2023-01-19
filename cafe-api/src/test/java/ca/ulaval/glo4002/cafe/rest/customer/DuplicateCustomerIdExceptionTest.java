package ca.ulaval.glo4002.cafe.rest.customer;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

class DuplicateCustomerIdExceptionTest {

  private static final String DUPLICATE_CUSTOMER_ID_ERROR = "DUPLICATE_CUSTOMER_ID";
  private static final String DUPLICATE_CUSTOMER_ID_ERROR_MESSAGE = "The customer cannot visit the café multiple times in the same day.";
  private static final ErrorResponse DUPLICATE_CUSTOMER_ID_ERROR_RESPONSE = new ErrorResponse(DUPLICATE_CUSTOMER_ID_ERROR, DUPLICATE_CUSTOMER_ID_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenReturnsDuplicateCustomerIdErrorWithStatusBadRequest() {
    DuplicateCustomerIdException duplicateCustomerIdException = new DuplicateCustomerIdException();

    assertEquals(SC_BAD_REQUEST, duplicateCustomerIdException.getStatus());
    assertEquals(DUPLICATE_CUSTOMER_ID_ERROR_RESPONSE, duplicateCustomerIdException.getErrorResponse());
  }
}



