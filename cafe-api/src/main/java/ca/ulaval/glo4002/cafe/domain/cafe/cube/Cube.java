package ca.ulaval.glo4002.cafe.domain.cafe.cube;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ca.ulaval.glo4002.cafe.domain.cafe.seat.Seat;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.SeatStatus;

public class Cube {
  private final CubeName cubeName;
  private final List<Seat> seats = new ArrayList<>();

  public Cube(CubeName cubeName) {
    this.cubeName = cubeName;
  }

  public void addSeat(Seat seat) {
    seats.add(seat);
  }

  public CubeName getCubeName() {
    return cubeName;
  }

  public int getCubeSize() {
    return this.seats.size();
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public List<Seat> getAvailableSeats() {
    return seats.stream().filter(seat -> seat.getStatus().equals(SeatStatus.AVAILABLE)).toList();
  }

  public List<Seat> getAvailableGroupSeats(String groupName) {
    return this.seats.stream().filter(seat -> seat.getStatus() == SeatStatus.RESERVED && seat.getGroupName().equals(groupName)).toList();
  }

  public Optional<Seat> getSeatByCustomerId(String customerId) {
    return this.seats.stream().filter(seat -> customerId.equals(seat.getCustomerId())).findFirst();
  }

  public void freeAllGroupSeats(String groupName) {
    List<Seat> groupSeats = this.getGroupSeats(groupName);
    for (Seat seat : groupSeats) {
      seat.freeSeat();
    }
  }

  public List<Seat> getGroupSeats(String groupName) {
    return this.seats.stream().filter(seat -> groupName.equals(seat.getGroupName())).toList();
  }

  public boolean hasAllAvailableSeats() {
    return seats.stream().allMatch(seat -> seat.getStatus().equals(SeatStatus.AVAILABLE));
  }

  public void freeCubeSeats() {
    for (Seat seat : seats) {
      seat.freeSeat();
    }
  }

  public void reserveAllSeats(String groupName) {
    for (Seat seat : seats) {
      seat.reserveSeat(groupName);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cube cube = (Cube) o;
    return cubeName == cube.cubeName && Objects.equals(seats, cube.seats);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cubeName, seats);
  }
}
