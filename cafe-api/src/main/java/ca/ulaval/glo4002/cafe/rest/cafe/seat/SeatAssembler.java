package ca.ulaval.glo4002.cafe.rest.cafe.seat;

import ca.ulaval.glo4002.cafe.domain.cafe.seat.Seat;

public class SeatAssembler {

  public SeatResponse toResponse(Seat seat) {
    SeatResponse seatResponse = new SeatResponse();

    seatResponse.number = seat.getSeatNumber();
    seatResponse.status = seat.getStatus().toString();
    seatResponse.customer_id = seat.getCustomerId();
    seatResponse.group_name = seat.getGroupName();

    return seatResponse;
  }
}
