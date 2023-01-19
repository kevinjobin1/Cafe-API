package ca.ulaval.glo4002.cafe.rest.reservation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"group_name", "group_size"})
public class ReservationResponse {
  public String group_name;
  public int group_size;

  public ReservationResponse(String group_name, int group_size) {
    this.group_name = group_name;
    this.group_size = group_size;
  }
}
