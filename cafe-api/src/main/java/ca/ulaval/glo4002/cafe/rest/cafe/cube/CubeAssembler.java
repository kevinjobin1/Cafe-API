package ca.ulaval.glo4002.cafe.rest.cafe.cube;

import java.util.stream.Collectors;

import ca.ulaval.glo4002.cafe.domain.cafe.cube.Cube;
import ca.ulaval.glo4002.cafe.rest.cafe.seat.SeatAssembler;

public class CubeAssembler {
  public final SeatAssembler seatAssembler;

  public CubeAssembler(SeatAssembler seatAssembler) {
    this.seatAssembler = seatAssembler;
  }

  public CubeResponse toResponse(Cube cube) {
    CubeResponse cubeResponse = new CubeResponse();

    cubeResponse.name = cube.getCubeName().toString();
    cubeResponse.seats = cube.getSeats().stream().map(seatAssembler::toResponse).collect(Collectors.toList());

    return cubeResponse;
  }
}
