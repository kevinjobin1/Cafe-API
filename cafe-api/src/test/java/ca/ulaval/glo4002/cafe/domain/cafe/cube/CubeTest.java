package ca.ulaval.glo4002.cafe.domain.cafe.cube;

import ca.ulaval.glo4002.cafe.domain.cafe.seat.Seat;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.SeatStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.*;


class CubeTest {

  private static final CubeName DEFAULT_CUBE_NAME = CubeName.BLOOM;
  private static final int SEAT_NUMBER = 1;
  private static final int NEXT_SEAT_NUMBER = 2;
  private static final SeatStatus AVAILABLE_SEAT_STATUS = SeatStatus.AVAILABLE;
  private static final SeatStatus OCCUPIED_SEAT_STATUS = SeatStatus.OCCUPIED;
  private static final SeatStatus RESERVED_SEAT_STATUS = SeatStatus.RESERVED;
  private static final String GROUP_NAME = "Les 4 fees";

  private Cube cube;
  private Seat availableSeat;
  private Seat nextAvailableSeat;
  private Seat occupiedSeat;
  private Seat nextOccupiedSeat;

  @BeforeEach
  public void setUp() {
    cube = new Cube(DEFAULT_CUBE_NAME);
    availableSeat = new Seat(SEAT_NUMBER, AVAILABLE_SEAT_STATUS);
    nextAvailableSeat = new Seat(NEXT_SEAT_NUMBER, AVAILABLE_SEAT_STATUS);
    occupiedSeat = new Seat(SEAT_NUMBER, OCCUPIED_SEAT_STATUS);
    nextOccupiedSeat = new Seat(NEXT_SEAT_NUMBER, OCCUPIED_SEAT_STATUS);
  }

  @Test
  void whenAddingASeat_thenSeatIsAddedToCube() {
    cube.addSeat(availableSeat);

    assertThat(cube.getSeats(), contains(availableSeat));
  }

  @Test
  void whenAddingConsecutiveSeats_thenSeatsAreAddedInSameOrderToList() {
    cube.addSeat(availableSeat);
    cube.addSeat(nextAvailableSeat);

    assertThat(cube.getSeats(), contains(availableSeat, nextAvailableSeat));
  }

  @Test
  void whenAddingAnAvailableSeat_thenSeatIsAddedToAvailableSeats() {
    cube.addSeat(availableSeat);

    assertThat(cube.getAvailableSeats(), contains(availableSeat));
  }

  @Test
  void whenAddingAnOccupiedSeat_thenSeatIsNotAddedToAvailableSeats() {
    cube.addSeat(occupiedSeat);

    assertEquals(0, cube.getAvailableSeats().size());
  }

  @Test
  void givenCubeWithAllAvailableSeats_whenCheckingIfAllSeatsAreAvailable_thenReturnsTrue() {
    cube.addSeat(availableSeat);
    cube.addSeat(nextAvailableSeat);

    boolean hasAllAvailableSeats  = cube.hasAllAvailableSeats();

    assertTrue(hasAllAvailableSeats);
  }

  @Test
  void givenCubeWithAllOccupiedSeats_whenCheckingIfAllSeatsAreAvailable_thenReturnsFalse() {
    cube.addSeat(occupiedSeat);
    cube.addSeat(nextOccupiedSeat);

    boolean hasAllAvailableSeats  = cube.hasAllAvailableSeats();

    assertFalse(hasAllAvailableSeats);
  }

  @Test
  void givenCubeWithAvailableAndOccupiedSeats_whenCheckingIfAllSeatsAreAvailable_thenReturnsFalse() {
    cube.addSeat(availableSeat);
    cube.addSeat(occupiedSeat);

    boolean hasAllAvailableSeats  = cube.hasAllAvailableSeats();

    assertFalse(hasAllAvailableSeats);
  }

  @Test
  void givenOccupiedSeat_whenFreeingSeat_thenSeatIsAvailable() {
    cube.addSeat(occupiedSeat);

    cube.freeCubeSeats();

    assertEquals(AVAILABLE_SEAT_STATUS, occupiedSeat.getStatus());
  }

  @Test
  void givenCubeWithAvailableSeats_whenReservingAllSeats_thenAllSeatsAreReserved() {
    cube.addSeat(availableSeat);
    cube.addSeat(nextAvailableSeat);

    cube.reserveAllSeats(GROUP_NAME);

    assertEquals(RESERVED_SEAT_STATUS, availableSeat.getStatus());
    assertEquals(RESERVED_SEAT_STATUS, nextAvailableSeat.getStatus());
  }
}
