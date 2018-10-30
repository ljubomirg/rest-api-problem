package app.service.rest.beans;

public class Location {

	public String postalCode;
	public GeographicCoordinate geographicCoordinate;

	public Location() {
	}

	public Location(String postalCode, GeographicCoordinate geographicCoordinate) {
		super();
		this.postalCode = postalCode;
		this.geographicCoordinate = geographicCoordinate;
	}

}
