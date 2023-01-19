package ca.ulaval.glo4002.cafe.rest.reservation;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class DuplicateGroupNameException extends ApplicationException {
  public DuplicateGroupNameException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("DUPLICATE_GROUP_NAME", "The specified group already made a reservation today."));
  }
}
