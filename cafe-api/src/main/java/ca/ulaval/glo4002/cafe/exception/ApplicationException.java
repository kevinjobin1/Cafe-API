package ca.ulaval.glo4002.cafe.exception;

public abstract class ApplicationException extends RuntimeException {
  private final int status;
  private final ErrorResponse errorResponse;

  protected ApplicationException(int status, ErrorResponse errorResponse) {
    this.status = status;
    this.errorResponse = errorResponse;
  }

  public ErrorResponse getErrorResponse() {
    return errorResponse;
  }

  public int getStatus() {
    return status;
  }
}
