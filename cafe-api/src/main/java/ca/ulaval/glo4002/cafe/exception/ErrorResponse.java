package ca.ulaval.glo4002.cafe.exception;

import java.util.Objects;

public class ErrorResponse {
  public String error;
  public String description;

  public ErrorResponse(String error, String description) {
    this.error = error;
    this.description = description;
  }
  public String getError() {
    return error;
  }
  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse that = (ErrorResponse) o;
    return Objects.equals(getError(), that.getError()) && Objects.equals(getDescription(), that.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getError(), getDescription());
  }
}
