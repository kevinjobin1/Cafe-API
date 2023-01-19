package ca.ulaval.glo4002.cafe.domain.cafe.strategies;

import java.util.List;

import ca.ulaval.glo4002.cafe.domain.cafe.cube.Cube;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import ca.ulaval.glo4002.cafe.rest.customer.InsufficientSeatsException;

public class ReservationStrategyFullCubes implements ReservationStrategy {

  @Override
  public void reserveSeats(List<Cube> cubes, Reservation reservation) {
    int groupSize = reservation.getGroupSize();
    String groupName = reservation.getGroupName();
    int cubeSize = cubes.get(0).getCubeSize();
    int neededCubes = (int) Math.ceil((double) groupSize / cubeSize);

    List<Cube> availableCubes = cubes.stream().filter(Cube::hasAllAvailableSeats).toList();

    if (availableCubes.size() < neededCubes) {
      throw new InsufficientSeatsException();
    }

    for (int i = 0; i < neededCubes; i++) {
      availableCubes.get(i).reserveAllSeats(groupName);
    }
  }
}
