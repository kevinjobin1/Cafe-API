package ca.ulaval.glo4002.cafe.domain.cafe;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class InvalidGroupReservationMethodException extends ApplicationException {

  public InvalidGroupReservationMethodException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("INVALID_GROUP_RESERVATION_METHOD", "The group reservation method is not supported."));
  }
}
