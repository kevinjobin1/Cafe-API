package ca.ulaval.glo4002.cafe.domain.cafe.seat;

public enum SeatStatus {
  AVAILABLE("Available"),
  OCCUPIED("Occupied"),
  RESERVED("Reserved");

  private final String status;

  SeatStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return status;
  }
}
