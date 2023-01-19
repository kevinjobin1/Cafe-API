package ca.ulaval.glo4002.cafe.rest.customer;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;
import org.eclipse.jetty.server.Response;

public class CustomerAlreadyCheckedOutException extends ApplicationException {

  public CustomerAlreadyCheckedOutException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("CUSTOMER_ALREADY_CHECKED_OUT", "The customer has already checked out for the day."));
  }

}
