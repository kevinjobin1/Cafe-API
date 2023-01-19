package ca.ulaval.glo4002.cafe.domain.receipt;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;
import org.eclipse.jetty.server.Response;

public class NoBillException extends ApplicationException {

  public NoBillException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("NO_BILL", "The customer needs to do a checkout before receiving his bill."));
  }

}
