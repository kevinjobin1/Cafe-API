package ca.ulaval.glo4002.cafe.rest.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class CustomerCheckoutRequest {

  @NotNull
  @JsonProperty("customer_id")
  public String customerId;

}
