package ca.ulaval.glo4002.cafe.rest.reservation;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.cafe.application.cafe.CafeService;
import jakarta.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class ReservationResourceTest {
  private final ObjectMapper mapper = new ObjectMapper();
  private static final String A_GROUP_NAME = "X-Men";
  private static final String A_SECOND_GROUP_NAME = "Avengers";
  private static final int A_GROUP_SIZE = 6;
  private static final int A_SECOND_GROUP_SIZE = 5;
  private final ReservationResponse A_RESERVATION_RESPONSE = new ReservationResponse(A_GROUP_NAME, A_GROUP_SIZE);
  private final ReservationResponse A_SECOND_RESERVATION_RESPONSE = new ReservationResponse(A_SECOND_GROUP_NAME, A_SECOND_GROUP_SIZE);
  private final ReservationRequest A_RESERVATION_REQUEST = givenReservationRequest();
  private final List<ReservationResponse> A_SINGLE_RESERVATION_RESPONSE = List.of(A_RESERVATION_RESPONSE);
  private final List<ReservationResponse> A_MULTIPLE_RESERVATIONS_RESPONSE = List.of(A_RESERVATION_RESPONSE, A_SECOND_RESERVATION_RESPONSE);
  private ReservationAssembler reservationAssembler;
  private CafeService cafeService;
  private ReservationResource reservationResource;

  @BeforeEach
  public void setUp() {
    this.cafeService = mock(CafeService.class);
    this.reservationAssembler = mock(ReservationAssembler.class);
    reservationResource = new ReservationResource(reservationAssembler, cafeService);
  }

  @Test
  public void whenPostingReservation_thenReturnsStatusOk() {
    Response response = reservationResource.postReservation(A_RESERVATION_REQUEST);

    assertEquals(SC_OK, response.getStatus());
  }

  @Test
  public void givenASingleReservation_whenGettingReservation_thenReturnsSameReservationAndStatusOk() throws JsonProcessingException {
    List<ReservationResponse> expectedReservation = A_SINGLE_RESERVATION_RESPONSE;
    when(reservationAssembler.toResponse(cafeService.getAllReservations())).thenReturn(expectedReservation);

    Response response = reservationResource.getReservations();

    List<ReservationResponse> actualReservation = (List<ReservationResponse>) response.getEntity();
    assertEquals(mapper.writeValueAsString(expectedReservation), mapper.writeValueAsString(actualReservation));
    assertEquals(SC_OK, response.getStatus());
  }

  @Test
  public void givenMultipleReservations_whenGettingReservations_thenReturnsSameReservationsAndStatusOk() throws JsonProcessingException {
    List<ReservationResponse> expectedReservations = A_MULTIPLE_RESERVATIONS_RESPONSE;
    when(reservationAssembler.toResponse(cafeService.getAllReservations())).thenReturn(expectedReservations);

    Response response = reservationResource.getReservations();

    List<ReservationResponse> actualReservations = (List<ReservationResponse>) response.getEntity();
    assertEquals(mapper.writeValueAsString(expectedReservations), mapper.writeValueAsString(actualReservations));
    assertEquals(SC_OK, response.getStatus());
  }

  private static ReservationRequest givenReservationRequest() {
    ReservationRequest aReservationRequest = new ReservationRequest();
    aReservationRequest.groupName = A_GROUP_NAME;
    aReservationRequest.groupSize = A_GROUP_SIZE;
    return aReservationRequest;
  }
}
