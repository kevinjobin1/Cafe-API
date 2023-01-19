package ca.ulaval.glo4002.cafe.rest.reservation;

import java.util.List;

import ca.ulaval.glo4002.cafe.application.cafe.CafeService;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/reservations")
public class ReservationResource {
  private final ReservationAssembler reservationAssembler;
  private final CafeService cafeService;

  public ReservationResource(ReservationAssembler reservationAssembler, CafeService cafeService) {
    this.reservationAssembler = reservationAssembler;
    this.cafeService = cafeService;
  }

  @POST()
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response postReservation(ReservationRequest reservationRequest) {
    ReservationDTO reservation = this.reservationAssembler.fromRequest(reservationRequest);
    this.cafeService.addReservation(reservation);
    return Response.ok().build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getReservations() {
    List<Reservation> reservations = cafeService.getAllReservations();
    List<ReservationResponse> reservationResponses = reservationAssembler.toResponse(reservations);
    return Response.ok().entity(reservationResponses).build();
  }
}
