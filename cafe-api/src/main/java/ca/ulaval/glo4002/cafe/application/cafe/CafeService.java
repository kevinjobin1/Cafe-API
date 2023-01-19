package ca.ulaval.glo4002.cafe.application.cafe;

import java.util.List;

import ca.ulaval.glo4002.cafe.application.tax.TaxService;
import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeConfiguration;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeConfigurationFactory;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeFactory;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeRepository;
import ca.ulaval.glo4002.cafe.domain.customer.Customer;
import ca.ulaval.glo4002.cafe.domain.customer.CustomerFactory;
import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.IngredientRepository;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.product.ProductRepository;
import ca.ulaval.glo4002.cafe.domain.receipt.Receipt;
import ca.ulaval.glo4002.cafe.domain.reservation.Reservation;
import ca.ulaval.glo4002.cafe.domain.reservation.ReservationFactory;
import ca.ulaval.glo4002.cafe.rest.cafe.CafeConfigurationDTO;
import ca.ulaval.glo4002.cafe.domain.tax.LocationDetails;
import ca.ulaval.glo4002.cafe.rest.reservation.ReservationDTO;

public class CafeService {
  private final CafeRepository cafeRepository;
  private final CafeFactory cafeFactory;
  private final CafeConfigurationFactory cafeConfigurationFactory;
  private final CustomerFactory customerFactory;
  private final ReservationFactory reservationFactory;
  private final ProductRepository productRepository;
  private final TaxService taxService;
  private final IngredientRepository ingredientRepository;
  private String organizationName;

  public CafeService(CafeRepository cafeRepository, CafeFactory cafeFactory, CustomerFactory customerFactory, ReservationFactory reservationFactory,
                     CafeConfigurationFactory cafeConfigurationFactory, ProductRepository productRepository, TaxService taxService,
                     IngredientRepository ingredientRepository) {
    this.cafeRepository = cafeRepository;
    this.cafeFactory = cafeFactory;
    this.customerFactory = customerFactory;
    this.reservationFactory = reservationFactory;
    this.cafeConfigurationFactory = cafeConfigurationFactory;
    this.productRepository = productRepository;
    this.taxService = taxService;
    this.ingredientRepository = ingredientRepository;
  }

  public Cafe getCafe() {
    return this.cafeRepository.findByName(this.organizationName);
  }

  public List<Reservation> getAllReservations() {
    return this.cafeRepository.findByName(this.organizationName).getReservations();
  }

  public void createCafe(CafeConfiguration cafeConfiguration) {
    List<Product> products = this.productRepository.findAll();
    List<Ingredient> ingredients = this.ingredientRepository.findAll();
    Cafe cafe = this.cafeFactory.createCafe(cafeConfiguration, products, ingredients);
    this.organizationName = cafeConfiguration.getOrganizationName();
    this.cafeRepository.save(cafe);
  }

  public void closeCafe() {
    this.cafeRepository.findByName(this.organizationName).closeCafe();
  }

  public void updateCafeConfiguration(CafeConfigurationDTO cafeConfigurationDTO, LocationDetails locationDetails) {
    CafeConfiguration newCafeConfiguration = cafeConfigurationFactory.createCafeConfiguration(cafeConfigurationDTO);
    this.taxService.setLocation(locationDetails);
    this.createCafe(newCafeConfiguration);
  }

  public void checkInCustomer(Customer customer) {
    this.cafeRepository.findByName(this.organizationName).occupySeat(customer);
  }

  public void checkoutCustomer(String customerId) {
    this.cafeRepository.findByName(this.organizationName).checkoutCustomer(customerId, this.taxService.getTax());
    this.cafeRepository.findByName(this.organizationName).freeCustomerSeat(customerId);
  }

  public Receipt getCustomerReceipt(String customerId) {
    return this.cafeRepository.findByName(this.organizationName).getCustomerReceipt(customerId);
  }

  public Customer getCustomerById(String customerId) {
    return this.cafeRepository.findByName(this.organizationName).getCustomerById(customerId);
  }

  public int findCustomerSeatNumber(String customerId) {
    return cafeRepository.findByName(this.organizationName).getSeatByCustomerId(customerId).getSeatNumber();
  }

  public void addReservation(ReservationDTO reservationDTO) {
    Reservation reservation = this.reservationFactory.createReservation(reservationDTO.getGroupName(), reservationDTO.getGroupSize());
    this.cafeRepository.findByName(this.organizationName).addReservation(reservation);
    this.cafeRepository.findByName(this.organizationName).reserveSeats(reservation);
  }

  public List<Product> getCustomerOrder(String customerId) {
    return this.cafeRepository.findByName(this.organizationName).getCustomerOrder(customerId);
  }

  public void orderProducts(String customerId, List<String> orders) {
    this.cafeRepository.findByName(this.organizationName).placeOrders(customerId, orders);
  }

  public void addIngredients(List<Ingredient> ingredients) {
    this.cafeRepository.findByName(this.organizationName).addIngredients(ingredients);
  }

  public Inventory getInventory() {
    return this.cafeRepository.findByName(this.organizationName).getInventory();
  }
}
