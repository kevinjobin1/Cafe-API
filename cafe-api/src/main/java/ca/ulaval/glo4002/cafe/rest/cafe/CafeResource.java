package ca.ulaval.glo4002.cafe.rest.cafe;

import ca.ulaval.glo4002.cafe.application.cafe.CafeService;
import ca.ulaval.glo4002.cafe.domain.cafe.Cafe;
import ca.ulaval.glo4002.cafe.domain.ingredient.Ingredient;
import ca.ulaval.glo4002.cafe.domain.ingredient.Inventory;
import ca.ulaval.glo4002.cafe.domain.tax.LocationDetails;
import ca.ulaval.glo4002.cafe.rest.inventory.InventoryAssembler;
import ca.ulaval.glo4002.cafe.rest.inventory.InventoryRequest;
import ca.ulaval.glo4002.cafe.rest.inventory.InventoryResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/")
public class CafeResource {
  private final CafeAssembler cafeAssembler;
  private final CafeService cafeService;
  private final InventoryAssembler inventoryAssembler;
  private final CafeConfigurationAssembler cafeConfigurationAssembler;
  private final LocationDetailsAssembler locationDetailsAssembler;

  public CafeResource(CafeAssembler cafeAssembler, CafeService cafeService, InventoryAssembler inventoryAssembler,
                      CafeConfigurationAssembler cafeConfigurationAssembler, LocationDetailsAssembler locationDetailsAssembler) {
    this.cafeAssembler = cafeAssembler;
    this.cafeService = cafeService;
    this.inventoryAssembler = inventoryAssembler;
    this.cafeConfigurationAssembler = cafeConfigurationAssembler;
    this.locationDetailsAssembler = locationDetailsAssembler;
  }

  @GET
  @Path("layout")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getLayout() {
    Cafe cafe = cafeService.getCafe();
    CafeResponse cafeResponse = cafeAssembler.toResponse(cafe);
    return Response.ok(cafeResponse).build();
  }

  @POST()
  @Path("close")
  public Response closeCafe() {
    cafeService.closeCafe();
    return Response.ok().build();
  }

  @POST()
  @Path("config")
  public Response postCafeConfig(CafeConfigurationRequest cafeConfigurationRequest) {
    CafeConfigurationDTO cafeConfigurationDTO = this.cafeConfigurationAssembler.fromRequest(cafeConfigurationRequest);
    LocationDetails locationDetails = this.locationDetailsAssembler.fromRequest(cafeConfigurationRequest);
    cafeService.updateCafeConfiguration(cafeConfigurationDTO, locationDetails);
    return Response.ok().build();
  }

  @GET
  @Path("inventory")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getInventory() {
    Inventory inventory = this.cafeService.getInventory();
    InventoryResponse inventoryResponse = this.inventoryAssembler.toResponse(inventory);
    return Response.ok(inventoryResponse).build();
  }

  @PUT
  @Path("inventory")
  public Response putInventory(InventoryRequest inventoryRequest) {
    List<Ingredient> ingredients = this.inventoryAssembler.fromRequest(inventoryRequest);
    this.cafeService.addIngredients(ingredients);
    return Response.ok().build();
  }
}
