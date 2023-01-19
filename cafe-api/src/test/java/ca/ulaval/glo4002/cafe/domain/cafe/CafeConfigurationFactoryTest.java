package ca.ulaval.glo4002.cafe.domain.cafe;

import ca.ulaval.glo4002.cafe.rest.cafe.CafeConfigurationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CafeConfigurationFactoryTest {

  private static final String ORGANIZATION_NAME = "Scott Tot's Coffee Shop";
  private static final int ORGANIZATION_CUBE_SIZE = 4;
  private static final int ORGANIZATION_TIP_RATE = 7;
  private static final GroupReservationMethod DEFAULT_RESERVATION_METHOD = GroupReservationMethod.DEFAULT;
  private static final GroupReservationMethod NEW_RESERVATION_METHOD = GroupReservationMethod.FULL_CUBES;
  private static final String INVALID_RESERVATION_METHOD = "Michael's Method";

  private CafeConfiguration cafeConfiguration;
  private CafeConfigurationFactory cafeConfigurationFactory;
  private CafeConfigurationDTO cafeConfigurationDTO;

  @BeforeEach
  void setUp() {
    this.cafeConfiguration = new CafeConfiguration(ORGANIZATION_NAME, ORGANIZATION_CUBE_SIZE, DEFAULT_RESERVATION_METHOD, 0);
    this.cafeConfigurationFactory = new CafeConfigurationFactory();
  }

  @Test
  void whenCreateCafeConfiguration_thenShouldReturnNewCafeConfiguration() {
    this.cafeConfigurationDTO = new CafeConfigurationDTO(ORGANIZATION_NAME, ORGANIZATION_CUBE_SIZE, NEW_RESERVATION_METHOD.toString(), ORGANIZATION_TIP_RATE);
    cafeConfiguration = cafeConfigurationFactory.createCafeConfiguration(cafeConfigurationDTO);
    GroupReservationMethod groupReservationMethod = cafeConfiguration.getGroupReservationMethod();

    assertEquals(groupReservationMethod, NEW_RESERVATION_METHOD);
  }

  @Test
  void givenAnInvalidReservationMethodRequest_whenCreateCafeConfiguration_thenShouldThrowInvalidGroupReservationMethodException() {
    this.cafeConfigurationDTO = new CafeConfigurationDTO(ORGANIZATION_NAME, ORGANIZATION_CUBE_SIZE, INVALID_RESERVATION_METHOD, ORGANIZATION_TIP_RATE);
    assertThrows(InvalidGroupReservationMethodException.class, () -> cafeConfigurationFactory.createCafeConfiguration(cafeConfigurationDTO));
  }
}
