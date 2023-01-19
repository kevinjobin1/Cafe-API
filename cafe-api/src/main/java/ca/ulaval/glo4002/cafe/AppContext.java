package ca.ulaval.glo4002.cafe;

import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.cafe.application.cafe.CafeService;
import ca.ulaval.glo4002.cafe.application.tax.TaxService;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeConfiguration;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeConfigurationFactory;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeFactory;
import ca.ulaval.glo4002.cafe.domain.cafe.CafeRepository;
import ca.ulaval.glo4002.cafe.domain.cafe.GroupReservationMethod;
import ca.ulaval.glo4002.cafe.domain.cafe.ReservationStrategyFactory;
import ca.ulaval.glo4002.cafe.domain.customer.CustomerFactory;
import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.IngredientRepository;
import ca.ulaval.glo4002.cafe.domain.ingredient.InventoryFactory;
import ca.ulaval.glo4002.cafe.domain.product.Product;
import ca.ulaval.glo4002.cafe.domain.product.ProductRepository;
import ca.ulaval.glo4002.cafe.domain.reservation.ReservationFactory;
import ca.ulaval.glo4002.cafe.domain.tax.Tax;
import ca.ulaval.glo4002.cafe.domain.tax.TaxStrategyFactory;
import ca.ulaval.glo4002.cafe.domain.tax.TaxRepository;
import ca.ulaval.glo4002.cafe.infrastructure.CafeRepositoryInMemory;
import ca.ulaval.glo4002.cafe.infrastructure.IngredientRepositoryInMemory;
import ca.ulaval.glo4002.cafe.infrastructure.ProductRepositoryInMemory;
import ca.ulaval.glo4002.cafe.infrastructure.TaxRepositoryInMemory;
import ca.ulaval.glo4002.cafe.rest.cafe.CafeAssembler;
import ca.ulaval.glo4002.cafe.rest.cafe.CafeConfigurationAssembler;
import ca.ulaval.glo4002.cafe.rest.cafe.CafeResource;
import ca.ulaval.glo4002.cafe.rest.cafe.LocationDetailsAssembler;
import ca.ulaval.glo4002.cafe.rest.cafe.cube.CubeAssembler;
import ca.ulaval.glo4002.cafe.rest.cafe.seat.SeatAssembler;
import ca.ulaval.glo4002.cafe.rest.customer.CustomerAssembler;
import ca.ulaval.glo4002.cafe.rest.customer.CustomerResource;
import ca.ulaval.glo4002.cafe.rest.inventory.InventoryAssembler;
import ca.ulaval.glo4002.cafe.rest.order.CustomerOrdersAssembler;
import ca.ulaval.glo4002.cafe.rest.order.ReceiptAssembler;
import ca.ulaval.glo4002.cafe.rest.reservation.ReservationAssembler;
import ca.ulaval.glo4002.cafe.rest.reservation.ReservationResource;

public class AppContext {
  public static final String DEFAULT_CAFE_NAME = "Les 4-Fées";
  public static final int DEFAULT_CUBE_SIZE = 4;
  public static final GroupReservationMethod DEFAULT_RESERVATION_METHOD = GroupReservationMethod.DEFAULT;
  public static final double DEFAULT_TIP_RATE = 0;
  public final CafeConfiguration defaultCafeConfiguration = new CafeConfiguration(DEFAULT_CAFE_NAME, DEFAULT_CUBE_SIZE, DEFAULT_RESERVATION_METHOD,
                                                                                  DEFAULT_TIP_RATE);
  public final InventoryFactory inventoryFactory = new InventoryFactory();
  public final CafeFactory cafeFactory = new CafeFactory(new ReservationStrategyFactory(), inventoryFactory);
  public final SeatAssembler seatAssembler = new SeatAssembler();
  public final CubeAssembler cubeAssembler = new CubeAssembler(seatAssembler);
  public final CafeAssembler cafeAssembler = new CafeAssembler(cubeAssembler);
  public final CustomerAssembler customerAssembler = new CustomerAssembler();
  public final ReservationAssembler reservationAssembler = new ReservationAssembler();
  public final CustomerOrdersAssembler customerOrdersAssembler = new CustomerOrdersAssembler();
  public final ReceiptAssembler receiptAssembler = new ReceiptAssembler();
  public final CafeConfigurationAssembler cafeConfigurationAssembler = new CafeConfigurationAssembler();
  public final LocationDetailsAssembler locationDetailsAssembler = new LocationDetailsAssembler();
  public final CafeRepository cafeRepository = new CafeRepositoryInMemory();
  public final ProductRepository productRepository = new ProductRepositoryInMemory();
  public final CafeConfigurationFactory cafeConfigurationFactory = new CafeConfigurationFactory();
  public final CustomerFactory customerFactory = new CustomerFactory();
  public final ReservationFactory reservationFactory = new ReservationFactory();
  public final TaxStrategyFactory taxFactory = new TaxStrategyFactory();
  public final TaxRepository countryTax = new TaxRepositoryInMemory();
  public final TaxRepository provinceTax = new TaxRepositoryInMemory();
  public final TaxRepository stateTax = new TaxRepositoryInMemory();
  public final TaxService taxService = new TaxService(taxFactory, countryTax, provinceTax, stateTax);
  public final IngredientRepository ingredientRepository = new IngredientRepositoryInMemory();
  public final CafeService cafeService = new CafeService(cafeRepository, cafeFactory, customerFactory, reservationFactory, cafeConfigurationFactory,
                                                         productRepository, taxService, ingredientRepository);
  public final CafeResource cafeResource = new CafeResource(cafeAssembler, cafeService, new InventoryAssembler(), cafeConfigurationAssembler,
      locationDetailsAssembler);
  public final CustomerResource customerResource = new CustomerResource(customerAssembler, cafeService, customerOrdersAssembler, receiptAssembler);
  public final ReservationResource reservationResource = new ReservationResource(reservationAssembler, cafeService);

  public AppContext() {
    initializeProductRepository();
    initializeTaxRepositories();
    initializeIngredientRepository();
    cafeService.createCafe(defaultCafeConfiguration);
  }

  private void initializeProductRepository() {
    List<Ingredient> americanoIngredients = Arrays.asList(new Ingredient("Espresso", 50), new Ingredient("Water", 50));
    this.productRepository.save(new Product("Americano", 2.25, americanoIngredients));
    List<Ingredient> darkRoastIngredients = Arrays.asList(new Ingredient("Espresso", 40), new Ingredient("Water", 40), new Ingredient("Chocolate", 10),
                                                          new Ingredient("Milk", 10));
    this.productRepository.save(new Product("Dark Roast", 2.10, darkRoastIngredients));
    List<Ingredient> cappuccinoIngredients = Arrays.asList(new Ingredient("Espresso", 50), new Ingredient("Water", 40), new Ingredient("Milk", 10));
    this.productRepository.save(new Product("Cappuccino", 3.29, cappuccinoIngredients));
    List<Ingredient> espressoIngredients = Arrays.asList(new Ingredient("Espresso", 60));
    this.productRepository.save(new Product("Espresso", 2.95, espressoIngredients));
    List<Ingredient> flatWhiteIngredients = Arrays.asList(new Ingredient("Espresso", 50), new Ingredient("Milk", 50));
    this.productRepository.save(new Product("Flat White", 3.75, flatWhiteIngredients));
    List<Ingredient> latteIngredients = Arrays.asList(new Ingredient("Espresso", 50), new Ingredient("Milk", 50));
    this.productRepository.save(new Product("Latte", 2.95, latteIngredients));
    List<Ingredient> macchiatoIngredients = Arrays.asList(new Ingredient("Espresso", 80), new Ingredient("Milk", 20));
    this.productRepository.save(new Product("Macchiato", 4.75, macchiatoIngredients));
    List<Ingredient> mochaIngredients = Arrays.asList(new Ingredient("Espresso", 50), new Ingredient("Milk", 40), new Ingredient("Chocolate", 10));
    this.productRepository.save(new Product("Mocha", 4.15, mochaIngredients));
  }

  private void initializeTaxRepositories() {
    this.initializeCountryTaxRepository();
    this.initializeProvinceTaxRepository();
    this.initializeStateTaxRepository();
  }

  private void initializeCountryTaxRepository() {
    this.countryTax.save(new Tax(0, "None"));
    this.countryTax.save(new Tax(0.05, "CA"));
    this.countryTax.save(new Tax(0, "US"));
    this.countryTax.save(new Tax(0.19, "CL"));
  }

  private void initializeProvinceTaxRepository() {
    this.provinceTax.save(new Tax(0, "AB"));
    this.provinceTax.save(new Tax(0.07, "BC"));
    this.provinceTax.save(new Tax(0.07, "MB"));
    this.provinceTax.save(new Tax(0.1, "NB"));
    this.provinceTax.save(new Tax(0.1, "NL"));
    this.provinceTax.save(new Tax(0, "NT"));
    this.provinceTax.save(new Tax(0.1, "NS"));
    this.provinceTax.save(new Tax(0, "NU"));
    this.provinceTax.save(new Tax(0.08, "ON"));
    this.provinceTax.save(new Tax(0.1, "PE"));
    this.provinceTax.save(new Tax(0.09975, "QC"));
    this.provinceTax.save(new Tax(0.06, "SK"));
    this.provinceTax.save(new Tax(0, "YT"));
  }

  private void initializeStateTaxRepository() {
    this.stateTax.save(new Tax(0.04, "AL"));
    this.stateTax.save(new Tax(0.056, "AZ"));
    this.stateTax.save(new Tax(0.0725, "CA"));
    this.stateTax.save(new Tax(0.06, "FL"));
    this.stateTax.save(new Tax(0.055, "ME"));
    this.stateTax.save(new Tax(0.04, "NY"));
    this.stateTax.save(new Tax(0.0625, "TX"));
  }

  private void initializeIngredientRepository() {
    this.ingredientRepository.save(new Ingredient("Chocolate", 0));
    this.ingredientRepository.save(new Ingredient("Espresso", 0));
    this.ingredientRepository.save(new Ingredient("Milk", 0));
    this.ingredientRepository.save(new Ingredient("Water", 0));
  }

}
