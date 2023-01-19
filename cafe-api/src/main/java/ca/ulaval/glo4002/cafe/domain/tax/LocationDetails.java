package ca.ulaval.glo4002.cafe.domain.tax;

public class LocationDetails {

  private final String country;
  private final String province;
  private final String state;

  public LocationDetails(String country, String province, String state) {
    this.country = country;
    this.province = province;
    this.state = state;
  }

  public String getCountry() {
    return this.country;
  }

  public String getProvince() {
    return this.province;
  }

  public String getState() {
    return this.state;
  }

}
