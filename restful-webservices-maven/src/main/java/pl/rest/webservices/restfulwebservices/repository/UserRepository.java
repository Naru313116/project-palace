package pl.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rest.webservices.restfulwebservices.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {


}
