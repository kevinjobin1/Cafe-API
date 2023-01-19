package ca.ulaval.glo4002.cafe.domain.cafe;

import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategy;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategyDefault;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategyFullCubes;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategyNoLoners;

public class ReservationStrategyFactory {

  public ReservationStrategy createReservationStrategy(GroupReservationMethod groupReservationMethod) {
    if (groupReservationMethod.equals(GroupReservationMethod.DEFAULT)) {
      return new ReservationStrategyDefault();
    }
    else if (groupReservationMethod.equals(GroupReservationMethod.FULL_CUBES)) {
      return new ReservationStrategyFullCubes();
    }
    else if (groupReservationMethod.equals(GroupReservationMethod.NO_LONERS)) {
      return new ReservationStrategyNoLoners();
    }
    throw new RuntimeException();
  }
}
