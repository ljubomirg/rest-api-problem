package app.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.service.repository.LocationSQLRepository;

@RestController
public class UpdateCoordinatesRestService {

	@Autowired
	private LocationSQLRepository locationSQLRepository;
	
	@RequestMapping("/updateCoordinates")
	public String updateCoordinates(
			@RequestParam(name = "postcode", required = true) String postcode,
			@RequestParam(name = "latitude", required = false) Double latitude, 
			@RequestParam(name = "longitude", required = false) Double longitude, 
			Model model
			) {
		if(latitude != null && longitude != null) {
			locationSQLRepository.updateLatitudeAndLongitude(postcode, latitude, longitude);
		} else if(latitude != null) {
			locationSQLRepository.updateLatitude(postcode, latitude);
		} else if(longitude != null) {
			locationSQLRepository.updateLongitude(postcode, longitude);
		}
		return "success";
	}
}
