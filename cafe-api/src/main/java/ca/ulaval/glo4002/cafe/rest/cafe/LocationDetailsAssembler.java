package ca.ulaval.glo4002.cafe.rest.cafe;

import ca.ulaval.glo4002.cafe.domain.tax.LocationDetails;

public class LocationDetailsAssembler {

  public LocationDetails fromRequest(CafeConfigurationRequest cafeConfigurationRequest) {
    return new LocationDetails(cafeConfigurationRequest.country, cafeConfigurationRequest.province, cafeConfigurationRequest.state);
  }

}
