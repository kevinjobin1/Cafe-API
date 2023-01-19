package ca.ulaval.glo4002.cafe.domain.cafe;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cafe.domain.product.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.ulaval.glo4002.cafe.domain.cafe.cube.CubeName;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.Seat;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.SeatStatus;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategy;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategyDefault;
import ca.ulaval.glo4002.cafe.domain.customer.Customer;
import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import ca.ulaval.glo4002.cafe.rest.customer.DuplicateCustomerIdException;
import ca.ulaval.glo4002.cafe.rest.customer.InvalidCustomerIdException;
import ca.ulaval.glo4002.cafe.rest.reservation.DuplicateGroupNameException;
import ca.ulaval.glo4002.cafe.rest.reservation.NoGroupSeatsException;

import static org.junit.jupiter.api.Assertions.*;

class CafeTest {

  private static final String ORGANISATION_NAME = "Les 4-Fées";
  private static final int CUBE_SIZE = 4;
  private static final int NUMBER_OF_CUBE = 4;

  private static final CubeName EXPECTED_CUBE_1_NAME = CubeName.BLOOM;
  private static final CubeName EXPECTED_CUBE_2_NAME = CubeName.MERRYWEATHER;
  private static final CubeName EXPECTED_CUBE_3_NAME = CubeName.TINKER_BELL;
  private static final CubeName EXPECTED_CUBE_4_NAME = CubeName.WANDA;

  private static final int GROUP_SIZE = 2;
  private static final ReservationStrategy DEFAULT_RESERVATION_STRATEGY = new ReservationStrategyDefault();

  private static final String CUSTOMER_ID = "1";
  private static final String CUSTOMER_NAME = "Michael Scarn";
  private static final String CUSTOMER_GROUP_NAME = "Group 1";

  private static final String CUSTOMER_ID_2 = "2";
  private static final String CUSTOMER_ID_3 = "3";

  private final List<Product> products = new ArrayList<>();
  private Customer customer;
  private Cafe cafe;

  @BeforeEach
  public void setUp() {
    List<Ingredient> ingredients = new ArrayList<>();
    this.products.add(new Product("Latte", 2.95, ingredients));
    this.products.add(new Product("Mocha", 4.15, ingredients));
    Inventory inventory = new Inventory(new ArrayList<>());
    cafe = new Cafe(ORGANISATION_NAME, CUBE_SIZE, DEFAULT_RESERVATION_STRATEGY, new Menu(products), inventory, 0);
  }

  @Test
  void whenCafeIsInitialized_thenFourCubesAreAlwaysCreated() {
    assertEquals(NUMBER_OF_CUBE, cafe.getCubes().size());
  }

  @Test
  void whenCafeIsInitialized_thenCubesAreCreatedInOrder() {
    assertEquals(EXPECTED_CUBE_1_NAME, cafe.getCubes().get(0).getCubeName());
    assertEquals(EXPECTED_CUBE_2_NAME, cafe.getCubes().get(1).getCubeName());
    assertEquals(EXPECTED_CUBE_3_NAME, cafe.getCubes().get(2).getCubeName());
    assertEquals(EXPECTED_CUBE_4_NAME, cafe.getCubes().get(3).getCubeName());
  }

  @Test
  void whenCafeIsInitialized_thenCubeHasTheSpecifiedSize() {
    assertEquals(CUBE_SIZE, cafe.getCubes().get(0).getSeats().size());
    assertEquals(CUBE_SIZE, cafe.getCubes().get(1).getSeats().size());
    assertEquals(CUBE_SIZE, cafe.getCubes().get(2).getSeats().size());
    assertEquals(CUBE_SIZE, cafe.getCubes().get(3).getSeats().size());
  }

  @Test
  void whenCafeIsInitialized_thenCubeHasSeatsInOrder() {
    assertEquals(1, cafe.getCubes().get(0).getSeats().get(0).getSeatNumber());
    assertEquals(2, cafe.getCubes().get(0).getSeats().get(1).getSeatNumber());
    assertEquals(3, cafe.getCubes().get(0).getSeats().get(2).getSeatNumber());
    assertEquals(4, cafe.getCubes().get(0).getSeats().get(3).getSeatNumber());
  }

  @Test
  void whenClosingCafe_thenAllSeatsAreFree() {
    cafe.closeCafe();

    assertEquals(CUBE_SIZE, cafe.getCubes().get(0).getAvailableSeats().size());
    assertEquals(CUBE_SIZE, cafe.getCubes().get(1).getAvailableSeats().size());
    assertEquals(CUBE_SIZE, cafe.getCubes().get(2).getAvailableSeats().size());
    assertEquals(CUBE_SIZE, cafe.getCubes().get(3).getAvailableSeats().size());
  }

  @Test
  void whenClosingCafe_thenReservationsAreCleared() {
    cafe.closeCafe();

    assertEquals(0, cafe.getReservations().size());
  }

  @Test
  void whenClosingCafe_thenPreviousCustomerCanCheckInAgain() {
    Customer customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.occupySeat(customer);

    cafe.closeCafe();

    assertDoesNotThrow(() ->cafe.occupySeat(customer));
  }

  @Test
  void whenAddingReservation_thenReservationIsAdded() {
    cafe.addReservation(new Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE));

    assertEquals(1, cafe.getReservations().size());
  }

  @Test
  void givenAReservation_whenAddingAReservationWithSameGroupName_thenShouldThrowDuplicateGroupNameException() {
    Reservation reservation = new Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);

    cafe.addReservation(reservation);

    assertThrows(DuplicateGroupNameException.class, () -> cafe.addReservation(reservation));
  }

  @Test
  void givenAReservationInDefaultStrategy_whenReserveSeats_thenSeatsAreReserved() {
    Reservation reservation = new Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);
    cafe.addReservation(reservation);

    cafe.reserveSeats(reservation);

    assertEquals(SeatStatus.RESERVED, cafe.getSeats().get(0).getStatus());
    assertEquals(SeatStatus.RESERVED, cafe.getSeats().get(1).getStatus());
  }

  @Test
  void givenACustomerWhoAlreadyVisited_whenOccupySeat_thenShouldThrowDuplicateCustomerIdException() {
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);

    cafe.occupySeat(customer);

    assertThrows(DuplicateCustomerIdException.class, () -> cafe.occupySeat(customer));
  }

  @Test
  void givenACustomerWithoutAGroup_whenOccupySeat_thenCustomerOccupiesFirstAvailableSeat() {
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);

    cafe.occupySeat(customer);

    assertEquals(SeatStatus.OCCUPIED, cafe.getCubes().get(0).getSeats().get(0).getStatus());
  }

  @Test
  void givenACustomerWithoutAGroup_whenOccupySeat_thenCustomerIsAddedToList() {
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);

    cafe.occupySeat(customer);

    assertEquals(1, cafe.getCustomers().size());
  }

  @Test
  void givenACustomerWithAGroup_whenOccupySeat_thenCustomerOccupiesFirstReservedSeat() {
    Reservation reservation = new  Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);
    cafe.addReservation(reservation);
    cafe.reserveSeats(reservation);
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_GROUP_NAME);

    cafe.occupySeat(customer);

    assertEquals(SeatStatus.OCCUPIED, cafe.getSeats().get(0).getStatus());
  }

  @Test
  void givenACustomerWithAGroupWithNoReservedSeatsLeft_whenOccupySeat_thenShouldThrowNoGroupSeatsException() {
    Reservation reservation = new  Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);
    cafe.addReservation(reservation);
    cafe.reserveSeats(reservation);
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_GROUP_NAME);
    Customer customer2 = new Customer(CUSTOMER_ID_2, CUSTOMER_NAME, CUSTOMER_GROUP_NAME);
    Customer customer3 = new Customer(CUSTOMER_ID_3, CUSTOMER_NAME, CUSTOMER_GROUP_NAME);

    cafe.occupySeat(customer);
    cafe.occupySeat(customer2);

    assertThrows(NoGroupSeatsException.class, () -> cafe.occupySeat(customer3));
  }

  @Test
  void givenACustomerWithAGroup_whenOccupySeat_thenCustomerIsAddedToList() {
    Reservation reservation = new  Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);
    cafe.addReservation(reservation);
    cafe.reserveSeats(reservation);
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_GROUP_NAME);

    cafe.occupySeat(customer);

    assertEquals(1, cafe.getCustomers().size());
  }

  @Test
  void whenGetAvailableSeat_thenShouldReturnFirstAvailableSeat() {
    cafe.getAvailableSeat();

    assertEquals(cafe.getSeats().get(0), cafe.getAvailableSeat());
  }

  @Test
  void givenAReservation_whenGetAvailableGroupSeat_thenShouldReturnFirstReservedSeat() {
    Reservation reservation = new  Reservation(CUSTOMER_GROUP_NAME, GROUP_SIZE);
    cafe.addReservation(reservation);
    cafe.reserveSeats(reservation);

    Seat actual = cafe.getAvailableGroupSeat(CUSTOMER_GROUP_NAME);

    assertEquals(cafe.getSeats().get(0), actual);
  }

  @Test
  void givenACustomerOccupyingASeat_whenGetSeatByCustomerId_thenShouldReturnSeat() {
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.occupySeat(customer);

    Seat actual = cafe.getSeatByCustomerId(CUSTOMER_ID);

    assertEquals(cafe.getSeats().get(0), actual);
  }

  @Test
  void givenACustomer_whenGetCustomerById_thenShouldReturnCustomer() {
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.getCustomers().add(customer);

    Customer actual = cafe.getCustomerById(CUSTOMER_ID);

    assertEquals(customer, actual);
  }

  @Test
  void whenGetCustomerByIdOfInvalidCustomer_thenThrowInvalidCustomerIdException() {
    assertThrows(InvalidCustomerIdException.class, () -> cafe.getCustomerById(CUSTOMER_ID));
  }

  @Test
  void givenACustomerOrder_whenGetCustomerOrder_thenShouldReturnCustomerOrder() {
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.getCustomers().add(customer);
    List<String> orders = new ArrayList<>();
    orders.add("Mocha");
    cafe.placeOrders(customer.getId(), orders);

    List<Product> order = cafe.getCustomerOrder(CUSTOMER_ID);

    assertEquals(orders.get(0), order.get(0).getName());
  }

  @Test
  void givenACustomer_whenPlacingOrdersWithInvalidProduct_thenThrowInvalidMenuOrderException() {
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.getCustomers().add(customer);

    List<String> orders = new ArrayList<>();
    orders.add("Dundee Award");
    assertThrows(InvalidMenuOrderException.class, () -> cafe.placeOrders(CUSTOMER_ID, orders));
  }

  @Test
  void givenACustomer_whenPlacingOrdersWithValidProduct_thenShouldAddOrderToCustomer() {
    customer = new Customer(CUSTOMER_ID, CUSTOMER_NAME, null);
    cafe.getCustomers().add(customer);

    List<String> orders = new ArrayList<>();
    orders.add("Mocha");
    cafe.placeOrders(CUSTOMER_ID, orders);

    assertEquals(orders.get(0), cafe.getCustomerOrder(CUSTOMER_ID).get(0).getName());
  }
}
