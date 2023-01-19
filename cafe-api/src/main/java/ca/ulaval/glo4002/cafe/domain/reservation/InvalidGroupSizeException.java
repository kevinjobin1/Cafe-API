package ca.ulaval.glo4002.cafe.domain.reservation;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class InvalidGroupSizeException extends ApplicationException {
  public InvalidGroupSizeException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("INVALID_GROUP_SIZE", "Groups must reserve at least two seats."));
  }
}
