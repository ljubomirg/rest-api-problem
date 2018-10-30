package app.service.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.service.sql.beans.LocationSQL;

@Repository
public interface LocationSQLRepository extends CrudRepository<LocationSQL, Long> {

	@Query(value = "SELECT * FROM postcodelatlng  WHERE postcode=:postcode", nativeQuery = true)
	public LocationSQL getLocationSQLByPostcode(@Param("postcode") String postcode);

	@Transactional
	@Modifying
	@Query(value = "UPDATE postcodelatlng SET latitude=:latitude WHERE postcode=:postcode", nativeQuery = true)
	public int updateLatitude(@Param("postcode") String postcode, @Param("latitude") double latitude);

	@Transactional
	@Modifying
	@Query(value = "UPDATE postcodelatlng SET longitude=:longitude WHERE postcode=:postcode", nativeQuery = true)
	public int updateLongitude(@Param("postcode") String postcode, @Param("longitude") double longitude);

	@Transactional
	@Modifying
	@Query(value = "UPDATE postcodelatlng SET latitude=:latitude, longitude=:longitude WHERE postcode=:postcode", nativeQuery = true)
	public int updateLatitudeAndLongitude(@Param("postcode") String postcode, @Param("latitude") double latitude,
			@Param("longitude") double longitude);

}
