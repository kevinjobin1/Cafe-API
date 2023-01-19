package ca.ulaval.glo4002.cafe.domain.cafe;

import java.util.Arrays;

import ca.ulaval.glo4002.cafe.rest.cafe.CafeConfigurationDTO;

public class CafeConfigurationFactory {
  public CafeConfiguration createCafeConfiguration(CafeConfigurationDTO cafeConfigurationDTO) {
    GroupReservationMethod validReservationMethod = Arrays.stream(GroupReservationMethod.values())
            .filter(methodName -> methodName.toString().equals(cafeConfigurationDTO.getGroupReservationMethod()))
            .findFirst().orElseThrow(InvalidGroupReservationMethodException::new);
    if (cafeConfigurationDTO.getTip() < 0 || cafeConfigurationDTO.getTip() > 100) {
      throw new InvalidGroupTipRateException();
    }
    return new CafeConfiguration(cafeConfigurationDTO.getOrganizationName(), cafeConfigurationDTO.getCubeSize(),
            validReservationMethod, cafeConfigurationDTO.getTip());
  }
}
