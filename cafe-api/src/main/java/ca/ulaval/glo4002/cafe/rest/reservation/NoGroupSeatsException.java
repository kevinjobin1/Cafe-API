package ca.ulaval.glo4002.cafe.rest.reservation;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class NoGroupSeatsException extends ApplicationException {
  public NoGroupSeatsException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("NO_GROUP_SEATS", "There are no more seats reserved for that group."));
  }
}
