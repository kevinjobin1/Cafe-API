package ca.ulaval.glo4002.cafe.domain.cafe.strategies;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.cafe.cube.Cube;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.Seat;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import ca.ulaval.glo4002.cafe.rest.customer.InsufficientSeatsException;

public class ReservationStrategyNoLoners implements ReservationStrategy {

  @Override
  public void reserveSeats(List<Cube> cubes, Reservation reservation) {
    int groupSize = reservation.getGroupSize();
    String groupName = reservation.getGroupName();
    List<Seat> seatsToReserve = new ArrayList<>();

    for (Cube cube : cubes) {
      List<Seat> availableSeatsInCube = cube.getAvailableSeats();
      if (availableSeatsInCube.size() > 1) {
        if (availableSeatsInCube.size() + 1 == groupSize - seatsToReserve.size()) {
          seatsToReserve.addAll(availableSeatsInCube.subList(0, availableSeatsInCube.size() - 1));
        }
        else if (availableSeatsInCube.size() < groupSize - seatsToReserve.size()) {
          seatsToReserve.addAll(availableSeatsInCube);
        }
        else {
          seatsToReserve.addAll(availableSeatsInCube.subList(0, groupSize - seatsToReserve.size()));
        }
      }
    }

    if (seatsToReserve.size() < groupSize) {
      throw new InsufficientSeatsException();
    }

    for (Seat seat : seatsToReserve) {
      seat.reserveSeat(groupName);
    }
  }
}
