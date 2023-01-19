package ca.ulaval.glo4002.cafe.rest.customer;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class DuplicateCustomerIdException extends ApplicationException {
  public DuplicateCustomerIdException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("DUPLICATE_CUSTOMER_ID", "The customer cannot visit the café multiple times in the same day."));
  }
}
