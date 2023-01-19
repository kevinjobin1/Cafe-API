package ca.ulaval.glo4002.cafe.rest.cafe.cube;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.ulaval.glo4002.cafe.rest.cafe.seat.SeatResponse;

@JsonPropertyOrder({"name", "seats"})
public class CubeResponse {
  public String name;
  public List<SeatResponse> seats;
}
