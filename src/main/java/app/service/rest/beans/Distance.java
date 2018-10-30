package app.service.rest.beans;

public class Distance {

	public Location firstLocation;
	public Location secondLocation;
	public double value;
	public static final String unit = "km";

	public Distance() {
	}

	public Distance(Location firstLocation, Location secondLocation, double value) {
		super();
		this.firstLocation = firstLocation;
		this.secondLocation = secondLocation;
		this.value = value;
	}

}
