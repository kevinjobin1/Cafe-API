package ca.ulaval.glo4002.cafe.domain.ingredient;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;
import org.eclipse.jetty.server.Response;

public class InsufficientIngredientsException extends ApplicationException {

  public InsufficientIngredientsException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("INSUFFICIENT_INGREDIENTS", "We lack the necessary number of ingredients to fulfill your order."));
  }

}
