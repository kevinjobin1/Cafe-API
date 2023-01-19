package ca.ulaval.glo4002.cafe.domain.tax;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;
import org.eclipse.jetty.server.Response;

public class InvalidCountryException extends ApplicationException {

  public InvalidCountryException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("INVALID_COUNTRY", "The specified country is invalid."));
  }

}
