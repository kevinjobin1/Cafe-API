package ca.ulaval.glo4002.cafe.domain.cafe.seat;

import java.util.Objects;

public class Seat {
  private final int seatNumber;
  private String customerId;
  private String groupName;
  private SeatStatus status = SeatStatus.AVAILABLE;

  public Seat(int number, SeatStatus seatStatus) {
    this.seatNumber = number;
    this.status = seatStatus;
  }

  public Seat(int number) {
    this.seatNumber = number;
  }

  public SeatStatus getStatus() {
    return status;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public void occupySeat(String customerId) {
    this.customerId = customerId;
    this.status = SeatStatus.OCCUPIED;
  }

  public void reserveSeat(String groupName) {
    this.groupName = groupName;
    this.status = SeatStatus.RESERVED;
  }

  public void freeSeat() {
    this.customerId = null;
    this.status = SeatStatus.AVAILABLE;
    this.groupName = null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Seat seat = (Seat) o;
    return seatNumber == seat.seatNumber;
  }

  @Override
  public int hashCode() {
    return Objects.hash(seatNumber, status);
  }
}
