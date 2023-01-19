package ca.ulaval.glo4002.cafe.rest.customer;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "seat_number", "group_name"})
public class CustomerResponse {
  public String name;
  public int seat_number;
  public String group_name;

  public CustomerResponse(String name, int seat_number, String group_name) {
    this.name = name;
    this.seat_number = seat_number;
    this.group_name = group_name;
  }
}
