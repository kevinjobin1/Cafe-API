package ca.ulaval.glo4002.cafe.rest.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class CustomerOrdersRequest {

  @NotNull
  @JsonProperty("orders")
  public String[] orders;

}
