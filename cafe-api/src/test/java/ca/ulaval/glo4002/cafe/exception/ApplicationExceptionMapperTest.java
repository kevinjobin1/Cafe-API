package ca.ulaval.glo4002.cafe.exception;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ApplicationExceptionMapperTest {
  private static final String AN_ERROR_DESCRIPTION = "You cannot do that, it is forbidden by the programming law.";
  private static final String AN_ERROR = "GENERIC_ERROR";
  private static final int EXPECTED_ERROR_CODE = 400;
  private static final ErrorResponse EXPECTED_ERROR_RESPONSE = new ErrorResponse(AN_ERROR, AN_ERROR_DESCRIPTION);
  ApplicationExceptionMapper applicationExceptionMapper;

  @BeforeEach
  void setUp() {
    this.applicationExceptionMapper = new ApplicationExceptionMapper();
  }

  @Test
  void givenAnException_whenConvertingToResponse_thenReturnsGivenErrorResponseWithGivenErrorCode() {
    ApplicationException anException = mock(ApplicationException.class);
    when(anException.getStatus()).thenReturn(EXPECTED_ERROR_CODE);
    when(anException.getErrorResponse()).thenReturn(EXPECTED_ERROR_RESPONSE);

    Response response = applicationExceptionMapper.toResponse(anException);

    ErrorResponse actualErrorResponse = (ErrorResponse) response.getEntity();
    assertEquals(EXPECTED_ERROR_CODE, response.getStatus());
    assertEquals(EXPECTED_ERROR_RESPONSE.error, actualErrorResponse.error);
    assertEquals(EXPECTED_ERROR_RESPONSE.description, actualErrorResponse.description);
  }
}
