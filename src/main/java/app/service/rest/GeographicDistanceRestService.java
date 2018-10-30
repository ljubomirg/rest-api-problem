package app.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.service.repository.LocationSQLRepository;
import app.service.repository.UserSQLRepository;
import app.service.rest.beans.Distance;
import app.service.rest.beans.GeographicCoordinate;
import app.service.rest.beans.Location;
import app.service.rest.beans.Position;
import app.service.sql.beans.LocationSQL;
import app.service.sql.beans.UserSQL;

@RestController
public class GeographicDistanceRestService {

	private static double EARTH_RADIUS = 6371; // radius in kilometers

	@Autowired
	private LocationSQLRepository locationSQLRepository;

	@Autowired
	private UserSQLRepository userSQLRepository;

	@RequestMapping("/geographicDistance")
	public Distance getGeographicDistance(
			@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam(name = "firstPostalCode", required = true) String firstPostalCode,
			@RequestParam(name = "secondPostalCode", required = true) String secondPostalCode, 
			Model model
			) {

		UserSQL userSQL = userSQLRepository.checkUserAuthentication(username, password);
		if(userSQL == null) {
			//here we can decide what to do with the user that is not in the database
			//we do not need to send username and password as params, we can use the model to check is there some 
			//information about authentication or username and password, and then decide what to do
		}
		
		LocationSQL firstLocationSQL = locationSQLRepository.getLocationSQLByPostcode(firstPostalCode);
		LocationSQL secondLocationSQL = locationSQLRepository.getLocationSQLByPostcode(secondPostalCode);

		double distanceInKM = calculateDistance(firstLocationSQL.getLatitude(), firstLocationSQL.getLongitude(), secondLocationSQL.getLatitude(), secondLocationSQL.getLongitude());

		Position firstLatitude = new Position(firstLocationSQL.getLatitude(), firstLocationSQL.getLatitude() < 0 ? "S" : "N");
		Position firstLongitude = new Position(firstLocationSQL.getLongitude(), firstLocationSQL.getLongitude() < 0 ? "W" : "E");
		Position secondLatitude = new Position(secondLocationSQL.getLatitude(), secondLocationSQL.getLatitude() < 0 ? "S" : "N");
		Position secondLongitude = new Position(secondLocationSQL.getLongitude(), secondLocationSQL.getLongitude() < 0 ? "W" : "E");

		GeographicCoordinate firstGeographicCoordinate = new GeographicCoordinate(firstLatitude, firstLongitude);
		GeographicCoordinate secondGeographicCoordinate = new GeographicCoordinate(secondLatitude, secondLongitude);

		Location firstLocation = new Location(firstPostalCode, firstGeographicCoordinate);
		Location secondLocation = new Location(secondPostalCode, secondGeographicCoordinate);

		Distance distance = new Distance(firstLocation, secondLocation, distanceInKM);

		return distance;
	}

	private double calculateDistance(double latitude, double longitude, double latitude2, double longitude2) {
		// Using Haversine formula! See Wikipedia;
		double lon1Radians = Math.toRadians(longitude);
		double lon2Radians = Math.toRadians(longitude2);
		double lat1Radians = Math.toRadians(latitude);
		double lat2Radians = Math.toRadians(latitude2);
		double a = haversine(lat1Radians, lat2Radians)
				+ Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return (EARTH_RADIUS * c);
	}

	private double haversine(double deg1, double deg2) {
		return square(Math.sin((deg1 - deg2) / 2.0));
	}

	private double square(double x) {
		return x * x;
	}

}
