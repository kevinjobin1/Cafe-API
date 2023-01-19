package ca.ulaval.glo4002.cafe.domain.cafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ca.ulaval.glo4002.cafe.domain.cafe.cube.Cube;
import ca.ulaval.glo4002.cafe.domain.cafe.cube.CubeName;
import ca.ulaval.glo4002.cafe.domain.cafe.seat.Seat;
import ca.ulaval.glo4002.cafe.domain.cafe.strategies.ReservationStrategy;
import ca.ulaval.glo4002.cafe.domain.customer.Customer;
import ca.ulaval.glo4002.cafe.domain.customer.CustomerList;
import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.InsufficientIngredientsException;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;
import ca.ulaval.glo4002.cafe.domain.product.Menu;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.receipt.Receipt;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import ca.ulaval.glo4002.cafe.domain.reservation.ReservationList;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategy;
import ca.ulaval.glo4002.cafe.rest.customer.CustomerAlreadyCheckedOutException;
import ca.ulaval.glo4002.cafe.rest.customer.DuplicateCustomerIdException;
import ca.ulaval.glo4002.cafe.rest.customer.InsufficientSeatsException;
import ca.ulaval.glo4002.cafe.rest.customer.InvalidCustomerIdException;
import ca.ulaval.glo4002.cafe.rest.reservation.NoGroupSeatsException;
import ca.ulaval.glo4002.cafe.rest.reservation.NoReservationsFoundException;

public class Cafe {
  private final String organizationName;
  private final int cubeSize;
  private final ReservationStrategy reservationStrategy;
  private final List<Cube> cubes = new ArrayList<>();
  private final CustomerList customerList = new CustomerList();
  private final ReservationList reservationList = new ReservationList();
  private final Menu menu;
  private final Inventory inventory;
  private final double tipRate;

  public Cafe(String organizationName, int cubeSize, ReservationStrategy reservationStrategy, Menu menu, Inventory inventory, double tipRate) {
    this.organizationName = organizationName;
    this.cubeSize = cubeSize;
    this.reservationStrategy = reservationStrategy;
    this.menu = menu;
    this.inventory = inventory;
    this.tipRate = tipRate;
    initialize();
  }

  private void initialize() {
    int seatNumber = 1;
    for (CubeName cubeName : CubeName.values()) {
      Cube cube = new Cube(cubeName);
      cubes.add(cube);
      for (int i = 0; i < this.cubeSize; i++) {
        cube.addSeat(new Seat(seatNumber));
        seatNumber++;
      }
    }
  }

  public String getOrganizationName() {
    return this.organizationName;
  }

  public int getCubeSize() {
    return this.cubeSize;
  }

  public List<Cube> getCubes() {
    return cubes;
  }

  public Seat getAvailableSeat() {
    List<Seat> availableSeats = new ArrayList<>();
    for (Cube cube : this.cubes) {
      availableSeats.addAll(cube.getAvailableSeats());
    }
    if (availableSeats.isEmpty()) {
      throw new InsufficientSeatsException();
    }
    return availableSeats.get(0);
  }

  public Seat getAvailableGroupSeat(String groupName) {
    List<Seat> availableGroupSeats = new ArrayList<>();
    for (Cube cube : this.cubes) {
      availableGroupSeats.addAll(cube.getAvailableGroupSeats(groupName));
    }
    if (availableGroupSeats.isEmpty()) {
      throw new NoGroupSeatsException();
    }
    return availableGroupSeats.get(0);
  }

  public Seat getSeatByCustomerId(String customerId) {
    for (Cube cube : this.cubes) {
      Optional<Seat> seat = cube.getSeatByCustomerId(customerId);
      if (seat.isPresent()) {
        return seat.get();
      }
    }
    throw new InvalidCustomerIdException();
  }

  public List<Seat> getSeats() {
    List<Seat> seats = new ArrayList<>();
    for (Cube cube : cubes) {
      seats.addAll(cube.getSeats());
    }
    return seats;
  }

  public List<Customer> getCustomers() {
    return this.customerList.getCustomers();
  }

  public void freeAllSeats() {
    for (Cube cube : cubes) {
      cube.freeCubeSeats();
    }
  }

  public void occupySeat(Customer customer) {
    if (customerList.customerAlreadyVisited(customer)) {
      throw new DuplicateCustomerIdException();
    }

    if (customer.getGroupName().isPresent()) {
      Reservation reservation = this.reservationList.getReservationByGroupName(customer.getGroupName().get()).orElseThrow(NoReservationsFoundException::new);
      Seat groupSeat = this.getAvailableGroupSeat(reservation.getGroupName());
      groupSeat.occupySeat(customer.getId());
    }
    else {
      Seat seat = this.getAvailableSeat();
      seat.occupySeat(customer.getId());
    }
    this.customerList.addCustomer(customer);
  }

  public void checkoutCustomer(String customerId, TaxStrategy tax) {
    this.customerList.checkoutCustomer(customerId, tax, this.tipRate);
  }

  public void freeCustomerSeat(String customerId) {
    Seat seat = this.getSeatByCustomerId(customerId);
    String groupName = seat.getGroupName();
    seat.freeSeat();
    if (groupName != null) {
      List<Customer> remainingGroupCustomers = this.customerList.getRemainingCustomersFromGroup(groupName);
      if (remainingGroupCustomers.size() == 0) {
        this.freeAllGroupSeats(groupName);
      }
    }
  }

  public void freeAllGroupSeats(String groupName) {
    for (Cube cube : cubes) {
      cube.freeAllGroupSeats(groupName);
    }
  }

  public Receipt getCustomerReceipt(String customerId) {
    return this.customerList.getCustomerReceipt(customerId);
  }

  public void addReservation(Reservation reservation) {
    this.reservationList.addReservation(reservation);
  }

  public void reserveSeats(Reservation reservation) {
    this.reservationStrategy.reserveSeats(this.cubes, reservation);
  }

  public List<Reservation> getReservations() {
    return this.reservationList.getReservations();
  }

  public List<Product> getCustomerOrder(String customerId) {
    return this.customerList.getCustomerOrder(customerId);
  }

  public Customer getCustomerById(String customerId) {
    return this.customerList.getCustomerById(customerId);
  }

  public void placeOrders(String customerId, List<String> orders) {
    Customer customer = this.customerList.getCustomerById(customerId);
    if (customer.isCheckedOut()) {
      throw new CustomerAlreadyCheckedOutException();
    }

    List<Product> orderedProducts = this.menu.getProductsByName(orders);

    if (!this.inventory.hasEnoughIngredients(orderedProducts)) {
      throw new InsufficientIngredientsException();
    }
    this.inventory.useProductsIngredients(orderedProducts);

    customer.placeOrder(orderedProducts);
  }

  public void closeCafe() {
    this.freeAllSeats();
    this.reservationList.clearAllReservations();
    this.customerList.clearAllCustomers();
    this.inventory.emptyInventory();
  }

  public void addIngredients(List<Ingredient> ingredients) {
    this.inventory.addIngredients(ingredients);
  }

  public Inventory getInventory() {
    return this.inventory;
  }

}
