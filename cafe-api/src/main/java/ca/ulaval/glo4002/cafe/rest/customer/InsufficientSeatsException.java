package ca.ulaval.glo4002.cafe.rest.customer;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class InsufficientSeatsException extends ApplicationException {
  public InsufficientSeatsException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("INSUFFICIENT_SEATS", "There are currently no available seats. Please come back later."));
  }
}
