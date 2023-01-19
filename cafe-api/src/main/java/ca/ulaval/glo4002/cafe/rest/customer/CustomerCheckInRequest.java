package ca.ulaval.glo4002.cafe.rest.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class CustomerCheckInRequest {
  @NotNull
  @JsonProperty("customer_id")
  public String customerId;

  @NotNull
  @JsonProperty("customer_name")
  public String customerName;

  @JsonProperty("group_name")
  public String groupName;
}
