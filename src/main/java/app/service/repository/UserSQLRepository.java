package app.service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.service.sql.beans.UserSQL;

@Repository
public interface UserSQLRepository extends CrudRepository<UserSQL, Long> {

	@Query(value = "SELECT * FROM appuser WHERE username=:username and password=:password", nativeQuery = true)
	public UserSQL checkUserAuthentication(@Param("username") String username, @Param("password") String password);
}
