package ca.ulaval.glo4002.cafe.rest.cafe;

import java.util.stream.Collectors;

import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.rest.cafe.cube.CubeAssembler;

public class CafeAssembler {
  private final CubeAssembler cubeAssembler;

  public CafeAssembler(CubeAssembler cubeAssembler) {
    this.cubeAssembler = cubeAssembler;
  }

  public CafeResponse toResponse(Cafe cafe) {
    CafeResponse cafeResponse = new CafeResponse();

    cafeResponse.name = cafe.getOrganizationName();
    cafeResponse.cubes = cafe.getCubes().stream().map(cubeAssembler::toResponse).collect(Collectors.toList());

    return cafeResponse;
  }
}
