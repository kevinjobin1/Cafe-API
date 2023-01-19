package ca.ulaval.glo4002.cafe.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
  @Override
  public Response toResponse(ApplicationException exception) {
    return Response.status(exception.getStatus()).entity(exception.getErrorResponse()).build();
  }
}
