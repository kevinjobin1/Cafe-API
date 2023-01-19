package ca.ulaval.glo4002.cafe.domain.cafe;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CafeConfigurationTest {
  private static final String EXPECTED_ORGANIZATION_NAME = "Custom Cafe name 12345";
  private static final int EXPECTED_CUBE_SIZE = 10;
  private static final double EXPECTED_TIP_RATE = 0;
  private static final GroupReservationMethod EXPECTED_GROUP_RESERVATION_METHOD = GroupReservationMethod.FULL_CUBES;

  @Test
  void givenACustomConfiguration_whenConfigurationIsCreated_thenOrgNameAndCubeSizeAndGroupReservationMethodAreSet() {
    CafeConfiguration cafeConfiguration = new CafeConfiguration(EXPECTED_ORGANIZATION_NAME, EXPECTED_CUBE_SIZE, EXPECTED_GROUP_RESERVATION_METHOD,
                                                                EXPECTED_TIP_RATE);

    Assertions.assertEquals(EXPECTED_ORGANIZATION_NAME, cafeConfiguration.getOrganizationName());
    Assertions.assertEquals(EXPECTED_CUBE_SIZE, cafeConfiguration.getCubeSize());
    Assertions.assertEquals(EXPECTED_GROUP_RESERVATION_METHOD, cafeConfiguration.getGroupReservationMethod());
    Assertions.assertEquals(EXPECTED_TIP_RATE, cafeConfiguration.getTip());
  }
}
