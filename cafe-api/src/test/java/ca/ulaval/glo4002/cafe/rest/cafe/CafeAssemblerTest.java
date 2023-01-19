package ca.ulaval.glo4002.cafe.rest.cafe;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.product.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.domain.cafe.cube.CubeName;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.SeatStatus;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategy;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategyDefault;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.rest.cafe.cube.CubeAssembler;
import ca.ulaval.glo4002.cafe.rest.cafe.seat.SeatAssembler;

class CafeAssemblerTest {

  private static final ReservationStrategy DEFAULT_GROUP_RESERVATION_STRATEGY = new ReservationStrategyDefault();
  private static final String DEFAULT_CAFE_NAME = "Les 4 Fées";
  private static final int DEFAULT_NUMBER_OF_CUBES = 4;
  private static final CubeName FIRST_CUBE_NAME = CubeName.BLOOM;
  private static final CubeName SECOND_CUBE_NAME = CubeName.MERRYWEATHER;
  private static final CubeName THIRD_CUBE_NAME = CubeName.TINKER_BELL;
  private static final CubeName FORTH_CUBE_NAME = CubeName.WANDA;
  private static final int DEFAULT_NUMBER_OF_SEATS = 4;
  private static final int DEFAULT_FIRST_SEAT_NUMBER = 1;
  private static final int DEFAULT_SECOND_SEAT_NUMBER = 2;
  private static final SeatStatus DEFAULT_FIRST_SEAT_STATUS = SeatStatus.AVAILABLE;
  private static final String DEFAULT_CUSTOMER_ID = null;
  private static final String DEFAULT_GROUP_NAME = null;
  private final List<Product> cafeProducts = List.of();
  private CafeAssembler cafeAssembler;
  private Cafe cafe;
  private Inventory inventory;

  @BeforeEach
  public void setup() {
    this.cafeAssembler = new CafeAssembler(new CubeAssembler(new SeatAssembler()));
    this.inventory = new Inventory(new ArrayList<>());
    this.cafe = new Cafe(DEFAULT_CAFE_NAME, DEFAULT_NUMBER_OF_SEATS, DEFAULT_GROUP_RESERVATION_STRATEGY, new Menu(cafeProducts), inventory, 0);
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenResponseHasValidCafeName() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(DEFAULT_CAFE_NAME, cafeResponse.name);
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenResponseHasValidNumberOfCubes() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(DEFAULT_NUMBER_OF_CUBES, cafeResponse.cubes.size());
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenFirstCubeHasValidNumberOfSeats() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(DEFAULT_NUMBER_OF_SEATS, cafeResponse.cubes.get(0).seats.size());
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenCubesHaveValidNames() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(FIRST_CUBE_NAME.toString(), cafeResponse.cubes.get(0).name);
    assertEquals(SECOND_CUBE_NAME.toString(), cafeResponse.cubes.get(1).name);
    assertEquals(THIRD_CUBE_NAME.toString(), cafeResponse.cubes.get(2).name);
    assertEquals(FORTH_CUBE_NAME.toString(), cafeResponse.cubes.get(3).name);
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenFirstCubeFirstSeatHasValidSeatNumber() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(DEFAULT_FIRST_SEAT_NUMBER, cafeResponse.cubes.get(0).seats.get(0).number);
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenFirstCubeSecondSeatHasValidSeatNumber() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(DEFAULT_SECOND_SEAT_NUMBER, cafeResponse.cubes.get(0).seats.get(1).number);
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenFirstCubeFirstSeatHasValidSeatStatus() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(SeatStatus.AVAILABLE.toString(), cafeResponse.cubes.get(0).seats.get(0).status);
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenFirstCubeFirstSeatIsAvailable() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(DEFAULT_FIRST_SEAT_STATUS.toString(), cafeResponse.cubes.get(0).seats.get(0).status);
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenFirstCubeFirstSeatCustomerIdIsNull() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(DEFAULT_CUSTOMER_ID, cafeResponse.cubes.get(0).seats.get(0).customer_id);
  }

  @Test
  void givenCafe_whenAssemblingResponse_thenFirstCubeFirstSeatGroupNameIsNull() {
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);

    assertEquals(DEFAULT_GROUP_NAME, cafeResponse.cubes.get(0).seats.get(0).group_name);
  }
}

