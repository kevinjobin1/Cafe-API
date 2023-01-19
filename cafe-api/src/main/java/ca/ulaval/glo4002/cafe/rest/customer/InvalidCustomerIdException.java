package ca.ulaval.glo4002.cafe.rest.customer;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class InvalidCustomerIdException extends ApplicationException {
  public InvalidCustomerIdException() {
    super(Response.SC_NOT_FOUND, new ErrorResponse("INVALID_CUSTOMER_ID", "The customer does not exist."));
  }
}
