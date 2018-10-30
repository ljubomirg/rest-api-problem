package app.service.sql.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postcodelatlng", schema = "public")
public class LocationSQL {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "postcode", unique = true)
	private String postcode;
	@Column(name = "id")
	private int id;
	@Column(name = "latitude")
	private double latitude;
	@Column(name = "longitude")
	private double longitude;

	public LocationSQL() {
	}

	public LocationSQL(String postcode, int id, double latitude, double longitude) {
		super();
		this.postcode = postcode;
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "LocationSQL [postcode=" + postcode + ", id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}

}
