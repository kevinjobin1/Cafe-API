package ca.ulaval.glo4002.cafe.rest.reservation;

import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DuplicateGroupNameExceptionTest {
  private static final String DUPLICATE_GROUP_NAME_ERROR = "DUPLICATE_GROUP_NAME";
  private static final String DUPLICATE_GROUP_NAME_ERROR_MESSAGE = "The specified group already made a reservation today.";
  private static final ErrorResponse DUPLICATE_GROUP_NAME_ERROR_RESPONSE = new ErrorResponse(DUPLICATE_GROUP_NAME_ERROR, DUPLICATE_GROUP_NAME_ERROR_MESSAGE);

  @Test
  void whenInitialized_thenIsCreatedWithDuplicateGroupNameErrorAndStatusBadRequest() {
    DuplicateGroupNameException duplicateGroupNameException = new DuplicateGroupNameException();

    assertEquals(SC_BAD_REQUEST, duplicateGroupNameException.getStatus());
    assertEquals(DUPLICATE_GROUP_NAME_ERROR_RESPONSE, duplicateGroupNameException.getErrorResponse());
  }

}
