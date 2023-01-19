package ca.ulaval.glo4002.cafe.rest.cafe;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ca.ulaval.glo4002.cafe.rest.cafe.cube.CubeResponse;

@JsonPropertyOrder({"name", "cubes"})
public class CafeResponse {
  public String name;
  public List<CubeResponse> cubes;
}
