package ca.ulaval.glo4002.cafe.rest.cafe;

public class CafeConfigurationAssembler {

  public CafeConfigurationDTO fromRequest(CafeConfigurationRequest cafeConfigurationRequest) {
    return new CafeConfigurationDTO(cafeConfigurationRequest.organizationName, cafeConfigurationRequest.cubeSize,
      cafeConfigurationRequest.groupReservationMethod, cafeConfigurationRequest.groupTipRate);
  }

}
