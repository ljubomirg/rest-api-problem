package app.service.rest.beans;

public class GeographicCoordinate {

	public Position latitude;
	public Position longitude;

	public GeographicCoordinate() {
	}

	public GeographicCoordinate(Position latitude, Position longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
