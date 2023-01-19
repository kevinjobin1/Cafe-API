package ca.ulaval.glo4002.cafe.rest.cafe.seat;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"number", "status", "customer_id", "group_name"})
public class SeatResponse {
  public int number;
  public String status;
  public String customer_id;
  public String group_name;
}
