package ca.ulaval.glo4002.cafe.rest.reservation;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;

public class ReservationAssembler {

  public List<ReservationResponse> toResponse(List<Reservation> reservations) {
    List<ReservationResponse> reservationResponses = new ArrayList<>();
    for (Reservation reservation : reservations) {
      reservationResponses.add(toResponse(reservation));
    }
    return reservationResponses;
  }

  public ReservationResponse toResponse(Reservation reservation) {
    return new ReservationResponse(reservation.getGroupName(), reservation.getGroupSize());
  }

  public ReservationDTO fromRequest(ReservationRequest reservationRequest) {
    return new ReservationDTO(reservationRequest.groupName, reservationRequest.groupSize);
  }
}
