package ca.ulaval.glo4002.cafe.rest.reservation;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class NoReservationsFoundException extends ApplicationException {
  public NoReservationsFoundException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("NO_RESERVATIONS_FOUND", "No reservations were made today for that group."));
  }
}
