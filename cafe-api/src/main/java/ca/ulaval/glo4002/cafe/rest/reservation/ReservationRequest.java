package ca.ulaval.glo4002.cafe.rest.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class ReservationRequest {
  @NotNull
  @JsonProperty("group_name")
  public String groupName;

  @NotNull
  @JsonProperty("group_size")
  public int groupSize;
}
