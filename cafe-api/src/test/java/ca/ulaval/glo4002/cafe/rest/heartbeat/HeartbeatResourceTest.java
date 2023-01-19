package ca.ulaval.glo4002.cafe.rest.heartbeat;

import jakarta.ws.rs.core.Response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HeartbeatResourceTest {
  private static final String A_TOKEN = "token";
  private HeartbeatResource heartbeatResource;

  @BeforeEach
  void setUp() {
    heartbeatResource = new HeartbeatResource();
  }

  @Test
  void givenAToken_whenGettingHeartbeat_thenReturnsSameToken() {
    HeartbeatResponse heartbeatResponse = heartbeatResource.heartbeat(A_TOKEN);
    assertEquals(A_TOKEN, heartbeatResponse.token);
  }

  @Test
  void givenAHeartbeat_whenPosting_thenReturnsStatusOk() {
    HeartbeatResponse heartbeatResponse = new HeartbeatResponse(A_TOKEN);
    Response response = heartbeatResource.blabla(heartbeatResponse);
    assertEquals(SC_OK, response.getStatus());
  }
}
