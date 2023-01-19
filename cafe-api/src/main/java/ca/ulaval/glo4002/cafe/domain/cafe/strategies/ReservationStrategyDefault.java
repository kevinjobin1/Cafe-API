package ca.ulaval.glo4002.cafe.domain.cafe.strategies;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.cafe.cube.Cube;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.Seat;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import ca.ulaval.glo4002.cafe.rest.customer.InsufficientSeatsException;

public class ReservationStrategyDefault implements ReservationStrategy {
  @Override
  public void reserveSeats(List<Cube> cubes, Reservation reservation) {
    List<Seat> availableSeats = new ArrayList<>();
    int groupSize = reservation.getGroupSize();
    String groupName = reservation.getGroupName();
    for (Cube cube : cubes) {
      availableSeats.addAll(cube.getAvailableSeats());
    }
    if (availableSeats.size() < groupSize) {
      throw new InsufficientSeatsException();
    }

    availableSeats.stream().limit(groupSize).forEach(seat -> {
      seat.reserveSeat(groupName);
    });
  }
}
