package ca.ulaval.glo4002.cafe.domain.cafe;

import org.eclipse.jetty.server.Response;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;

public class InvalidMenuOrderException extends ApplicationException {

  public InvalidMenuOrderException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("INVALID_MENU_ORDER", "An item ordered is not on the menu."));
  }

}
