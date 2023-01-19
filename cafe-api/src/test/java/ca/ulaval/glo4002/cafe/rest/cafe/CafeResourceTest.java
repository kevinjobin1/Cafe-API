package ca.ulaval.glo4002.cafe.rest.cafe;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import ca.ulaval.glo4002.cafe.application.cafe.CafeService;
import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.domain.cafe.GroupReservationMethod;
import ca.ulaval.glo4002.cafe.domain.cafe.cube.CubeName;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.SeatStatus;
import ca.ulaval.glo4002.cafe.rest.cafe.cube.CubeResponse;
import ca.ulaval.glo4002.cafe.rest.cafe.seat.SeatResponse;
import ca.ulaval.glo4002.cafe.rest.inventory.InventoryAssembler;
import jakarta.ws.rs.core.Response;

public class CafeResourceTest {
  private final ObjectMapper mapper = new ObjectMapper();
  private static final String A_CAFE_NAME = "Les 4 Fées";
  private static final String A_CUBE_NAME = CubeName.BLOOM.toString();
  private static final int A_SEAT_NUMBER = 11;
  private static final String A_CUSTOMER_ID = "123456789";
  private static final String A_GROUP_NAME = "Les 4 Chums";
  private static final String A_SEAT_STATUTS = SeatStatus.AVAILABLE.toString();
  private static final int A_CUBE_SIZE = 4;
  private static final GroupReservationMethod A_GROUP_RESERVATION_METHOD = GroupReservationMethod.DEFAULT;
  private final CafeResponse A_CAFE_LAYOUT = givenCafeLayout();
  private final CafeConfigurationRequest A_CAFE_CONFIGURATION_REQUEST = givenCafeConfigurationRequest();
  private CafeAssembler cafeAssembler;
  private InventoryAssembler inventoryAssembler;
  private CafeConfigurationAssembler cafeConfigurationAssembler;
  private LocationDetailsAssembler locationDetailsAssembler;

  private CafeService cafeService;
  private Cafe aCafe;
  private CafeResource cafeResource;

  @BeforeEach
  public void setUp() {
    this.cafeService = mock(CafeService.class);
    this.cafeAssembler = mock(CafeAssembler.class);
    this.aCafe = mock(Cafe.class);
    this.inventoryAssembler = mock(InventoryAssembler.class);
    this.cafeConfigurationAssembler = new CafeConfigurationAssembler();
    this.locationDetailsAssembler = new LocationDetailsAssembler();
    this.cafeResource = new CafeResource(cafeAssembler, cafeService, inventoryAssembler, cafeConfigurationAssembler, locationDetailsAssembler);
  }

  @Test
  public void givenACafeLayout_whenGettingLayout_thenReturnsSameLayoutWithStatusOk() throws JsonProcessingException {
    CafeResponse expectedLayout = A_CAFE_LAYOUT;
    when(cafeService.getCafe()).thenReturn(aCafe);
    when(cafeAssembler.toResponse(aCafe)).thenReturn(expectedLayout);

    Response response = cafeResource.getLayout();

    CafeResponse actualLayout = (CafeResponse) response.getEntity();
    assertEquals(SC_OK, response.getStatus());
    assertEquals(mapper.writeValueAsString(expectedLayout), mapper.writeValueAsString(actualLayout));
  }

  @Test
  public void whenClosingCafe_thenReturnsStatusOk() {
    Response response = cafeResource.closeCafe();

    assertEquals(SC_OK, response.getStatus());
  }

  @Test
  public void givenAConfigurationRequest_whenPostingConfigurationRequest_thenReturnsStatusOk() {
    Response response = cafeResource.postCafeConfig(A_CAFE_CONFIGURATION_REQUEST);

    assertEquals(SC_OK, response.getStatus());
  }

  private static CafeResponse givenCafeLayout() {
    SeatResponse aSeatResponse = new SeatResponse();
    aSeatResponse.number = A_SEAT_NUMBER;
    aSeatResponse.status = A_SEAT_STATUTS;
    aSeatResponse.customer_id = A_CUSTOMER_ID;
    aSeatResponse.group_name = A_GROUP_NAME;
    CubeResponse aCubeResponse = new CubeResponse();
    aCubeResponse.name = A_CUBE_NAME;
    aCubeResponse.seats = List.of(aSeatResponse);
    CafeResponse aCafeResponse = new CafeResponse();
    aCafeResponse.name = A_CAFE_NAME;
    aCafeResponse.cubes = List.of(aCubeResponse);
    return aCafeResponse;
  }

  private static CafeConfigurationRequest givenCafeConfigurationRequest() {
    CafeConfigurationRequest aConfigRequest = new CafeConfigurationRequest();
    aConfigRequest.organizationName = A_CAFE_NAME;
    aConfigRequest.groupReservationMethod = A_GROUP_RESERVATION_METHOD.toString();
    aConfigRequest.cubeSize = A_CUBE_SIZE;
    return aConfigRequest;
  }

}
