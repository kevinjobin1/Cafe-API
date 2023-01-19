package ca.ulaval.glo4002.cafe.domain.ingredient;

import ca.ulaval.glo4002.cafe.exception.ApplicationException;
import ca.ulaval.glo4002.cafe.exception.ErrorResponse;
import org.eclipse.jetty.server.Response;

public class InvalidIngredientException extends ApplicationException {

  public InvalidIngredientException() {
    super(Response.SC_BAD_REQUEST, new ErrorResponse("INVALID_INGREDIENT", "The ingredient is not supported."));
  }

}
