package ca.ulaval.glo4002.cafe.domain.cafe;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;
import org.eclipse.jetty.server.Response;

public class InvalidGroupTipRateException extends ApplicationException {

  public InvalidGroupTipRateException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("INVALID_GROUP_TIP_RATE", "The group tip rate must be set to a value between 0 to 100."));
  }

}
