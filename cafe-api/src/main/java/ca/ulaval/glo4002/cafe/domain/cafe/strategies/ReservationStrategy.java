package ca.ulaval.glo4002.cafe.domain.cafe.strategies;

import java.util.List;

import ca.ulaval.glo4002.cafe.domain.cafe.cube.Cube;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;

public interface ReservationStrategy {
  void reserveSeats(List<Cube> cubes, Reservation reservation);
}
