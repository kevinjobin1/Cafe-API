package ca.ulaval.glo4002.cafe.rest.cafe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CafeConfigurationRequest {

  @NotNull
  @JsonProperty("group_reservation_method")
  public String groupReservationMethod;

  @NotNull
  @JsonProperty("organization_name")
  public String organizationName;

  @NotNull
  @JsonProperty("cube_size")
  public int cubeSize;

  @NotNull
  @JsonProperty("country")
  public String country;

  @NotNull
  @JsonProperty("province")
  public String province;

  @NotNull
  @JsonProperty("state")
  public String state;

  @NotNull
  @JsonProperty("group_tip_rate")
  public double groupTipRate;
}
