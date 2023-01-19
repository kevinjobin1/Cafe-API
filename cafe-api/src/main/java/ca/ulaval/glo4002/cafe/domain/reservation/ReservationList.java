package ca.ulaval.glo4002.cafe.domain.reservation;

import ca.ulaval.glo4002.cafe.rest.reservation.DuplicateGroupNameException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservationList {

  private final List<Reservation> reservations = new ArrayList<>();

  public void addReservation(Reservation reservation) {
    this.getReservationByGroupName(reservation.getGroupName()).ifPresent(existingReservation -> {
      throw new DuplicateGroupNameException();
    });
    this.reservations.add(reservation);
  }

  public Optional<Reservation> getReservationByGroupName(String groupName) {
    return this.reservations.stream().filter(reservation -> reservation.getGroupName().equals(groupName)).findFirst();
  }

  public List<Reservation> getReservations() {
    return this.reservations;
  }

  public void clearAllReservations() {
    this.reservations.clear();
  }

}
